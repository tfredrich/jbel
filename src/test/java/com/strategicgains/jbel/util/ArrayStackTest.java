/*
	Copyright 2012 Strategic Gains, Inc.

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
package com.strategicgains.jbel.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.EmptyStackException;
import java.util.Iterator;

import org.junit.Test;

/**
 * 
 * @author toddf
 * @since Dec 18, 2012
 */
public class ArrayStackTest
{
	@Test(expected=EmptyStackException.class)
	public void shouldThrowOnEmptyPop()
	{
		Stack<Object> stack = new ArrayStack<Object>();
		stack.pop();
	}

	@Test(expected=EmptyStackException.class)
	public void shouldThrowOnEmptyPeek()
	{
		Stack<Object> stack = new ArrayStack<Object>();
		stack.peek();
	}

	@Test
	public void shouldPushPeekAndPop()
	{
		Stack<Object> stack = new ArrayStack<Object>();
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());

		stack.push(new Object());
		assertFalse(stack.isEmpty());
		assertEquals(1, stack.size());		

		Object o1 = stack.peek();
		assertNotNull(o1);
		assertFalse(stack.isEmpty());
		assertEquals(1, stack.size());		

		Object o2 = stack.pop();
		assertNotNull(o2);
		assertEquals(o1, o2);
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());
	}
	
	@Test
	public void shouldClear()
	{
		Stack<Object> stack = new ArrayStack<Object>();
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());

		stack.push(new Object());
		assertFalse(stack.isEmpty());
		assertEquals(1, stack.size());		

		stack.clear();
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());
	}

	@Test
	public void shouldReturnObjectsInReverseOrder()
	{
		Stack<Integer> stack = new ArrayStack<Integer>();
		
		for (int i = 0; i < 50; ++i)
		{
			stack.push(Integer.valueOf(i));
		}
		
		for (int c = 49; c >= 0; --c)
		{
			assertEquals(Integer.valueOf(c), stack.pop());
		}
	}

	@Test
	public void shouldIterateObjectsInPushOrder()
	{
		Stack<Integer> stack = new ArrayStack<Integer>();
		
		for (int i = 0; i < 50; ++i)
		{
			stack.push(Integer.valueOf(i));
		}

		Iterator<Integer> iterator = stack.iterator();
		int c = 0;

		while (iterator.hasNext())
		{
			assertEquals(Integer.valueOf(c++), iterator.next());
		}
	}
}
