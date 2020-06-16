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
package com.strategicgains.jbel.builder;

import com.strategicgains.jbel.expression.Expression;

/**
 * An AbstractExpressionBuilder is the root object for specialized expression builders.  Essentially,
 * its only function is to contain the underlying expression that is being built.
 * 
 * @author Todd Fredrich
 * @since Aug 26, 2005
 * @version $Revision: 1.9 $
 */
public abstract class AbstractExpressionBuilder
{
	private Expression expression;
	
	public AbstractExpressionBuilder()
	{
		super();
	}

	public Expression build()
	{
		return expression;
	}
	
	protected void setExpression(Expression expression)
	{
		this.expression = expression;
	}
}
