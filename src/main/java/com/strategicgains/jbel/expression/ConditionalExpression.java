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
 * An if-then-else expression.
 * 
 * A conditional expression is a ternary expression that evaluates the logical (predicate) expression and, if it evaluates to true, 
 * returns the evaluation value of the then expression.  Otherwise, it returns the evaluation value of the else expression.
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
	private Expression thenExpression;
	
	/**
	 * The expression that gets evaluated (and the value returned) if the predicate evaluates to false.
	 */
	private Expression elseExpression;

	/**
	 * Construct a new conditional expression instance.
	 */
	public ConditionalExpression(Predicate predicate, Expression thenExpression, Expression elseExpression)
	{
		this.predicate = predicate;
		this.thenExpression = thenExpression;
		this.elseExpression = elseExpression;
	}

	/**
	 * Return the false expression.
	 * 
	 * @return the false expression.
	 */
	public Expression getElseExpression()
	{
		return elseExpression;
	}

	/**
	 * Return the logical expression.
	 * 
	 * @return the logical expression.
	 */
	public Predicate getPredicate()
	{
		return predicate;
	}

	/**
	 * Return the true expression.
	 * 
	 * @return the true expression.
	 */
	public Expression getThenExpression()
	{
		return thenExpression;
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
			return thenExpression.evaluate(argument);
		}
		else
		{
			return elseExpression.evaluate(argument);
		}
	}

}
