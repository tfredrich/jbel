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
package com.strategicgains.jbel.builder;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.strategicgains.jbel.CollectionUtils;
import com.strategicgains.jbel.domain.TestObject;
import com.strategicgains.jbel.expression.AccessorExpression;
import com.strategicgains.jbel.expression.CollationExpression;
import com.strategicgains.jbel.predicate.Predicate;

public class PredicateBuilderTest
{
	private PredicateBuilder predicateBuilder;
	private CollationExpressionBuilder<TestObject> orderBy;
	private List<TestObject> objects;
	private TestObject parent1;
	private TestObject parent2;
	private List<TestObject> parents;

	@Before
	public void setUp()
	{
		predicateBuilder = new PredicateBuilder();
		orderBy = new CollationExpressionBuilder<TestObject>();
		objects = buildObjectsList();
		setupParents();
	}

	private void setupParents()
	{
		assignParent1();
		assignParent2();
		parents = new ArrayList<TestObject>(2);
		parents.add(parent1);
		parents.add(parent2);
	}

	private void assignParent1()
	{
		parent1 = new TestObject("parent1", 99, "99");
		
		for (int i = 0; i < 7; ++i)
		{
			parent1.addChild((TestObject) objects.get(i));
		}
	}

	private void assignParent2()
	{
		parent2 = new TestObject("parent2", 50, "50");
		
		for (int i = 7; i < 13; ++i)
		{
			parent2.addChild((TestObject) objects.get(i));
		}
	}

	/*
	 * Test method for 'com.strategicgains.jbel.builder.PredicateBuilder.select()'
	 */
	@Test
	public void testSelectEquals()
	throws Exception
	{
		predicateBuilder.field("intValue").equalTo(5);
		List<TestObject> results = (List<TestObject>) CollectionUtils.select(objects, (Predicate) predicateBuilder.getExpression());
		assertEquals(5, ((TestObject)results.get(0)).getIntValue());
	}

	/*
	 * Test method for 'com.strategicgains.jbel.builder.PredicateBuilder.select()'
	 */
	@Test
	public void testSelectIsIn()
	throws Exception
	{
		Integer[] values = 
		{
			Integer.valueOf(5),
			Integer.valueOf(1),
			Integer.valueOf(3),
			Integer.valueOf(7)
		};
		
		predicateBuilder.field("intValue").isIn(values);
		List<TestObject> result = (List<TestObject>) CollectionUtils.select(objects, (Predicate) predicateBuilder.getExpression());
		assertEquals(4, result.size());
	}

	/*
	 * Test method for 'com.strategicgains.jbel.builder.PredicateBuilder.select()'
	 */
	@Test
	public void testSelectIsInWithInsideSelect()
	throws Exception
	{
		
		List<TestObject> values = (List<TestObject>) new AccessorExpression("intValue").evaluate(objects);
		predicateBuilder.field("intValue").isIn(values);
		List<TestObject> result = (List<TestObject>) CollectionUtils.select(objects, (Predicate) predicateBuilder.getExpression());
		assertEquals(13, result.size());
	}

	@Test
	public void testSelectToUpper()
	throws Exception
	{
		predicateBuilder.toUpper(predicateBuilder.field("name")).equalTo("FIVE");
		List<TestObject> result = (List<TestObject>) CollectionUtils.select(objects, (Predicate) predicateBuilder.getExpression());
		assertEquals(1, result.size());
		assertEquals(5, ((TestObject)result.get(0)).getIntValue());
	}

	@Test
	public void testSelectToLower()
	throws Exception
	{
		predicateBuilder.toLower(predicateBuilder.field("name")).equalTo("eight");
		List<TestObject> result = (List<TestObject>) CollectionUtils.select(objects, (Predicate) predicateBuilder.getExpression());
		assertEquals(1, result.size());
		assertEquals(8, ((TestObject)result.get(0)).getIntValue());
	}

	/*
	 * Test method for 'com.strategicgains.jbel.builder.PredicateBuilder.orderBy()'
	 */
	@Test
	public void testSelectWithOrderBy()
	throws Exception
	{
		Integer[] values = 
		{
			Integer.valueOf(5),
			Integer.valueOf(1),
			Integer.valueOf(3),
			Integer.valueOf(7)
		};
		
		predicateBuilder.field("intValue").isIn(values);
		orderBy.orderBy("intValue");
		List<TestObject> results = (List<TestObject>) CollectionUtils.select(objects, (Predicate) predicateBuilder.getExpression(), (CollationExpression) orderBy.getExpression());
		assertEquals(4, results.size());
		assertEquals(1, ((TestObject)results.get(0)).getIntValue());
		assertEquals(3, ((TestObject)results.get(1)).getIntValue());
		assertEquals(5, ((TestObject)results.get(2)).getIntValue());
		assertEquals(7, ((TestObject)results.get(3)).getIntValue());
	}

	/*
	 * Test method for 'com.strategicgains.jbel.builder.PredicateBuilder.select()'
	 */
	@Test
	public void testSelectWithAnd()
	throws Exception
	{
		predicateBuilder.field("name").equalTo("five")
			.and(predicateBuilder.field("stringValue").equalTo("5"))
			.and(predicateBuilder.field("intValue").equalTo(5));
		List<TestObject> results = (List<TestObject>) CollectionUtils.select(objects, (Predicate) predicateBuilder.getExpression());
		assertEquals(1, results.size());
		assertEquals(5, ((TestObject)results.get(0)).getIntValue());
	}

	/*
	 * Test method for 'com.strategicgains.jbel.builder.PredicateBuilder.select()'
	 */
	@Test
	public void testSelectWithNonsensicalAnd()
	throws Exception
	{
		predicateBuilder.field("stringValue").equalTo("3")
			.and(predicateBuilder.field("intValue").equalTo(5))
			.and(predicateBuilder.field("name").equalTo("five"));
		List<TestObject> results = (List<TestObject>) CollectionUtils.select(objects, (Predicate) predicateBuilder.getExpression());
		assertEquals(0, results.size());
	}

	/*
	 * Test method for 'com.strategicgains.jbel.builder.PredicateBuilder.select()'
	 */
	@Test
	public void testSelectWithOr()
	throws Exception
	{
		predicateBuilder.field("intValue").equalTo(5)
			.or(predicateBuilder.field("name").equalTo("nine"))
			.or(predicateBuilder.field("stringValue").equalTo("3"));
		orderBy.orderBy("intValue");
		List<TestObject> results = (List<TestObject>) CollectionUtils.select(objects, (Predicate) predicateBuilder.getExpression(), (CollationExpression) orderBy.getExpression());
		assertEquals(3, results.size());
		assertEquals(3, ((TestObject)results.get(0)).getIntValue());
		assertEquals(5, ((TestObject)results.get(1)).getIntValue());
		assertEquals(9, ((TestObject)results.get(2)).getIntValue());
	}
	
	@Test
	public void testSelectWithAndOr()
	throws Exception
	{
		predicateBuilder.field("name").equalTo("five")
			.and(predicateBuilder.field("stringValue").equalTo("5"))
			.and(predicateBuilder.field("intValue").equalTo(5))
			.or(predicateBuilder.field("name").equalTo("nine2")
				.and(predicateBuilder.field("stringValue").equalTo("9"))
				.and(predicateBuilder.field("intValue").equalTo(9)));
		orderBy.orderBy("intValue");
		List<TestObject> results = (List<TestObject>) CollectionUtils.select(objects, (Predicate) predicateBuilder.getExpression(), (CollationExpression) orderBy.getExpression());
		assertEquals(2, results.size());
		assertEquals(5, ((TestObject)results.get(0)).getIntValue());
		assertEquals(9, ((TestObject)results.get(1)).getIntValue());
	}

	@Test
	public void testOneToOneAttributeSelect()
	throws Exception
	{
		predicateBuilder.field("parent").field("intValue").equalTo(99);
		List<TestObject> results = (List<TestObject>) CollectionUtils.select(objects, (Predicate) predicateBuilder.getExpression());
		assertEquals(7, results.size());
	}

	@Test
	public void testMultipleGetExpressionCalls()
	throws Exception
	{
		predicateBuilder.field("intValue").equalTo(5)
			.or(predicateBuilder.field("name").equalTo("nine"))
			.or(predicateBuilder.field("stringValue").equalTo("3"));
		predicateBuilder.getExpression();
		predicateBuilder.getExpression();
		orderBy.orderBy("intValue");
		List<TestObject> results = (List<TestObject>) CollectionUtils.select(objects, (Predicate) predicateBuilder.getExpression(), (CollationExpression) orderBy.getExpression());
		assertEquals(3, results.size());
		assertEquals(3, ((TestObject)results.get(0)).getIntValue());
		assertEquals(5, ((TestObject)results.get(1)).getIntValue());
		assertEquals(9, ((TestObject)results.get(2)).getIntValue());
	}

	@Test
	public void testSelectBetween()
	throws Exception
	{
		predicateBuilder.field("intValue").between(3, 5);
		orderBy.orderBy("intValue");
		List<TestObject> results = (List<TestObject>) CollectionUtils.select(objects, (Predicate) predicateBuilder.getExpression(), (CollationExpression) orderBy.getExpression());
		assertEquals(3, results.size());
		assertEquals(3, ((TestObject)results.get(0)).getIntValue());
		assertEquals(4, ((TestObject)results.get(1)).getIntValue());
		assertEquals(5, ((TestObject)results.get(2)).getIntValue());
	}

	private List<TestObject> buildObjectsList()
	{
		List<TestObject> results = new ArrayList<TestObject>(13);
		results.add(new TestObject("ten", 10, "10"));
		results.add(new TestObject("nine2", 9, "9"));
		results.add(new TestObject("seven", 7, "7"));
		results.add(new TestObject("one", 1, "1"));
		results.add(new TestObject("five", 5, "5"));
		results.add(new TestObject("eiGht", 8, "8"));
		results.add(new TestObject("four", 4, "4"));
		results.add(new TestObject("nine", 9, "9"));
		results.add(new TestObject("six", 6, "6"));
		results.add(new TestObject("six2", 6, "6"));
		results.add(new TestObject("ten2", 10, "1"));
		results.add(new TestObject("two", 2, "2"));
		results.add(new TestObject("three", 3, "3"));
		return results;
	}
}
