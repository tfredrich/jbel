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

import java.util.Arrays;
import java.util.List;

import com.strategicgains.jbel.expression.AccessorExpression;
import com.strategicgains.jbel.expression.Expression;
import com.strategicgains.jbel.expression.LiteralExpression;
import com.strategicgains.jbel.expression.ToLowerExpression;
import com.strategicgains.jbel.expression.ToUpperExpression;
import com.strategicgains.jbel.predicate.AndPredicate;
import com.strategicgains.jbel.predicate.BetweenPredicate;
import com.strategicgains.jbel.predicate.EqualityPredicate;
import com.strategicgains.jbel.predicate.GreaterThanOrEqualPredicate;
import com.strategicgains.jbel.predicate.GreaterThanPredicate;
import com.strategicgains.jbel.predicate.IsInPredicate;
import com.strategicgains.jbel.predicate.LessThanOrEqualPredicate;
import com.strategicgains.jbel.predicate.LessThanPredicate;
import com.strategicgains.jbel.predicate.OrPredicate;

/**
 * SelectExpressionBuilder is used to construct expressions usable in Expressions.select() in in-fix notation.
 * 
 * @author Todd Fredrich
 * @since Aug 26, 2005
 * @version $Revision: 1.17 $
 */
public class SelectExpressionBuilder
extends AbstractExpressionBuilder
{
	public SelectExpressionBuilder()
	{
		super();
	}
	
	// BUILDER METHODS

	public SelectExpressionBuilder field(String name)
	{
		SelectExpressionBuilder result = this;
		
		if (build() != null)
		{
			if (build() instanceof AccessorExpression)
			{
				((AccessorExpression) result.build()).attribute(name);
			}
			else
			{
				result = new SelectExpressionBuilder();
				result.setExpression(new AccessorExpression(name));
			}
		}
		else
		{
			result.setExpression(new AccessorExpression(name));
		}

		return result;
	}
	
	public SelectExpressionBuilder fields(String... names)
	{
		return this;
	}

	public SelectExpressionBuilder index(int index)
	{
		return this;
	}

	public SelectExpressionBuilder between(int low, int high)
	{
		return between(new LiteralExpression(low), new LiteralExpression(high));
	}
	
	public SelectExpressionBuilder between(double low, double high)
	{
		return between(new LiteralExpression(low), new LiteralExpression(high));
	}
	
	public SelectExpressionBuilder between(long low, long high)
	{
		return between(new LiteralExpression(low), new LiteralExpression(high));
	}
	
	public SelectExpressionBuilder between(Object low, Object high)
	{
		return between(new LiteralExpression(low), new LiteralExpression(high));
	}
	
	public SelectExpressionBuilder between(Expression low, Expression high)
	{
		setExpression(new BetweenPredicate(build(), low, high));
		return this;
	}
	
	public SelectExpressionBuilder equalTo(int value)
	{
		return equalTo(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder equalTo(double value)
	{
		return equalTo(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder equalTo(long value)
	{
		return equalTo(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder equalTo(Object value)
	{
		return equalTo(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder equalTo(Expression value)
	{
		setExpression(new EqualityPredicate(build(), value));
		return this;
	}
	
	public SelectExpressionBuilder lessThan(int value)
	{
		return lessThan(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder lessThan(double value)
	{
		return lessThan(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder lessThan(long value)
	{
		return lessThan(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder lessThan(Expression value)
	{
		setExpression(new LessThanPredicate(build(), value));
		return this;
	}
	
	public SelectExpressionBuilder lessThanOrEqual(int value)
	{
		return lessThanOrEqual(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder lessThanOrEqual(double value)
	{
		return lessThanOrEqual(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder lessThanOrEqual(long value)
	{
		return lessThanOrEqual(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder lessThanOrEqual(Expression value)
	{
		setExpression(new LessThanOrEqualPredicate(build(), value));
		return this;
	}
	
	public SelectExpressionBuilder greaterThan(int value)
	{
		return greaterThan(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder greaterThan(double value)
	{
		return greaterThan(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder greaterThan(long value)
	{
		return greaterThan(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder greaterThan(Expression value)
	{
		setExpression(new GreaterThanPredicate(build(), value));
		return this;
	}
	
	public SelectExpressionBuilder greaterThanOrEqual(int value)
	{
		return greaterThanOrEqual(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder greaterThanOrEqual(double value)
	{
		return greaterThanOrEqual(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder greaterThanOrEqual(long value)
	{
		return greaterThanOrEqual(new LiteralExpression(value));
	}
	
	public SelectExpressionBuilder greaterThanOrEqual(Expression value)
	{
		setExpression(new GreaterThanOrEqualPredicate(build(), value));
		return this;
	}

	public SelectExpressionBuilder isIn(Object[] values)
	{
		return isIn(Arrays.asList(values));
	}

	public SelectExpressionBuilder isIn(List<?> values)
	{
		setExpression(new IsInPredicate(build(), new LiteralExpression(values)));
		return this;
	}
	
	public SelectExpressionBuilder toUpper(SelectExpressionBuilder builder)
	{
		setExpression(new ToUpperExpression(builder.build()));
		return this;
	}
	
	public SelectExpressionBuilder toLower(SelectExpressionBuilder builder)
	{
		setExpression(new ToLowerExpression(builder.build()));
		return this;
	}

	// LOGICALS

	public SelectExpressionBuilder and(SelectExpressionBuilder subExpressionBuilder)
	{
		setExpression(new AndPredicate(build(), subExpressionBuilder.build()));
		return this;
	}

	public SelectExpressionBuilder or(SelectExpressionBuilder subExpressionBuilder)
	{
		setExpression(new OrPredicate(build(), subExpressionBuilder.build()));
		return this;
	}
}
