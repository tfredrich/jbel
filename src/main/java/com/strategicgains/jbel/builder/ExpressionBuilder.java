/*
	Copyright 2014 Strategic Gains, Inc.

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
 * ExpressionBuilder is used to construct expressions using a DSL.
 * 
 * @author Todd Fredrich
 * @since Sep 15, 2014
 */
public class ExpressionBuilder
extends AbstractExpressionBuilder
{
	public ExpressionBuilder()
	{
		super();
	}
	
	// BUILDER METHODS

	public ExpressionBuilder field(String name)
	{
		ExpressionBuilder result = this;
		
		if (build() != null)
		{
			if (build() instanceof AccessorExpression)
			{
				((AccessorExpression) result.build()).attribute(name);
			}
			else
			{
				result = new ExpressionBuilder();
				result.setExpression(new AccessorExpression(name));
			}
		}
		else
		{
			result.setExpression(new AccessorExpression(name));
		}

		return result;
	}

	public ExpressionBuilder between(int low, int high)
	{
		return between(new LiteralExpression(low), new LiteralExpression(high));
	}
	
	public ExpressionBuilder between(double low, double high)
	{
		return between(new LiteralExpression(low), new LiteralExpression(high));
	}
	
	public ExpressionBuilder between(long low, long high)
	{
		return between(new LiteralExpression(low), new LiteralExpression(high));
	}
	
	public ExpressionBuilder between(Object low, Object high)
	{
		return between(new LiteralExpression(low), new LiteralExpression(high));
	}
	
	public ExpressionBuilder between(Expression low, Expression high)
	{
		setExpression(new BetweenPredicate(build(), low, high));
		return this;
	}
	
	public ExpressionBuilder equalTo(int value)
	{
		return equalTo(new LiteralExpression(value));
	}
	
	public ExpressionBuilder equalTo(double value)
	{
		return equalTo(new LiteralExpression(value));
	}
	
	public ExpressionBuilder equalTo(long value)
	{
		return equalTo(new LiteralExpression(value));
	}
	
	public ExpressionBuilder equalTo(Object value)
	{
		return equalTo(new LiteralExpression(value));
	}
	
	public ExpressionBuilder equalTo(Expression value)
	{
		setExpression(new EqualityPredicate(build(), value));
		return this;
	}
	
	public ExpressionBuilder lessThan(int value)
	{
		return lessThan(new LiteralExpression(value));
	}
	
	public ExpressionBuilder lessThan(double value)
	{
		return lessThan(new LiteralExpression(value));
	}
	
	public ExpressionBuilder lessThan(long value)
	{
		return lessThan(new LiteralExpression(value));
	}
	
	public ExpressionBuilder lessThan(Expression value)
	{
		setExpression(new LessThanPredicate(build(), value));
		return this;
	}
	
	public ExpressionBuilder lessThanOrEqual(int value)
	{
		return lessThanOrEqual(new LiteralExpression(value));
	}
	
	public ExpressionBuilder lessThanOrEqual(double value)
	{
		return lessThanOrEqual(new LiteralExpression(value));
	}
	
	public ExpressionBuilder lessThanOrEqual(long value)
	{
		return lessThanOrEqual(new LiteralExpression(value));
	}
	
	public ExpressionBuilder lessThanOrEqual(Expression value)
	{
		setExpression(new LessThanOrEqualPredicate(build(), value));
		return this;
	}
	
	public ExpressionBuilder greaterThan(int value)
	{
		return greaterThan(new LiteralExpression(value));
	}
	
	public ExpressionBuilder greaterThan(double value)
	{
		return greaterThan(new LiteralExpression(value));
	}
	
	public ExpressionBuilder greaterThan(long value)
	{
		return greaterThan(new LiteralExpression(value));
	}
	
	public ExpressionBuilder greaterThan(Expression value)
	{
		setExpression(new GreaterThanPredicate(build(), value));
		return this;
	}
	
	public ExpressionBuilder greaterThanOrEqual(int value)
	{
		return greaterThanOrEqual(new LiteralExpression(value));
	}
	
	public ExpressionBuilder greaterThanOrEqual(double value)
	{
		return greaterThanOrEqual(new LiteralExpression(value));
	}
	
	public ExpressionBuilder greaterThanOrEqual(long value)
	{
		return greaterThanOrEqual(new LiteralExpression(value));
	}
	
	public ExpressionBuilder greaterThanOrEqual(Expression value)
	{
		setExpression(new GreaterThanOrEqualPredicate(build(), value));
		return this;
	}

	public ExpressionBuilder isIn(Object[] values)
	{
		return isIn(Arrays.asList(values));
	}

	public ExpressionBuilder isIn(List<?> values)
	{
		setExpression(new IsInPredicate(build(), new LiteralExpression(values)));
		return this;
	}
	
	public ExpressionBuilder toUpper(ExpressionBuilder builder)
	{
		setExpression(new ToUpperExpression(builder.build()));
		return this;
	}
	
	public ExpressionBuilder toLower(ExpressionBuilder builder)
	{
		setExpression(new ToLowerExpression(builder.build()));
		return this;
	}

	// LOGICALS

	public ExpressionBuilder and(ExpressionBuilder subExpressionBuilder)
	{
		setExpression(new AndPredicate(build(), subExpressionBuilder.build()));
		return this;
	}

	public ExpressionBuilder or(ExpressionBuilder subExpressionBuilder)
	{
		setExpression(new OrPredicate(build(), subExpressionBuilder.build()));
		return this;
	}
}
