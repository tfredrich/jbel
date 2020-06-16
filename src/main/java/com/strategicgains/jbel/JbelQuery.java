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

import java.util.Iterator;

import com.strategicgains.jbel.expression.AccessorExpression;
import com.strategicgains.jbel.expression.AggregationExpression;
import com.strategicgains.jbel.expression.Expression;
import com.strategicgains.jbel.expression.MetaExpression;
import com.strategicgains.jbel.util.ArrayStack;
import com.strategicgains.jbel.util.Stack;

/**
 * Supports navigation and dissection of an object model.
 * 
 * @author toddf
 * @since Dec 18, 2012
 */
public class JbelQuery
{
	private Stack<Expression> expressions;

	public JbelQuery()
	{
		expressions = new ArrayStack<Expression>();
	}

	/**
	 * Return a field from the object being queried.
	 * 
	 * @param fieldNameString
	 * @return
	 */
	public JbelQuery field(String fieldNameString)
	{
		Expression exp = new AccessorExpression(fieldNameString);
		expressions.push(exp);
		return this;
	}

	public JbelQuery fields(String... fieldNameStrings)
	{
		Expression exp = new AggregationExpression(fieldNameStrings);
		expressions.push(exp);
		return this;
	}

	public JbelQuery select(String... fieldNameStrings)
	{
		if (isAggregationExpression(expressions.peek()))	// Add more field selectors to the aggregate expression.
		{
			((AggregationExpression) expressions.peek()).attributes(fieldNameStrings);
		}
		else
		{
			fields(fieldNameStrings);
		}

		return this;
	}

//	public JbelQuery except(String... fieldNameStrings)
//	{
//		return this;
//	}

	public JbelQuery where(WhereClause whereClause)
	{
		expressions.push(new MetaExpression(expressions.pop(), whereClause));
		return this;
	}

	public JbelQuery orderBy(OrderByClause orderByClause)
	{
		expressions.push(orderByClause.asExpression());
		return this;
	}
	
	public Object evaluate(Object argument)
	{
		Iterator<Expression> i = expressions.iterator();
		Object results = argument;

		while (i.hasNext())
		{
			results = i.next().evaluate(results);
		}
		
		return results;
	}

	private boolean isAggregationExpression(Expression expression)
    {
	    return AggregationExpression.class.isAssignableFrom(expression.getClass());
    }
}
