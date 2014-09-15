/*
	Copyright 2006 Strategic Gains, Inc.

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
import com.strategicgains.jbel.expression.UnaryExpression;

/**
 * A UnaryPredicate is an Expression that evaluates over a single sub-expression, returning true or false.  
 * While the evaluate() method returns an instance of type Object, the test() method returns type boolean.  
 * 
 * @author Todd Fredrich
 * @since Jan 26, 2006
 * @version $Revision: 1.5 $
 */
public abstract class UnaryPredicate
extends UnaryExpression
implements Predicate
{
	public UnaryPredicate(Expression expression)
	{
		super(expression);
	}
	
	public boolean test(Object object)
		throws EvaluationException
	{
		return ((Boolean) evaluate(object)).booleanValue();
	}
}
