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

import junit.framework.TestCase;

import com.strategicgains.jbel.domain.TestObject;

public class AccessorFunctionTestCase
	extends TestCase
{
	/*
	 * Test method for 'com.strategicgains.jbel.function.AttributeAccessor.evaluate(Object)'
	 */
	public void testEvaluate()
		throws Exception
	{
		TestObject obj1 = new TestObject("name", 1, "one");
		
		AccessorFunction accessor = new AccessorFunction("stringValue");
		Object value = accessor.perform(obj1);
		assertEquals("one", value.toString());
	}
}
