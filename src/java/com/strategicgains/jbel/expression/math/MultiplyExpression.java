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
package com.strategicgains.jbel.expression.math;

import com.strategicgains.jbel.exception.EvaluationException;
import com.strategicgains.jbel.expression.BinaryExpression;
import com.strategicgains.jbel.expression.Expression;
import com.strategicgains.jbel.function.math.MathFunctions;

public class MultiplyExpression
extends BinaryExpression
{
	public MultiplyExpression(Expression leftExpression, Expression rightExpression)
	{
		super(leftExpression, rightExpression);
	}

	protected Object evaluateResults(Object object1, Object object2)
		throws EvaluationException
	{
		return MathFunctions.MULTIPLY.perform(object1, object2);
	}
}
