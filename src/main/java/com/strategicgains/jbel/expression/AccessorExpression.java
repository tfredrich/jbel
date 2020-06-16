/*
	Copyright 2005 Strategic Gains, Inc.

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package com.strategicgains.jbel.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.strategicgains.jbel.exception.EvaluationException;
import com.strategicgains.jbel.function.AccessorFunction;
import com.strategicgains.jbel.function.ElementAccessorFunction;
import com.strategicgains.jbel.function.UnaryFunction;

/**
 * An expression that retrieves a value from an object graph.
 * 
 * @author Todd Fredrich
 * @since Aug 26, 2005
 */
public class AccessorExpression
implements Expression
{
	private static final String ARRAY_INDEX_REGEX = "(\\w*)\\[(\\d)\\]";
	private static final Pattern ARRAY_INDEX_PATTERN = Pattern.compile(ARRAY_INDEX_REGEX);

	/**
	 * A list of UnaryFunction instances that retrieve the field values from an object.
	 */
	private List<UnaryFunction> accessorFunctions;
	private String name;
	
	/**
	 * Construct a new empty AccessorExpression instance.
	 */
	public AccessorExpression()
	{
		accessorFunctions = new ArrayList<UnaryFunction>();
	}

	/**
	 * Construct a new AccessorExpression instance to access the given field.
	 * 
	 * @param attributeName the name of an object attribute.
	 */
	public AccessorExpression(String attributeName)
	{
		this();
		attribute(attributeName);
	}
	
	/**
	 * Add a new object accessor in the chain of this accessor expression.
	 * 
	 * @param attributeName the name of an object attribute
	 * @return AccessorExpression to facilitate chaining (e.g. expression.attribute("x").attribute("y"))
	 */
	public AccessorExpression attribute(String attributeName)
	{
		if (attributeName == null || attributeName.trim().isEmpty())
		{
			throw new NullPointerException("Empty/Null attribute select string");
		}

		this.name = attributeName;
		String[] segments = attributeName.split("\\.");

		for (String segment : segments)
		{
			Matcher m = ARRAY_INDEX_PATTERN.matcher(segment);

			if (m.matches())
			{
				String name = m.group(1);
				int index = Integer.parseInt(m.group(2));
				accessorFunctions.add(new AccessorFunction(name));
				addIndex(index);
			}
			else
			{
				accessorFunctions.add(new AccessorFunction(segment));
			}
		}

		return this;
	}

	/**
	 * Evaluate the accessor function over the elements of a list, returning the retrieved values
	 * as a list.
	 * 
	 * @param argument a list of objects.
	 * @return a list of retrieved values.
	 * @throws EvaluationException if the expression cannot be evaluated.
	 */
	public Object evaluate(List<?> argument)
	throws EvaluationException
	{
		List<Object> results = new ArrayList<Object>();
		
		for (Object element : argument)
		{
			results.add(evaluate(element));
		}

		return results;
	}

	/**
	 * Retrieve the field value from the argument object.
	 * 
	 * @return the field value as an object.
	 * @throws EvaluationException if the expression cannot be evaluated.
	 * @see com.strategicgains.jbel.function.UnaryFunction#perform(java.lang.Object)
	 */
	public Object evaluate(Object argument)
	throws EvaluationException
	{
		Object attribute = argument;
		
		for(UnaryFunction accessor : accessorFunctions)
		{
			attribute = accessor.perform(attribute);
		}
		
		return attribute;
	}
	
	public String getAttributeName()
	{
		return name;
	}
	
	public AccessorExpression addIndex(int index)
	{
		accessorFunctions.add(new ElementAccessorFunction(index));
		return this;
	}
}
