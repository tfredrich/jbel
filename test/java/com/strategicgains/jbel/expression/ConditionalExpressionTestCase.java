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

import junit.framework.TestCase;

import com.strategicgains.jbel.builder.SelectExpressionBuilder;
import com.strategicgains.jbel.domain.TestObject;
import com.strategicgains.jbel.predicate.BetweenPredicate;
import com.strategicgains.jbel.predicate.Predicate;

public class ConditionalExpressionTestCase
	extends TestCase
{
	private Expression expression = null;

	public void setUp()
	{
//		SelectExpressionBuilder select1 = new SelectExpressionBuilder();
//		select1.attribute("intValue").between(3, 5);
//		Predicate predicate = select1.getExpression();

		Predicate predicate = new BetweenPredicate(new AccessorExpression("intValue"), 
			new LiteralExpression(3), 
			new LiteralExpression(5));
		
		SelectExpressionBuilder select2 = new SelectExpressionBuilder();
		select2.attribute("stringValue");
		Expression trueExpr = select2.getExpression();

		Expression falseExpr = new LiteralExpression("N/A");
		expression = new ConditionalExpression(predicate, trueExpr, falseExpr);
	}
	
	public void testConditionalWithTrue()
	throws Exception
	{
		assertEquals("five", expression.evaluate(new TestObject("nambre", 5, "five")));
		assertEquals("three", expression.evaluate(new TestObject("nambre", 3, "three")));
		assertEquals("four", expression.evaluate(new TestObject("nambre", 4, "four")));
	}
	
	public void testConditionalWithFalse()
	throws Exception
	{
		assertEquals("N/A", expression.evaluate(new TestObject("nambre", 6, "six")));
		assertEquals("N/A", expression.evaluate(new TestObject("nambre", 2, "two")));
	}
}
