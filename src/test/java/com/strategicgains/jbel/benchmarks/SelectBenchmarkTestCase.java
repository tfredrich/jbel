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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import com.strategicgains.jbel.CollectionUtils;
import com.strategicgains.jbel.domain.TestObject;
import com.strategicgains.jbel.exception.EvaluationException;
import com.strategicgains.jbel.expression.AccessorExpression;
import com.strategicgains.jbel.expression.LiteralExpression;
import com.strategicgains.jbel.predicate.AbstractPredicate;
import com.strategicgains.jbel.predicate.AndPredicate;
import com.strategicgains.jbel.predicate.EqualityPredicate;
import com.strategicgains.jbel.predicate.GreaterThanPredicate;
import com.strategicgains.jbel.predicate.Predicate;

public class SelectBenchmarkTestCase
	extends TestCase
{
	private static final boolean SHOULD_BENCHMARK = true;
	private static final int LIST_SIZE = 1000;
	private static final int REPEAT_COUNT = 1000;
	
	private List<TestObject> toFilter;

	public void setUp()
	{
		toFilter = TestObject.createObjectList(LIST_SIZE);
	}

	public void testSelectWithJbelExpression()
	throws EvaluationException
	{
		if (SHOULD_BENCHMARK)
		{
			Date before = new Date();
	//		SelectExpressionBuilder select = new SelectExpressionBuilder();
	//		select.attribute("name").equalTo("zero").and(select.attribute("intValue").greaterThan(900));

			Predicate predicate = (Predicate) new AndPredicate(
				new EqualityPredicate(new AccessorExpression("name"),
					new LiteralExpression("zero")),
				new GreaterThanPredicate(new AccessorExpression("intValue"),
					new LiteralExpression(900)));

			List<TestObject> selected = null;

			for (int i = 0; i < REPEAT_COUNT; ++i)
			{
				selected = (List<TestObject>) CollectionUtils.select(toFilter, predicate);
			}
	
			Date after = new Date();
			System.out.println("Select (JBEL Expression):\t " + computeTime(before, after) + "ms");
			verifyListResults(selected);
		}
	}
	
	public void testSelectWithIterator()
	{
		if (SHOULD_BENCHMARK)
		{
			Date before = new Date();
			List<TestObject> selected = null;

			for (int i = 0; i < REPEAT_COUNT; ++i)
			{
				selected = selectCollectionItems(toFilter);
			}

			Date after = new Date();
			System.out.println("Select (Java Iterator):\t\t " + computeTime(before, after) + "ms");
			verifyListResults(selected);
		}
	}
	
	public void testSelectWithCallback()
	throws EvaluationException
	{
		if (SHOULD_BENCHMARK)
		{
			Date before = new Date();
			List<TestObject> selected = null;
			
			Predicate callbackPredicate = newCallbackPredicate();

			for (int i = 0; i < REPEAT_COUNT; ++i)
			{
				selected = (List<TestObject>) CollectionUtils.select(toFilter, callbackPredicate);
			}

			Date after = new Date();
			System.out.println("Select (JBEL Callback):\t\t " + computeTime(before, after) + "ms");
			verifyListResults(selected);
		}
	}
	
	private void verifyListResults(List<TestObject> selected)
	{
		assertEquals(9, selected.size());
		assertEquals("zero", (selected.get(0)).getName());
		assertEquals(910, (selected.get(0)).getIntValue());
		assertEquals("zero", (selected.get(1)).getName());
		assertEquals(920, (selected.get(1)).getIntValue());
		assertEquals("zero", (selected.get(2)).getName());
		assertEquals(930, (selected.get(2)).getIntValue());
		assertEquals("zero", (selected.get(3)).getName());
		assertEquals(940, (selected.get(3)).getIntValue());
		assertEquals("zero", (selected.get(4)).getName());
		assertEquals(950, (selected.get(4)).getIntValue());
		assertEquals("zero", (selected.get(5)).getName());
		assertEquals(960, (selected.get(5)).getIntValue());
		assertEquals("zero", (selected.get(6)).getName());
		assertEquals(970, (selected.get(6)).getIntValue());
		assertEquals("zero", (selected.get(7)).getName());
		assertEquals(980, (selected.get(7)).getIntValue());
		assertEquals("zero", (selected.get(8)).getName());
		assertEquals(990, (selected.get(8)).getIntValue());
	}
	
	private List<TestObject> selectCollectionItems(List<TestObject> toFilter)
	{
		List<TestObject> results = new ArrayList<TestObject>();
		
		for (TestObject to : toFilter)
		{
			if (to.getName().equals("zero")
			&& to.getIntValue() > 900)
			{
				results.add(to);
			}
		}

		return results;
	}
	
	private Predicate newCallbackPredicate()
	{
		return new AbstractPredicate()
		{
			public boolean test(Object object)
			{
				boolean result = false;
				TestObject to = (TestObject) object;
				
				if (to.getName().equals("zero")
					&& to.getIntValue() > 900)
				{
					result = true;
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
