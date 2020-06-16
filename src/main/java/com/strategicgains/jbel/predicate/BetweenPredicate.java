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

/**
 * BetweenPredicate is a meta-predicate that represents a sub-expression where: the results
 * of the accessor are greater-than-or-equal to the lowerBound and less-than-or-equal to 
 * the upperBound.
 * 
 * @author Todd Fredrich
 * @since Jan 26, 2006
 * @version $Revision: 1.5 $
 */
public class BetweenPredicate
extends UnaryPredicate
{
	public BetweenPredicate(Expression accessor, Expression lowerBound, Expression upperBound)
	{
		super(new AndPredicate(new GreaterThanOrEqualPredicate(accessor, lowerBound),
			new LessThanOrEqualPredicate(accessor, upperBound)));
	}

	protected Object evaluateResults(Object object)
		throws EvaluationException
	{
		return object;
	}	
}
