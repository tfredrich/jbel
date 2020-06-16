/*
	Copyright 2012 Strategic Gains, Inc.

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

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of several expressions that are evaluated as a group, with the results
 * of the first expression being passed to the second, etc.  The return value is
 * the value returned from the last expression after all expressions have been 
 * evaluated.
 * 
 * @author toddf
 * @since Dec 18, 2012
 */
public class MetaExpression
implements Expression
{
	private List<Expression> expressions = new ArrayList<Expression>();

	public MetaExpression(Expression... subExpressions)
    {
		super();
		expressions(subExpressions);
    }
	
	public MetaExpression(List<Expression> subExpressions)
	{
		super();
		expressions(subExpressions);
	}

	public void expressions(Expression... subExpressions)
    {
	    for (Expression expression : subExpressions)
		{
			expressions.add(expression);
		}
    }
	
	public void expressions(List<Expression> subExpressions)
	{
		expressions.addAll(subExpressions);
	}

	@Override
	public Object evaluate(Object argument)
	{
		Object results = argument;

		for (Expression expression : expressions)
		{
			results = expression.evaluate(results);
		}
		
		return results;
	}

}
