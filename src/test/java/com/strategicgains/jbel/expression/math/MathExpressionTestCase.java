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

import java.math.BigDecimal;

import junit.framework.TestCase;

import com.strategicgains.jbel.expression.Expression;
import com.strategicgains.jbel.expression.LiteralExpression;
import com.strategicgains.jbel.expression.math.AddExpression;

public class MathExpressionTestCase
	extends TestCase
{
	public void testAddWithInteger()
	throws Exception
	{
		Expression expression = new AddExpression(new LiteralExpression(Integer.valueOf(3)),
			new LiteralExpression(Integer.valueOf(5)));
		assertEquals(8, ((BigDecimal)expression.evaluate(null)).intValue());
	}

	public void testAddWithDouble()
	throws Exception
	{
		Expression expression = new AddExpression(new LiteralExpression(new Double(3.14)), new LiteralExpression(new Double(5.625)));
		assertEquals(8.765, ((BigDecimal)expression.evaluate(null)).doubleValue(), 0.0001);
	}
}
