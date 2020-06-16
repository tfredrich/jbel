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

import com.strategicgains.jbel.expression.Expression;

public class OrPredicateTestCase
extends TestCase
{
	public void testEvaluateTrue()
	throws Exception
	{
		Expression expression = new OrPredicate(LiteralPredicate.TRUE, LiteralPredicate.TRUE);
		assertTrue(((Boolean)expression.evaluate(null)).booleanValue());
	}

	public void testEvaluateTrueWithOneFalse()
	throws Exception
	{
		Expression expression = new OrPredicate(LiteralPredicate.TRUE, LiteralPredicate.FALSE);
		assertTrue(((Boolean)expression.evaluate(null)).booleanValue());
	}

	public void testEvaluateTrueWithOneFalseReverse()
	throws Exception
	{
		Expression expression = new OrPredicate(LiteralPredicate.FALSE, LiteralPredicate.TRUE);
		assertTrue(((Boolean)expression.evaluate(null)).booleanValue());
	}

	public void testEvaluateFalse()
	throws Exception
	{
		Expression expression = new OrPredicate(LiteralPredicate.FALSE, LiteralPredicate.FALSE);
		assertFalse(((Boolean)expression.evaluate(null)).booleanValue());
	}
}
