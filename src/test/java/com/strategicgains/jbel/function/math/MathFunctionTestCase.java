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
package com.strategicgains.jbel.function.math;

import java.math.BigDecimal;

import junit.framework.TestCase;

public class MathFunctionTestCase
	extends TestCase
{
	public void testAdd()
	throws Exception
	{
		assertEquals(5.1234, ((BigDecimal)MathFunctions.ADD.perform(Integer.valueOf(3), new Double(2.1234))).doubleValue(), 0.0001);
	}

	public void testSubtract()
	throws Exception
	{
		assertEquals(0.8766, ((BigDecimal)MathFunctions.SUBTRACT.perform(Integer.valueOf(3), new Double(2.1234))).doubleValue(), 0.0001);
	}

	public void testMultiply()
	throws Exception
	{
		assertEquals(4.2468, ((BigDecimal)MathFunctions.MULTIPLY.perform(new Double(2.1234), Integer.valueOf(2))).doubleValue(), 0.0001);
	}

	public void testDivide()
	throws Exception
	{
		assertEquals(1.0617, ((BigDecimal)MathFunctions.DIVIDE.perform(new Double(2.1234), Integer.valueOf(2))).doubleValue(), 0.0001);
	}

	public void testAbs()
	throws Exception
	{
		assertEquals(2.1234, ((BigDecimal)MathFunctions.ABS.perform(new Double(2.1234))).doubleValue(), 0.0001);
		assertEquals(2.1234, ((BigDecimal)MathFunctions.ABS.perform(new Double(-2.1234))).doubleValue(), 0.0001);
	}

	public void testNegate()
	throws Exception
	{
		assertEquals(-2.1234, ((BigDecimal)MathFunctions.NEGATE.perform(new Double(2.1234))).doubleValue(), 0.0001);
		assertEquals(2.1234, ((BigDecimal)MathFunctions.NEGATE.perform(new Double(-2.1234))).doubleValue(), 0.0001);
	}
}
