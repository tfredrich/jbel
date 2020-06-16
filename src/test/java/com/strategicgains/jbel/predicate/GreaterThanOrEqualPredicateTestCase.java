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

public class GreaterThanOrEqualPredicateTestCase
	extends TestCase
{
	public void testEvaluateLessThan()
	throws Exception
	{
		Predicate predicate = new GreaterThanOrEqualPredicate(new LiteralExpression("Aaaaaa"), new LiteralExpression("Bbbbbb"));
		assertFalse((predicate.test(null)));
	}

	public void testEvaluateGreaterThan()
	throws Exception
	{
		Predicate predicate = new GreaterThanOrEqualPredicate(new LiteralExpression("Aaaaaa"), new LiteralExpression("Aaaaa"));
		assertTrue(predicate.test(null));
	}

	public void testEvaluateEquals()
	throws Exception
	{
		Predicate predicate = new GreaterThanOrEqualPredicate(new LiteralExpression("Aaaaaa"), new LiteralExpression("Aaaaaa"));
		assertTrue(predicate.test(null));
	}

	public void testEvaluateLessThanWithInteger()
	throws Exception
	{
		Predicate predicate = new GreaterThanOrEqualPredicate(new LiteralExpression(Integer.valueOf(41)), new LiteralExpression(Integer.valueOf(42)));
		assertFalse(predicate.test(null));
	}

	public void testEvaluateGreaterThanWithInteger()
	throws Exception
	{
		Predicate predicate = new GreaterThanOrEqualPredicate(new LiteralExpression(Integer.valueOf(43)), new LiteralExpression(Integer.valueOf(42)));
		assertTrue(predicate.test(null));
	}

	public void testEvaluateEqualsWithInteger()
	throws Exception
	{
		Predicate predicate = new GreaterThanOrEqualPredicate(new LiteralExpression(Integer.valueOf(42)), new LiteralExpression(Integer.valueOf(42)));
		assertTrue(predicate.test(null));
	}
}
