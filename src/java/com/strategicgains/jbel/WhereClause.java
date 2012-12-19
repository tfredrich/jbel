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

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.strategicgains.jbel.exception.ExpressionException;
import com.strategicgains.jbel.expression.AccessorExpression;
import com.strategicgains.jbel.expression.Expression;
import com.strategicgains.jbel.expression.LiteralExpression;
import com.strategicgains.jbel.predicate.AbstractPredicate;
import com.strategicgains.jbel.predicate.BetweenPredicate;
import com.strategicgains.jbel.predicate.EqualityPredicate;
import com.strategicgains.jbel.predicate.GreaterThanOrEqualPredicate;
import com.strategicgains.jbel.predicate.GreaterThanPredicate;
import com.strategicgains.jbel.predicate.IsInPredicate;
import com.strategicgains.jbel.predicate.LessThanOrEqualPredicate;
import com.strategicgains.jbel.predicate.LessThanPredicate;
import com.strategicgains.jbel.predicate.Predicate;
import com.strategicgains.jbel.util.ArrayStack;
import com.strategicgains.jbel.util.Stack;


/**
 * @author toddf
 * @since Dec 18, 2012
 */
public class WhereClause
extends AbstractPredicate
{
	private Expression leftOperand;
	private Stack<Predicate> expressions;

	public WhereClause()
    {
		expressions = new ArrayStack<Predicate>();
    }
	
	public WhereClause field(String fieldNameString)
	{
		leftOperand = new AccessorExpression(fieldNameString);
		return this;
	}

	public WhereClause equalTo(Object value)
	{
		if (leftOperand == null)
		{
			throw new ExpressionException("Left operand required");
		}

		expressions.push(new EqualityPredicate(leftOperand, createOperand(value)));
		leftOperand = null;
		return this;
	}

	public WhereClause isIn(Object... values)
	{
		return isIn(Arrays.asList(values));
	}

	public WhereClause isIn(Collection<?> values)
	{
		if (leftOperand == null)
		{
			throw new ExpressionException("Left operand required");
		}

		expressions.push(new IsInPredicate(leftOperand, new LiteralExpression(values)));
		leftOperand = null;
		return this;
	}

	public WhereClause lessThan(Object value)
	{
		if (leftOperand == null)
		{
			throw new ExpressionException("Left operand required");
		}

		expressions.push(new LessThanPredicate(leftOperand, createOperand(value)));
		leftOperand = null;
		return this;
	}

	public WhereClause lessThanOrEqual(Object value)
	{
		if (leftOperand == null)
		{
			throw new ExpressionException("Left operand required");
		}

		expressions.push(new LessThanOrEqualPredicate(leftOperand, createOperand(value)));
		leftOperand = null;
		return this;
	}

	public WhereClause greaterThan(Object value)
	{
		if (leftOperand == null)
		{
			throw new ExpressionException("Left operand required");
		}

		expressions.push(new GreaterThanPredicate(leftOperand, createOperand(value)));
		leftOperand = null;
		return this;
	}

	public WhereClause greaterThanOrEqual(Object value)
	{
		if (leftOperand == null)
		{
			throw new ExpressionException("Left operand required");
		}

		expressions.push(new GreaterThanOrEqualPredicate(leftOperand, createOperand(value)));
		leftOperand = null;
		return this;
	}

	public WhereClause between(Object lowerBound, Object upperBound)
	{
		if (leftOperand == null)
		{
			throw new ExpressionException("Left operand required");
		}

		Expression lbe = createOperand(lowerBound);
		Expression ube = createOperand(upperBound);
		expressions.push(new BetweenPredicate(leftOperand, lbe, ube));
		leftOperand = null;
		return this;
	}

	@Override
	public Object evaluate(Object argument)
	{
		if (Collection.class.isAssignableFrom(argument.getClass()))
		{
			return CollectionUtils.select((Collection<?>) argument, this);
		}
		else
		{
			return super.evaluate(argument);
		}
	}

	@Override
    public boolean test(Object argument)
    {
		Iterator<Predicate> i = expressions.iterator();
		
		while(i.hasNext())
		{
			if (!i.next().test(argument)) return false;
		}
		
		return true;
    }

	private Expression createOperand(Object value)
    {
		if (Expression.class.isAssignableFrom(value.getClass()))
		{
			return (Expression) value;
		}
		return new LiteralExpression(value);
    }
}
