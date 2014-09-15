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

public class EqualityPredicateTestCase
	extends TestCase
{
	public void testEvaluateTrue()
	throws Exception
	{
		Predicate predicate = new EqualityPredicate(new LiteralExpression("Todd Fredrich"), new LiteralExpression("Todd Fredrich"));
		assertTrue(predicate.test(null));
	}

	public void testEvaluateFalse()
	throws Exception
	{
		Predicate predicate = new EqualityPredicate(new LiteralExpression("Todd Fredrich"), new LiteralExpression("Todd Fredrick"));
		assertFalse(predicate.test(null));
	}

	public void testEvaluateTrueWithInteger()
	throws Exception
	{
		Predicate predicate = new EqualityPredicate(new LiteralExpression(Integer.valueOf(42)), new LiteralExpression(Integer.valueOf(42)));
		assertTrue(predicate.test(null));
	}

	public void testEvaluateFalseWithInteger()
	throws Exception
	{
		Predicate predicate = new EqualityPredicate(new LiteralExpression(Integer.valueOf(43)), new LiteralExpression(Integer.valueOf(42)));
		assertFalse(predicate.test(null));
	}
}
