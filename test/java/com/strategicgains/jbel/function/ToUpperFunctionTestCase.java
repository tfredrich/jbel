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
package com.strategicgains.jbel.function;

import com.strategicgains.jbel.exception.EvaluationException;

import junit.framework.TestCase;

public class ToUpperFunctionTestCase
	extends TestCase
{
	private ToUpperFunction function = new ToUpperFunction();
	
	public void testEvaluate()
	throws EvaluationException
	{
		String value = "Strategic Gains, Inc.";
		assertEquals(value.toUpperCase(), function.perform(value));
	}
}
