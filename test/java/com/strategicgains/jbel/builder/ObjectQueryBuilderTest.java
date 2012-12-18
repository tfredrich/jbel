package com.strategicgains.jbel.builder;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.strategicgains.jbel.JBEL;

public class ObjectQueryBuilderTest
{
	@Test
	public void shouldRetrieveSingleInstance()
	{
		Map<String, Object> dto = new HashMap<String, Object>();
		dto.put("top", "single instance");
		dto.put("another", "another value");

		ObjectQueryBuilder qb = JBEL.queryBuilder().field("top");
		assertEquals("single instance", qb.build().evaluate(dto));
		assertEquals("single instance", JBEL.field(dto, "top"));
	}

	@Test
	public void shouldRetrieveRawList()
	{
		Map<String, Object> dto = new HashMap<String, Object>();
		dto.put("top", new ArrayList<String>(Arrays.asList("one", "two", "three")));
		dto.put("another", "another value");

		List<String> l = (List<String>) JBEL.field(dto, "top");
		assertEquals("one", l.get(0));
		assertEquals("two", l.get(1));
		assertEquals("three", l.get(2));
	}

	@Test
	public void shouldRetrieveRawListIndex()
	{
		Map<String, Object> dto = new HashMap<String, Object>();
		dto.put("top", new ArrayList<String>(Arrays.asList("one", "two", "three")));
		dto.put("another", "another value");

		assertEquals("one", JBEL.field(dto, "top[0]"));
		assertEquals("two", JBEL.field(dto, "top[1]"));
		assertEquals("three", JBEL.field(dto, "top[2]"));
	}

	@Test
	public void shouldRetrieveRawArray()
	{
		Map<String, Object> dto = new HashMap<String, Object>();
		dto.put("top", new String[] {"one", "two", "three"});
		dto.put("another", "another value");

		String[] l = (String[]) JBEL.field(dto, "top");
		assertEquals("one", l[0]);
		assertEquals("two", l[1]);
		assertEquals("three", l[2]);
	}

	@Test
	public void shouldRetrieveRawArrayIndex()
	{
		Map<String, Object> dto = new HashMap<String, Object>();
		dto.put("top", new String[] {"one", "two", "three"});
		dto.put("another", "another value");

		assertEquals("one", JBEL.field(dto, "top[0]"));
		assertEquals("two", JBEL.field(dto, "top[1]"));
		assertEquals("three", JBEL.field(dto, "top[2]"));
	}

	@Test
	public void shouldRetrieveFieldValue()
	{
		Map<String, Object> dto = new HashMap<String, Object>();
		dto.put("top", new InnerClass("foo"));
		dto.put("another", "another value");

		assertEquals("foo", JBEL.field(dto, "top.embedded"));
	}

	@Test
	public void shouldRetrieveEmbeddedArrayValue()
	{
		Map<String, Object> dto = new HashMap<String, Object>();
		dto.put("top", new InnerClass(new String[] {"foo", "bar", "bat"}));
		dto.put("another", "another value");

		assertEquals("foo", JBEL.field(dto, "top.embedded[0]"));
		assertEquals("bar", JBEL.field(dto, "top.embedded[1]"));
		assertEquals("bat", JBEL.field(dto, "top.embedded[2]"));
	}

	@Test
	public void shouldRetrieveEmbeddedMapValue()
	{
		Map<String, Object> dto = new HashMap<String, Object>();
		Map m = new HashMap();
		m.put("foo", new String[] {"bar", "bat"});
		m.put("bar", new InnerClass(new String[] {"foo", "bar", "bat"}));
		dto.put("top", m);
		dto.put("another", "another value");
		
		assertEquals("bar", JBEL.field(dto, "top.foo[0]"));
		assertEquals("bat", JBEL.field(dto, "top.foo[1]"));
		assertEquals("foo", JBEL.field(dto, "top.bar.embedded[0]"));
		assertEquals("bar", JBEL.field(dto, "top.bar.embedded[1]"));
		assertEquals("bat", JBEL.field(dto, "top.bar.embedded[2]"));
	}

	@Test
	public void shouldRetrieveMultipleFieldsViaStrings()
	{
		Map<String, Object> dto = new HashMap<String, Object>();
		Map m = new HashMap();
		m.put("foo", new String[] {"bar", "bat"});
		m.put("bar", new InnerClass(new String[] {"foo", "bar", "bat"}));
		dto.put("top", m);
		dto.put("another", "another value");

		Map<String, Object>multi = JBEL.fields(dto, "top.foo[1]", "top.bar.embedded[2]", "another");
		assertEquals(3, multi.size());
		assertEquals("bat", multi.get("top.foo[1]"));
		assertEquals("bat", multi.get("top.bar.embedded[2]"));
		assertEquals("another value", multi.get("another"));
	}

	@Test
	public void shouldRetrieveMultipleFieldViaExpressions()
	{
		Map<String, Object> dto = new HashMap<String, Object>();
		Map m = new HashMap();
		m.put("foo", new String[] {"bar", "bat"});
		m.put("bar", new InnerClass(new String[] {"foo", "bar", "bat"}));
		dto.put("top", m);
		dto.put("another", "another value");

		ObjectQueryBuilder qb = JBEL.queryBuilder().field("top").field("bar").field("embedded").element(2);
		assertEquals("bat", qb.build().evaluate(dto));
	}

	public class InnerClass
	{
		private Object embedded;

		public InnerClass(Object value)
		{
			this.embedded = value;
		}
	}
}
