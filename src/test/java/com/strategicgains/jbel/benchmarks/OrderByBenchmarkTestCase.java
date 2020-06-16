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
package com.strategicgains.jbel.benchmarks;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import com.strategicgains.jbel.CollationOrder;
import com.strategicgains.jbel.builder.CollationExpressionBuilder;
import com.strategicgains.jbel.domain.TestObject;
import com.strategicgains.jbel.expression.CollationExpression;

public class OrderByBenchmarkTestCase
	extends TestCase
{
	private static final boolean SHOULD_BENCHMARK = true;
	private static final int LIST_SIZE = 1000;
	private static final int REPEAT_COUNT = 1000;
	
	private List<TestObject> toSort;

	public void setUp()
	{
		toSort = TestObject.createObjectList(LIST_SIZE);
	}

	public void testOrderByWithJbel()
	{
		if (SHOULD_BENCHMARK)
		{
			Date before = new Date();
			CollationExpressionBuilder orderBy = new CollationExpressionBuilder();
			orderBy.orderBy("name", CollationOrder.DESCENDING)
				.orderBy("intValue");
			
			for (int i = 0; i < REPEAT_COUNT; ++i)
			{
				Collections.sort(toSort, (CollationExpression<TestObject>) orderBy.build());
			}

			Date after = new Date();
			System.out.println("Order By (JBEL):\t " + computeTime(before, after) + "ms");
			verifyListResults(toSort);
		}
	}
	
	public void testOrderByWithComparator()
	{
		if (SHOULD_BENCHMARK)
		{
			Date before = new Date();
			Comparator<TestObject> comparator = getComparator();
			
			for (int i = 0; i < REPEAT_COUNT; ++i)
			{
				Collections.sort(toSort, comparator);
			}
			
			Date after = new Date();
			System.out.println("Order By (Comparator):\t " + computeTime(before, after) + "ms");
			verifyListResults(toSort);
		}
	}
	
	private void verifyListResults(List<TestObject> toSort)
	{
		assertEquals(LIST_SIZE, toSort.size());
		assertEquals("zero", (toSort.get(0)).getName());
		assertEquals(0, (toSort.get(0)).getIntValue());
		assertEquals("zero", (toSort.get(1)).getName());
		assertEquals(10, (toSort.get(1)).getIntValue());
		assertEquals("zero", (toSort.get(2)).getName());
		assertEquals(20, (toSort.get(2)).getIntValue());
		assertEquals("zero", (toSort.get(3)).getName());
		assertEquals(30, (toSort.get(3)).getIntValue());
		assertEquals("zero", (toSort.get(4)).getName());
		assertEquals(40, (toSort.get(4)).getIntValue());
		assertEquals("zero", (toSort.get(5)).getName());
		assertEquals(50, (toSort.get(5)).getIntValue());
		assertEquals("zero", (toSort.get(6)).getName());
		assertEquals(60, (toSort.get(6)).getIntValue());
		assertEquals("zero", (toSort.get(7)).getName());
		assertEquals(70, (toSort.get(7)).getIntValue());
		assertEquals("zero", (toSort.get(8)).getName());
		assertEquals(80, (toSort.get(8)).getIntValue());
		assertEquals("zero", (toSort.get(9)).getName());
		assertEquals(90, (toSort.get(9)).getIntValue());
		assertEquals("zero", (toSort.get(10)).getName());
		assertEquals(100, (toSort.get(10)).getIntValue());
		assertEquals("two", (toSort.get(100)).getName());
		assertEquals(2, (toSort.get(100)).getIntValue());
		assertEquals("three", (toSort.get(200)).getName());
		assertEquals(3, (toSort.get(200)).getIntValue());
}
	
	private Comparator<TestObject> getComparator()
	{
		return new Comparator<TestObject>()
		{
			public int compare(TestObject t1, TestObject t2)
			{
				int result = t1.getName().compareTo(t2.getName()) * -1;
				
				if (result == 0)
				{
					if (t1.getIntValue() > t2.getIntValue())
					{
						result = 1;
					}
					else if (t1.getIntValue() < t2.getIntValue())
					{
						result = -1;
					}
				}
				
				return result;
			}
		};
	}
	
	private long computeTime(Date begin, Date end)
	{
		return (end.getTime() - begin.getTime());
	}
}
