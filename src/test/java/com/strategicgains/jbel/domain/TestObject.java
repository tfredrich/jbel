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
package com.strategicgains.jbel.domain;

import java.util.ArrayList;
import java.util.List;

public class TestObject
{
	private static final String ONES[] = 
	{
		"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
	};

	private int intValue;
	private String stringValue;
	private String name;
	private TestObject parent;
	private ArrayList<TestObject> children;
	
	public TestObject(String name, int intValue, String stringValue)
	{
		this.name = name;
		this.intValue = intValue;
		this.stringValue = stringValue;
		this.parent = null;
		children = new ArrayList<TestObject>();
	}
	
	// Factory Methods
	
	public static List<TestObject> createObjectList(int size)
	{
		List<TestObject> results = new ArrayList<TestObject>(size);
	
		for (int i = 0; i < size; ++i)
		{
			results.add(new TestObject(getName(i), i, String.valueOf(i)));
		}
		
		return results;
	}
	
	private static String getName(int i)
	{
		String value = String.valueOf(i);
		int w = Integer.valueOf(value.substring(value.length() - 1)).intValue();
		return ONES[w];
	}

	// Accessors

	public int getIntValue()
	{
		return intValue;
	}
	
	public String getName()
	{
		return name;
	}

	public String getStringValue()
	{
		return stringValue;
	}
	
	public TestObject getParent()
	{
		return parent;
	}
	
	public void setParent(TestObject parent)
	{
		this.parent = parent;
	}
	
	public void addChild(TestObject child)
	{
		children.add(child);
		child.setParent(this);
	}
	
	public TestObject getChild(int index)
	{
		return (TestObject) children.get(index);
	}

	public int getNumberOfChildren()
	{
		return children.size();
	}
}
