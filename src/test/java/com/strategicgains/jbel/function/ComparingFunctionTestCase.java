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

import com.strategicgains.jbel.CollationOrder;
import com.strategicgains.jbel.domain.TestObject;
import com.strategicgains.jbel.expression.AccessorExpression;

public class ComparingFunctionTestCase
extends TestCase 
{
	private TestObject obj1;
	private TestObject obj2;
	
	public void setUp()
	{
		obj1 = new TestObject("two", 2, "2");
		obj2 = new TestObject("ten", 10, "10");
	}

	public void testEvaluateWithInt()
		throws Exception
	{
		ComparingFunction function = new ComparingFunction(new AccessorExpression("intValue"), CollationOrder.ASCENDING);
	
		assertTrue("obj1.intValue < obj2.intValue", ((Integer) function.perform(obj1, obj2)).intValue() == -1);
		assertTrue("obj2.intValue > obj1.intValue", ((Integer) function.perform(obj2, obj1)).intValue() == 1);
		assertTrue("obj1.intValue == obj1.intValue", ((Integer) function.perform(obj1, obj1)).intValue() == 0);
	}

	public void testEvaluateWithString()
		throws Exception
	{
		ComparingFunction function = new ComparingFunction(new AccessorExpression("stringValue"), CollationOrder.ASCENDING);
	
		assertTrue("obj1.stringValue > obj2.stringValue", ((Integer) function.perform(obj1, obj2)).intValue() == 1);
		assertTrue("obj2.stringValue < obj1.stringValue", ((Integer) function.perform(obj2, obj1)).intValue() == -1);
		assertTrue("obj1.stringValue == obj1.stringValue", ((Integer) function.perform(obj1, obj1)).intValue() == 0);
	}
}
