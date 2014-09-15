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
import java.util.Iterator;
import java.util.List;

import com.strategicgains.jbel.exception.EvaluationException;
import com.strategicgains.jbel.function.AccessorFunction;

/**
 * An expression that retrieves a value from an object graph.
 * 
 * @author Todd Fredrich
 * @since Aug 26, 2005
 * @version $Revision: 1.7 $
 */
public class AccessorExpression
implements Expression
{
	/**
	 * A list of AccessorFunction instances that retrieve the field values from an object.
	 */
	private List<AccessorFunction> accessorFunctions;
	
	/**
	 * Construct a new empty AccessorExpression instance.
	 */
	public AccessorExpression()
	{
		accessorFunctions = new ArrayList<AccessorFunction>();
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
		accessorFunctions.add(new AccessorFunction(attributeName));
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
		Iterator<?> iterator = argument.iterator();
		
		while (iterator.hasNext())
		{
			Object result = evaluate(iterator.next());
			results.add(result);
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
		Iterator<?> iterator = accessorFunctions.iterator();
		Object attribute = argument;
		
		while (iterator.hasNext())
		{
			AccessorFunction accessor = (AccessorFunction) iterator.next();
			attribute = accessor.perform(attribute);
		}
		
		return attribute;
	}
}
