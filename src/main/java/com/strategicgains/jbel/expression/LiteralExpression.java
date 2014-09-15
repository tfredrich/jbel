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
 * LiteralExpression represents a literal or constant value as an expression. Internally, the literal value
 * is held as an Object. Thus, the return value of evaluate() is an Object to match the signature of 
 * Expression.evaluate().
 * 
 * @author Todd Fredrich
 * @since Aug 22, 2005
 * @version $Revision: 1.4 $
 */
public class LiteralExpression
implements Expression
{
	/**
	 * The internal value of this literal expression.
	 */
	private Object literal;
	
	/**
	 * Create an integer literal.
	 * 
	 * @param value an integer value.
	 */
	public LiteralExpression(int value)
	{
		this(Integer.valueOf(value));
	}
	
	/**
	 * Create a long integer literal.
	 * 
	 * @param value a long value.
	 */
	public LiteralExpression(long value)
	{
		this(Long.valueOf(value));
	}
	
	/**
	 * Create a double literal.
	 * 
	 * @param value a double value.
	 */
	public LiteralExpression(double value)
	{
		this(new Double(value));
	}

	/**
	 * Create a generic, object-valued literal.
	 * 
	 * @param value an Object.
	 */
	public LiteralExpression(Object value)
	{
		literal = value;
	}
	
	/**
	 * Simply returns the value of the literal as an Object.
	 */
	public Object evaluate(Object argument)
	throws EvaluationException
	{
		return literal;
	}
}
