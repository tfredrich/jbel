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

/**
 * A BinaryExpression has two child expressions, or operands.
 * 
 * @author Todd Fredrich
 * @since Aug 22, 2005
 * @version $Revision: 1.6 $
 */
public abstract class BinaryExpression
	extends AbstractExpression
{
	private Expression leftOperand;
	private Expression rightOperand;
	
	public BinaryExpression(Expression leftExpression, Expression rightExpression)
	{
		this.leftOperand = leftExpression;
		this.rightOperand = rightExpression;
	}
	
	public Expression getLeftOperand()
	{
		return leftOperand;
	}

	public Expression getRightOperand()
	{
		return rightOperand;
	}
	
	public void setRightOperand(Expression rightExpression)
	{
		this.rightOperand = rightExpression;
	}
	
	public Object evaluate(Object argument)
	throws EvaluationException
	{
		Object leftResult = leftOperand.evaluate(argument);
		Object rightResult = rightOperand.evaluate(argument);
		return evaluateResults(leftResult, rightResult);
	}

	protected abstract Object evaluateResults(Object object1, Object object2)
	throws EvaluationException;
}
