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
package com.strategicgains.jbel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

import junit.framework.TestCase;

import com.strategicgains.jbel.domain.TestObject;
import com.strategicgains.jbel.exception.EvaluationException;
import com.strategicgains.jbel.expression.CollationExpression;
import com.strategicgains.jbel.function.UnaryFunction;
import com.strategicgains.jbel.predicate.AbstractPredicate;
import com.strategicgains.jbel.predicate.Predicate;

public class JbelTestCase
	extends TestCase
{
	private static final int OBJECT_COUNT = 6;
	
	private List<TestObject> objects;
	private int objectCount;
	
	public void setUp()
	{
		objects = TestObject.createObjectList(OBJECT_COUNT);
		objectCount = 0;
	}
	/*
	 * Test method for 'com.strategicgains.jbel.Jbel.collect(Collection, Function)'
	 */
	public void testCollect()
	throws Exception
	{
		Collection results = CollectionUtils.collect(objects, getCollectFunction());
		assertEquals(OBJECT_COUNT, results.size());
		String[] resultsArray = (String[]) results.toArray(new String[0]);
		assertEquals("zero", resultsArray[0]);
		assertEquals("one", resultsArray[1]);
		assertEquals("two", resultsArray[2]);
		assertEquals("three", resultsArray[3]);
		assertEquals("four", resultsArray[4]);
		assertEquals("five", resultsArray[5]);
	}
	
	private UnaryFunction getCollectFunction()
	{
		return new UnaryFunction()
		{
			public Object perform(Object object)
			{
				return ((TestObject) object).getName();
			}
		};
	}

	/*
	 * Test method for 'com.strategicgains.jbel.Jbel.detect(Collection, Predicate)'
	 */
	public void testDetect()
	throws Exception
	{
		TestObject result = (TestObject) CollectionUtils.detect(objects, getPredicate());
		assertNotNull(result);
		assertEquals("three", result.getName());
	}
	
	private Predicate getPredicate()
	{
		return new AbstractPredicate()
		{
			public boolean test(Object object)
			{
				TestObject to = (TestObject) object;
				return (to.getName().equals("three")
					|| to.getName().equals("four"));
			}
		};
	}

	/*
	 * Test method for 'com.strategicgains.jbel.Jbel.doForEach(Collection, Function)'
	 */
	public void testDoForEach()
	throws Exception
	{
		CollectionUtils.doForEach(objects, getDoFunction());
		assertEquals(OBJECT_COUNT, objectCount);
	}
	
	private UnaryFunction getDoFunction()
	{
		return new UnaryFunction()
		{
			public Object perform(Object object)
			{
				objectCount++;
				return null;
			}
		};
	}
	

	/*
	 * Test method for 'com.strategicgains.jbel.Jbel.reject(Collection, Predicate)'
	 */
	public void testReject()
	throws Exception
	{
		Collection results = CollectionUtils.reject(objects, getPredicate());
		assertEquals(OBJECT_COUNT - 2, results.size());
		TestObject[] resultsArray = (TestObject[]) results.toArray(new TestObject[0]);
		assertEquals("zero", resultsArray[0].getName());
		assertEquals("one", resultsArray[1].getName());
		assertEquals("two", resultsArray[2].getName());
		assertEquals("five", resultsArray[3].getName());
	}

	/*
	 * Test method for 'com.strategicgains.jbel.Jbel.select(Collection, Predicate)'
	 */
	public void testSelectCollectionPredicate()
	throws Exception
	{
		Collection results = CollectionUtils.select(objects, getPredicate());
		assertEquals(2, results.size());
		TestObject[] resultsArray = (TestObject[]) results.toArray(new TestObject[0]);
		assertEquals("three", resultsArray[0].getName());
		assertEquals("four", resultsArray[1].getName());
	}

	public void testSelectWithClosure()
	throws Exception
	{
		Collection results = CollectionUtils.select(objects, new AbstractPredicate()
			{
				@Override
	            public boolean test(Object argument) throws EvaluationException
	            {
					TestObject to = (TestObject) argument;
					return (to.getName().equals("three")
						|| to.getName().equals("four"));
	            }
			}
		);
		assertEquals(2, results.size());
		TestObject[] resultsArray = (TestObject[]) results.toArray(new TestObject[0]);
		assertEquals("three", resultsArray[0].getName());
		assertEquals("four", resultsArray[1].getName());
	}

	/*
	 * Test method for 'com.strategicgains.jbel.Jbel.select(List, Predicate, Expression)'
	 */
	public void testSelectListPredicateExpression()
	throws Exception
	{
		Collection results = CollectionUtils.select(objects, getPredicate(), 
			new CollationExpression().orderBy("name", CollationOrder.ASCENDING));
		assertEquals(2, results.size());
		TestObject[] resultsArray = (TestObject[]) results.toArray(new TestObject[0]);
		assertEquals("four", resultsArray[0].getName());
		assertEquals("three", resultsArray[1].getName());
	}

	/*
	 * Test method for 'com.strategicgains.jbel.Jbel.newCollectionOfType(Collection)'
	 */
	public void testNewCollectionOfType()
	{
		assertTrue("ArrayList not returned", CollectionUtils.newCollectionOfType(new ArrayList(0)) instanceof ArrayList);
		assertTrue("Vector not returned", CollectionUtils.newCollectionOfType(new Vector(0)) instanceof Vector);
		assertTrue("HashSet not returned", CollectionUtils.newCollectionOfType(new HashSet(0)) instanceof HashSet);
		assertTrue("TreeSet not returned", CollectionUtils.newCollectionOfType(new TreeSet()) instanceof TreeSet);
		assertTrue("LinkedList not returned", CollectionUtils.newCollectionOfType(new TreeSet()) instanceof TreeSet);
	}

}
