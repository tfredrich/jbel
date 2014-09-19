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
 * PredicateExpressionBuilder is used to construct expressions usable in Expressions.select() in in-fix notation.
 * 
 * @author Todd Fredrich
 * @since Aug 26, 2005
 * @version $Revision: 1.17 $
 */
public class PredicateBuilder
extends AbstractExpressionBuilder
{
	public PredicateBuilder(Expression expression)
	{
		this();
		setExpression(expression);
	}

	public PredicateBuilder()
	{
		super();
	}
	
	// BUILDER METHODS

	public PredicateBuilder field(String name)
	{
		PredicateBuilder result = this;
		
		if (getExpression() != null)
		{
			if (getExpression() instanceof AccessorExpression)
			{
				((AccessorExpression) result.getExpression()).attribute(name);
			}
			else
			{
				result = new PredicateBuilder();
				result.setExpression(new AccessorExpression(name));
			}
		}
		else
		{
			result.setExpression(new AccessorExpression(name));
		}

		return result;
	}
	
	public PredicateBuilder fields(String... names)
	{
		return this;
	}

	public PredicateBuilder index(int index)
	{
		return this;
	}

	public PredicateBuilder between(int low, int high)
	{
		return between(new LiteralExpression(low), new LiteralExpression(high));
	}
	
	public PredicateBuilder between(double low, double high)
	{
		return between(new LiteralExpression(low), new LiteralExpression(high));
	}
	
	public PredicateBuilder between(long low, long high)
	{
		return between(new LiteralExpression(low), new LiteralExpression(high));
	}
	
	public PredicateBuilder between(Object low, Object high)
	{
		return between(new LiteralExpression(low), new LiteralExpression(high));
	}
	
	public PredicateBuilder between(Expression low, Expression high)
	{
		setExpression(new BetweenPredicate(getExpression(), low, high));
		return this;
	}
	
	public PredicateBuilder equalTo(int value)
	{
		return equalTo(new LiteralExpression(value));
	}
	
	public PredicateBuilder equalTo(double value)
	{
		return equalTo(new LiteralExpression(value));
	}
	
	public PredicateBuilder equalTo(long value)
	{
		return equalTo(new LiteralExpression(value));
	}
	
	public PredicateBuilder equalTo(Object value)
	{
		return equalTo(new LiteralExpression(value));
	}
	
	public PredicateBuilder equalTo(Expression value)
	{
		setExpression(new EqualityPredicate(getExpression(), value));
		return this;
	}
	
	public PredicateBuilder lessThan(int value)
	{
		return lessThan(new LiteralExpression(value));
	}
	
	public PredicateBuilder lessThan(double value)
	{
		return lessThan(new LiteralExpression(value));
	}
	
	public PredicateBuilder lessThan(long value)
	{
		return lessThan(new LiteralExpression(value));
	}
	
	public PredicateBuilder lessThan(Expression value)
	{
		setExpression(new LessThanPredicate(getExpression(), value));
		return this;
	}
	
	public PredicateBuilder lessThanOrEqual(int value)
	{
		return lessThanOrEqual(new LiteralExpression(value));
	}
	
	public PredicateBuilder lessThanOrEqual(double value)
	{
		return lessThanOrEqual(new LiteralExpression(value));
	}
	
	public PredicateBuilder lessThanOrEqual(long value)
	{
		return lessThanOrEqual(new LiteralExpression(value));
	}
	
	public PredicateBuilder lessThanOrEqual(Expression value)
	{
		setExpression(new LessThanOrEqualPredicate(getExpression(), value));
		return this;
	}
	
	public PredicateBuilder greaterThan(int value)
	{
		return greaterThan(new LiteralExpression(value));
	}
	
	public PredicateBuilder greaterThan(double value)
	{
		return greaterThan(new LiteralExpression(value));
	}
	
	public PredicateBuilder greaterThan(long value)
	{
		return greaterThan(new LiteralExpression(value));
	}
	
	public PredicateBuilder greaterThan(Expression value)
	{
		setExpression(new GreaterThanPredicate(getExpression(), value));
		return this;
	}
	
	public PredicateBuilder greaterThanOrEqual(int value)
	{
		return greaterThanOrEqual(new LiteralExpression(value));
	}
	
	public PredicateBuilder greaterThanOrEqual(double value)
	{
		return greaterThanOrEqual(new LiteralExpression(value));
	}
	
	public PredicateBuilder greaterThanOrEqual(long value)
	{
		return greaterThanOrEqual(new LiteralExpression(value));
	}
	
	public PredicateBuilder greaterThanOrEqual(Expression value)
	{
		setExpression(new GreaterThanOrEqualPredicate(getExpression(), value));
		return this;
	}

	public PredicateBuilder isIn(Object[] values)
	{
		return isIn(Arrays.asList(values));
	}

	public PredicateBuilder isIn(List<?> values)
	{
		setExpression(new IsInPredicate(getExpression(), new LiteralExpression(values)));
		return this;
	}
	
	public PredicateBuilder toUpper(PredicateBuilder builder)
	{
		setExpression(new ToUpperExpression(builder.getExpression()));
		return this;
	}
	
	public PredicateBuilder toLower(PredicateBuilder builder)
	{
		setExpression(new ToLowerExpression(builder.getExpression()));
		return this;
	}

	// LOGICALS

	public PredicateBuilder and(PredicateBuilder subExpressionBuilder)
	{
		setExpression(new AndPredicate(getExpression(), subExpressionBuilder.getExpression()));
		return this;
	}

	public PredicateBuilder or(PredicateBuilder subExpressionBuilder)
	{
		setExpression(new OrPredicate(getExpression(), subExpressionBuilder.getExpression()));
		return this;
	}
}
