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

import com.strategicgains.jbel.exception.EvaluationException;
import com.strategicgains.jbel.predicate.Predicate;


/**
 * A contional expression is a ternary expression that evaluates the logical expression and, if it evaluates to true, 
 * returns the evaluation value of the true expression.  Otherwise, it returns the evaluation value of the false expression.
 * 
 * @author Todd Fredrich
 * @since Aug 22, 2005
 */
public class ConditionalExpression
implements Expression
{
	/**
	 * The expression to evaluate that determines which of the other expressions to evaluate.
	 */
	private Predicate predicate;
	
	/**
	 * The expression that gets evaluated (and the value returned) if the predicate evaluates to true.
	 */
	private Expression trueExpression;
	
	/**
	 * The expression that gets evaluated (and the value returned) if the predicate evaluates to false.
	 */
	private Expression falseExpression;

	/**
	 * Construct a new conditional expression instance.
	 */
	public ConditionalExpression(Predicate predicate, Expression trueExpression, Expression falseExpression)
	{
		this.predicate = predicate;
		this.trueExpression = trueExpression;
		this.falseExpression = falseExpression;
	}

	/**
	 * Return the false expression.
	 * 
	 * @return the false expression.
	 */
	public Expression getFalseExpression()
	{
		return falseExpression;
	}

	/**
	 * Return the logical expression.
	 * 
	 * @return the logical expression.
	 */
	public Expression getPredicate()
	{
		return predicate;
	}

	/**
	 * Return the true expression.
	 * 
	 * @return the true expression.
	 */
	public Expression getTrueExpression()
	{
		return trueExpression;
	}

	/**
	 * Evaluate the logical expression for the argument and, if it evaluates to true, return the evaluation value of
	 * the true expression.  Otherwise return the value of the false expression.
	 * 
	 * @see com.strategicgains.jbel.function.UnaryFunction#perform(java.lang.Object)
	 */
	public Object evaluate(Object argument)
	throws EvaluationException
	{
		if (predicate.test(argument))
		{
			return trueExpression.evaluate(argument);
		}
		else
		{
			return falseExpression.evaluate(argument);
		}
	}
}
