/*
	Copyright 2012 Strategic Gains, Inc.

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

import com.strategicgains.jbel.function.AccessorFunction;
import com.strategicgains.jbel.function.UnaryFunction;

/**
 * @author toddf
 * @since Dec 14, 2012
 */
public class FieldExpression
extends AbstractExpression
{
	private static final String ARRAY_INDEX_REGEX = "(\\w*)\\[(\\d)\\]";
	private static final Pattern ARRAY_INDEX_PATTERN = Pattern.compile(ARRAY_INDEX_REGEX);

	private String name;
	private List<UnaryFunction> expressions;

	public FieldExpression(String select)
	{
		super();
		this.name = select;
		initialize(select);
		expressions = new ArrayList<UnaryFunction>();
	}

	public String getName()
	{
		return name;
	}

	private void initialize(String select)
	{
		if (select == null || select.trim().isEmpty())
		{
			throw new NullPointerException("Empty/Null select string");
		}

		String[] keys = select.split("\\.");

		for (String key : keys)
		{
			Matcher m = ARRAY_INDEX_PATTERN.matcher(key);

			if (m.matches())
			{
				String name = m.group(1);
				int index = Integer.parseInt(m.group(2));
				expressions.add(new AccessorFunction(name));
				expressions.add(new ElementAccessorExpression(index));
			}
			else
			{
				expressions.add(new AccessorFunction(key));
			}
		}
	}

	// TODO: implement selecting fields from objects in a list...
	//public Object evaluate (List argument)
	
	@Override
	public Object evaluate(Object argument)
	{
		Object result = argument;

		for (UnaryFunction expression : expressions)
		{
			result = expression.perform(result);
		}

		return result;
	}
}
