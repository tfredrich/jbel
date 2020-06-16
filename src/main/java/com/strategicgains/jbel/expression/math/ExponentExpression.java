package com.strategicgains.jbel.expression.math;

import com.strategicgains.jbel.exception.EvaluationException;
import com.strategicgains.jbel.expression.Expression;
import com.strategicgains.jbel.expression.UnaryExpression;
import com.strategicgains.jbel.function.math.MathFunctions;

public class ExponentExpression
extends UnaryExpression
{
	public ExponentExpression(Expression expression)
	{
		super(expression);
	}

	protected Object evaluateResults(Object object)
	throws EvaluationException
	{
		return MathFunctions.EXP.perform(object);
	}

}
