/*
    Copyright 2012, Strategic Gains, Inc.

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author toddf
 * @since Dec 18, 2012
 */
public class JbelQueryTest
{
	private JbelQuery q = new JbelQuery();

	@Test
	public void shouldQuerySingleStringValue()
	{
		q.field("stringValue");
		assertEquals("string value", q.evaluate(new QueriedClass()));
	}

	@Test
	public void shouldQuerySingleIntValue()
	{
		q.field("intValue");
		assertEquals(42, q.evaluate(new QueriedClass()));
	}

	@Test
	public void shouldQuerySingleBooleanValue()
	{
		q.field("boolValue");
		assertTrue((Boolean) q.evaluate(new QueriedClass()));
	}

	@Test
	public void shouldQueryMapValue()
	{
		Map<String, Object> m = (Map<String, Object>) q.field("map").evaluate(new QueriedClass());
		assertNotNull(m);
		assertEquals("first", m.get("mapString"));
		assertEquals(532, m.get("mapInteger"));
		assertEquals("uno", ((String[])m.get("mapArray"))[0]);
	}
	
	@Test
	public void shouldQueryListValue()
	{
		List<Object> l = (List<Object>) q.field("list").evaluate(new QueriedClass());
		assertNotNull(l);
		assertEquals("listOne", l.get(0));
		assertEquals("listTwo", l.get(1));
		assertEquals("listThree", l.get(2));
	}

	@Test
	public void shouldQueryMultipleFields()
	{
		Map<String, Object> m = (Map<String, Object>) q.fields("stringValue", "intValue", "boolValue", "map").evaluate(new QueriedClass());
		assertNotNull(m);
		assertEquals("string value", m.get("stringValue"));
		assertEquals(42, m.get("intValue"));
		assertTrue((Boolean) m.get("boolValue"));
		assertEquals("first", ((Map<String, Object>)m.get("map")).get("mapString"));
	}
	
	@Test
	public void shouldQueryListElements()
	{
		Object queried = new QueriedClass();
		Map<String, Object> m = (Map<String, Object>)q.fields("list[0]", "list[1]", "list[2]").evaluate(queried);
		assertEquals("listOne", m.get("list[0]"));
		assertEquals("listTwo", m.get("list[1]"));
		assertEquals("listThree", m.get("list[2]"));
	}
	
	@Test
	public void shouldQuerySingleListElement()
	{
		Object queried = new QueriedClass();
		Object o = q.field("list[1]").evaluate(queried);
		assertEquals("listTwo", o);
	}

	@Test
	public void shouldQueryMultipleLayersDeepViaMaps()
	{
		Object o = q.field("map.mapMap.mapMapOne").evaluate(new QueriedClass());
		assertEquals("one", o);
	}

	@Test
	public void shouldQueryCollectionSubset()
	{
		List<Person> p = new ArrayList<Person>();
		p.add(new Person("todd", "denver", 10000.0));
		p.add(new Person("kevin", "boulder", 20000.0));
		p.add(new Person("erik", "colosprings", 30000.0));
		p.add(new Person("eric", "longmont", 40000.0));
		List<Person> l = (List<Person>) q.field("passedIn")
			.where(new WhereClause().field("salary").greaterThan(20000.0)).evaluate(new QueriedClass(p));
		
		assertNotNull(l);
		assertEquals(2, l.size());
		assertEquals("erik", l.get(0).name);
		assertEquals("eric", l.get(1).name);
	}

	@Test
	public void shouldSortQueryCollectionSubset()
	{
		List<Person> p = new ArrayList<Person>();
		p.add(new Person("todd", "denver", 10000.0));
		p.add(new Person("kevin", "boulder", 20000.0));
		p.add(new Person("erik", "colosprings", 30000.0));
		p.add(new Person("eric", "longmont", 40000.0));
		List<Person> l = (List<Person>) q.field("passedIn")
			.orderBy(new OrderByClause().orderBy("salary", CollationOrder.DESCENDING))
			.where(new WhereClause().field("salary").greaterThan(10000.0)).evaluate(new QueriedClass(p));
		
		assertNotNull(l);
		assertEquals(3, l.size());
		assertEquals("eric", l.get(0).name);
		assertEquals("erik", l.get(1).name);
		assertEquals("kevin", l.get(2).name);
	}

	@Test
	public void shouldSelectCollectionSubset()
	{
		List<Person> p = new ArrayList<Person>();
		p.add(new Person("todd", "denver", 10000.0));
		p.add(new Person("kevin", "boulder", 20000.0));
		p.add(new Person("erik", "colosprings", 30000.0));
		p.add(new Person("eric", "longmont", 40000.0));
		List<Map<String, Object>> l = (List<Map<String, Object>>) q.field("passedIn")
			.select("businessUnit", "salary")
			.where(new WhereClause().field("salary").greaterThan(20000.0)).evaluate(new QueriedClass(p));

		assertNotNull(l);
		assertEquals(2, l.size());
		assertEquals("colosprings", l.get(0).get("businessUnit"));
		assertEquals("longmont", l.get(1).get("businessUnit"));
	}

	@Test
	public void shouldSortSelectCollectionSubset()
	{
		List<Person> p = new ArrayList<Person>();
		p.add(new Person("todd", "denver", 10000.0));
		p.add(new Person("kevin", "boulder", 20000.0));
		p.add(new Person("erik", "colosprings", 30000.0));
		p.add(new Person("eric", "longmont", 40000.0));
		List<Map<String, Object>> l = (List<Map<String, Object>>) q.field("passedIn")
			.select("businessUnit", "salary")
			.orderBy(new OrderByClause().orderBy("salary", CollationOrder.DESCENDING))
			.where(new WhereClause().field("salary").greaterThan(10000.0)).evaluate(new QueriedClass(p));

		assertNotNull(l);
		assertEquals(3, l.size());
		assertEquals("longmont", l.get(0).get("businessUnit"));
		assertEquals("colosprings", l.get(1).get("businessUnit"));
		assertEquals("boulder", l.get(2).get("businessUnit"));
	}

	@Test
	public void shouldSelectSubsetOfCollection()
	{
		List<Person> p = new ArrayList<Person>();
		p.add(new Person("todd", "denver", 10000.0));
		p.add(new Person("kevin", "boulder", 20000.0));
		p.add(new Person("erik", "colosprings", 30000.0));
		p.add(new Person("eric", "longmont", 40000.0));
		List<Map<String, Object>> l = (List<Map<String, Object>>) q.field("passedIn")
			.where(new WhereClause().field("salary").greaterThan(20000.0))
			.select("businessUnit", "name")
			.evaluate(new QueriedClass(p));

		assertNotNull(l);
		assertEquals(2, l.size());
		assertEquals("erik", l.get(0).get("name"));
		assertEquals("colosprings", l.get(0).get("businessUnit"));
		assertEquals("eric", l.get(1).get("name"));
		assertEquals("longmont", l.get(1).get("businessUnit"));
	}

	@Test
	public void shouldSortSelectSubsetOfCollection()
	{
		List<Person> p = new ArrayList<Person>();
		p.add(new Person("todd", "denver", 10000.0));
		p.add(new Person("kevin", "boulder", 20000.0));
		p.add(new Person("erik", "colosprings", 30000.0));
		p.add(new Person("eric", "longmont", 40000.0));
		List<Map<String, Object>> l = (List<Map<String, Object>>) q.field("passedIn")
			.where(new WhereClause().field("salary").between(10001.0, 39999.0))
			.orderBy(new OrderByClause().orderBy("salary", CollationOrder.DESCENDING))
			.select("businessUnit", "name")
			.evaluate(new QueriedClass(p));

		assertNotNull(l);
		assertEquals(2, l.size());
		assertEquals("erik", l.get(0).get("name"));
		assertEquals("colosprings", l.get(0).get("businessUnit"));
		assertEquals("kevin", l.get(1).get("name"));
		assertEquals("boulder", l.get(1).get("businessUnit"));
	}
	
	private class Person
	{
		private String name;
		private String businessUnit;
		private double salary;
		
		public Person(String name, String bu, double salary)
		{
			this.name = name;
			this.businessUnit = bu;
			this.salary = salary;
		}
	}
}
