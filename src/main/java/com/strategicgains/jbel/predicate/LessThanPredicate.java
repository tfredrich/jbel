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
package com.strategicgains.jbel.predicate;

import com.strategicgains.jbel.exception.EvaluationException;
import com.strategicgains.jbel.expression.Expression;

public class LessThanPredicate
	extends AbstractBinaryPredicate
{
	public LessThanPredicate(Expression leftExpression, Expression rightExpression)
	{
		super(leftExpression, rightExpression);
	}

	public Object evaluateResults(Object obj1, Object obj2)
	throws EvaluationException 
	{
		return Boolean.valueOf(compare(obj1, obj2) < 0);
	}
}
