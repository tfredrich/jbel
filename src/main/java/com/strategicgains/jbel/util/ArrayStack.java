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
package com.strategicgains.jbel.util;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;

/**
 * @author fredta2
 * @since Dec 18, 2012
 */
public class ArrayStack<T>
implements Stack<T>
{
	private List<T> stack;
	private int index = -1;

	public ArrayStack()
	{
		stack = new ArrayList<T>();
	}

	public ArrayStack(int initialSize)
	{
		stack = new ArrayList<T>(initialSize);
	}

	@Override
    public T push(T object)
    {
	    stack.add(object);
	    ++index;
	    return object;
    }

	@Override
    public T pop()
    {
		if (index < 0)
		{
			throw new EmptyStackException();
		}

	    return stack.remove(index--);
    }

	@Override
    public T peek()
    {
		if (index < 0)
		{
			throw new EmptyStackException();
		}

	    return stack.get(index);
    }

	@Override
    public int size()
    {
	    return stack.size();
    }
	
	@Override
	public boolean isEmpty()
	{
		return stack.isEmpty();
	}
	
	@Override
	public void clear()
	{
		stack.clear();
		index = -1;
	}

	@Override
    public Iterator<T> iterator()
    {
		return stack.iterator();
    }
}
