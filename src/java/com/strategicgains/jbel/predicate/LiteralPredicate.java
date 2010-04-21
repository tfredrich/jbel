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
import com.strategicgains.jbel.expression.AbstractExpression;

/**
 * LiteralPredicate represents a literal or constant value as an expression that returns only true or false.  The 
 * return value of evaluate() is an Object to match the signature of Expression.evaluate().
 * 
 * @author Todd Fredrich
 * @since Jan 26, 2006
 * @version $Revision: 1.3 $
 */
public class LiteralPredicate
	extends AbstractExpression
	implements Predicate
{
	/**
	 * The internal value of this literal expression.
	 */
	private boolean predicate;
	
	/**
	 * Create a boolean literal.
	 * 
	 * @param value a boolean value
	 */
	public LiteralPredicate(boolean value)
	{
		predicate = value;
	}

	/**
	 * Create a Boolean-valued literal.
	 * 
	 * @param value a Boolean.  Never may be null.
	 */
	public LiteralPredicate(Boolean value)
	{
		this(value.booleanValue());
	}
	
	/**
	 * Simply returns the value of the literal as an Object.
	 */
	public Object evaluate(Object argument)
		throws EvaluationException
	{
		return (predicate ? Boolean.TRUE : Boolean.FALSE);
	}

	public boolean test(Object object)
		throws EvaluationException
	{
		return predicate;
	}
}
