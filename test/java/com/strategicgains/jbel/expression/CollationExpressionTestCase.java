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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import com.strategicgains.jbel.CollationOrder;
import com.strategicgains.jbel.builder.CollationExpressionBuilder;
import com.strategicgains.jbel.domain.Address;
import com.strategicgains.jbel.domain.State;
import com.strategicgains.jbel.domain.TestObject;

public class CollationExpressionTestCase
	extends TestCase
{
	private List objects;
	private List addresses;
	
	public void setUp()
	{
		buildObjectsList();
	}

	public void testOrderByAsComparator()
	{
		CollationExpression orderBy = new CollationExpression()
		.orderBy(new AccessorExpression("intValue"), CollationOrder.ASCENDING)
		.orderBy("stringValue", CollationOrder.DESCENDING)
		.orderBy("name", CollationOrder.ASCENDING);

		Collections.sort(objects, orderBy);
		String[] expected = {"one", "two", "three", "four", "five", "six", "six2", "seven", "eight", "nine", "ten", "ten2"};
		
		for (int i = 0; i < objects.size(); ++i)
		{
			assertEquals(expected[i], ((TestObject)objects.get(i)).getName());
		}
	}

	public void testEvaluate()
	throws Exception
	{
		CollationExpression orderBy = new CollationExpression()
			.orderBy(new AccessorExpression("intValue"), CollationOrder.ASCENDING)
			.orderBy("stringValue", CollationOrder.DESCENDING)
			.orderBy("name", CollationOrder.ASCENDING);

		orderBy.evaluate(objects);
		String[] expected = {"one", "two", "three", "four", "five", "six", "six2", "seven", "eight", "nine", "ten", "ten2"};
		
		for (int i = 0; i < objects.size(); ++i)
		{
			assertEquals(expected[i], ((TestObject)objects.get(i)).getName());
		}
	}

	public void testOrderByWithAccessorExpression()
	throws Exception
	{
		buildAddressesList();
		AccessorExpression stateAccessor = new AccessorExpression()
			.attribute("state")
			.attribute("abbreviation");

		CollationExpression orderBy = new CollationExpression()
			.orderBy(stateAccessor, CollationOrder.ASCENDING)
			.orderBy("zipCode", CollationOrder.DESCENDING);
		
		orderBy.evaluate(addresses);
		assertEquals("AK", ((Address)addresses.get(0)).getState().getAbbreviation());
		assertEquals("CA", ((Address)addresses.get(1)).getState().getAbbreviation());
		assertEquals("95211", ((Address)addresses.get(1)).getZipCode());
		assertEquals("CA", ((Address)addresses.get(2)).getState().getAbbreviation());
		assertEquals("95210", ((Address)addresses.get(2)).getZipCode());
		assertEquals("CO", ((Address)addresses.get(3)).getState().getAbbreviation());
		assertEquals("WA", ((Address)addresses.get(4)).getState().getAbbreviation());
	}
	
	public void testBuilder()
	throws Exception
	{
		CollationExpressionBuilder builder = new CollationExpressionBuilder();
		builder.orderBy("intValue")
			.orderBy("stringValue", CollationOrder.DESCENDING)
			.orderBy("name", CollationOrder.ASCENDING);
		builder.evaluate(objects);
		String[] expected = {"one", "two", "three", "four", "five", "six", "six2", "seven", "eight", "nine", "ten", "ten2"};
		
		for (int i = 0; i < objects.size(); ++i)
		{
			assertEquals(expected[i], ((TestObject)objects.get(i)).getName());
		}
	}

	private void buildObjectsList()
	{
		objects = new ArrayList(10);
		objects.add(new TestObject("ten", 10, "10"));
		objects.add(new TestObject("seven", 7, "7"));
		objects.add(new TestObject("one", 1, "1"));
		objects.add(new TestObject("five", 5, "5"));
		objects.add(new TestObject("eight", 8, "8"));
		objects.add(new TestObject("four", 4, "4"));
		objects.add(new TestObject("nine", 9, "9"));
		objects.add(new TestObject("six", 6, "6"));
		objects.add(new TestObject("six2", 6, "6"));
		objects.add(new TestObject("ten2", 10, "1"));
		objects.add(new TestObject("two", 2, "2"));
		objects.add(new TestObject("three", 3, "3"));
	}
	
	private void buildAddressesList()
	{
		addresses = new ArrayList();
		addresses.add(new Address("123 Elm St.", "Seattle", State.WA, "88125"));
		addresses.add(new Address("224 Bluebird Ln.", "Bailey", State.CO, "80125"));
		addresses.add(new Address("11780 Azusa Ct.", "Turlock", State.CA, "95210"));
		addresses.add(new Address("11780 Azusa Ct.", "Turlock", State.CA, "95211"));
		addresses.add(new Address("8824 Dartmouth Ave.", "Fairbanks", State.AK, "05237"));
	}
}
