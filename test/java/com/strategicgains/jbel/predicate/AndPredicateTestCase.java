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

import com.strategicgains.jbel.Literals;
import com.strategicgains.jbel.expression.Expression;

public class AndPredicateTestCase
extends TestCase
{
	public void testEvaluateTrue()
	throws Exception
	{
		Expression expression = new AndPredicate(Literals.TRUE, Literals.TRUE);
		assertTrue(((Boolean)expression.evaluate(null)).booleanValue());
	}

	public void testEvaluateFalse()
	throws Exception
	{
		Expression expression = new AndPredicate(Literals.TRUE, Literals.FALSE);
		assertFalse(((Boolean)expression.evaluate(null)).booleanValue());
	}

	public void testEvaluateFalseReverse()
	throws Exception
	{
		Expression expression = new AndPredicate(Literals.FALSE, Literals.TRUE);
		assertFalse(((Boolean)expression.evaluate(null)).booleanValue());
	}

	public void testEvaluateBothFalse()
	throws Exception
	{
		Expression expression = new AndPredicate(Literals.FALSE, Literals.FALSE);
		assertFalse(((Boolean)expression.evaluate(null)).booleanValue());
	}
}
