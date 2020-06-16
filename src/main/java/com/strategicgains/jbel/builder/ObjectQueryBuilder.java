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
package com.strategicgains.jbel.builder;

import com.strategicgains.jbel.exception.ExpressionException;
import com.strategicgains.jbel.expression.AccessorExpression;
import com.strategicgains.jbel.expression.AggregationExpression;
import com.strategicgains.jbel.expression.Expression;

/**
 * SelectExpressionBuilder is used to construct expressions usable in Expressions.select() in in-fix notation.
 * 
 * @author Todd Fredrich
 * @since Aug 26, 2005
 * @version $Revision: 1.17 $
 */
public class ObjectQueryBuilder
extends AbstractExpressionBuilder
{
	public ObjectQueryBuilder()
	{
		super();
	}
	
	// BUILDER METHODS

	/**
	 * Supported forms of 'name' are "name" "name.name.name..." "name[0]" "name[0].name" "name.name[0]"
	 * 
	 * @param name
	 * @return
	 */
	public ObjectQueryBuilder field(String name)
	{
		if (build() != null)
		{
			if (build() instanceof AccessorExpression)
			{
				((AccessorExpression) build()).attribute(name);
			}
			else
			{
				setExpression(new AccessorExpression(name));
			}
		}
		else
		{
			setExpression(new AccessorExpression(name));
		}

		return this;
	}
	
	public ObjectQueryBuilder fields(String... names)
	{
		if (build() != null)
		{
			if (build() instanceof AggregationExpression)
			{
				((AggregationExpression) build()).attributes(names);
			}
			else
			{
				setExpression(new AggregationExpression(names));
			}
		}
		else
		{
			setExpression(new AggregationExpression(names));
		}

		return this;
	}

	public ObjectQueryBuilder element(int index)
	{
		Expression exp = build();
		
		if (AccessorExpression.class.isAssignableFrom(exp.getClass()))
		{
			((AccessorExpression) exp).addIndex(index);
		}
		else
		{
			throw new ExpressionException("Previous expression must be an AccessorExpression");
		}

		return this;
	}

	// Aggregation Operations

//	public ObjectQueryBuilder and(ObjectQueryBuilder subExpressionBuilder)
//	{
//		setExpression(new AndExpression(getExpression(), subExpressionBuilder.getExpression()));
//		return this;
//	}
}
