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

import junit.framework.TestCase;

import com.strategicgains.jbel.expression.LiteralExpression;

public class BetweenPredicateTestCase
	extends TestCase
{
	public void testEvaluateTrue()
	throws Exception
	{
		Predicate expression = new BetweenPredicate(new LiteralExpression(5), new LiteralExpression(1), new LiteralExpression(10));
		assertTrue(expression.test(null));
	}

	public void testEvaluateAboveRange()
	throws Exception
	{
		Predicate predicate = new BetweenPredicate(new LiteralExpression(11), new LiteralExpression(1), new LiteralExpression(10));
		assertFalse(predicate.test(null));
	}

	public void testEvaluateBelowRange()
	throws Exception
	{
		Predicate predicate= new BetweenPredicate(new LiteralExpression(0), new LiteralExpression(1), new LiteralExpression(10));
		assertFalse(predicate.test(null));
	}
}
