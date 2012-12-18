/*
    Copyright 2012, Strategic Gains, Inc.

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
package com.strategicgains.jbel;

import java.util.Map;

import com.strategicgains.jbel.builder.ObjectQueryBuilder;
import com.strategicgains.jbel.expression.AccessorExpression;
import com.strategicgains.jbel.expression.AggregationExpression;
import com.strategicgains.jbel.expression.Expression;

/**
 * @author toddf
 * @since Dec 16, 2012
 */
public class JBEL
{
	public static Object field(Object argument, String fieldExpression)
	{
		return new AccessorExpression(fieldExpression).evaluate(argument);
	}

	@SuppressWarnings("unchecked")
    public static Map<String, Object> fields(Object argument, String... fieldExpressions)
	{
		return (Map<String, Object>) new AggregationExpression(fieldExpressions).evaluate(argument);
	}

	public static ObjectQueryBuilder queryBuilder()
	{
		return new ObjectQueryBuilder();
	}

	public static Object query(Expression expression, Object argument)
	{
		return expression.evaluate(argument);
	}
}
