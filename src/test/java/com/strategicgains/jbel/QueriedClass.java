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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author toddf
 * @since Dec 18, 2012
 */
public class QueriedClass
{
	private Object passedIn;
	private String stringValue = "string value";
	private int intValue = 42;
	private boolean boolValue = true;
	private Map<String, Object> map = new HashMap<String, Object>();
	private List<Object> list = new ArrayList<Object>();

	public QueriedClass()
	{
		this(null);
	}

	public QueriedClass(Object wrapped)
	{
		this.passedIn = wrapped;
		map.put("mapString", "first");
		map.put("mapInteger", Integer.valueOf(532));
		map.put("mapArray", new String[] {"uno", "dos", "tres"});
		map.put("mapList", Arrays.asList(new String[] {"mapListOne", "mapListTwo", "mapListThree"}));
		Map<String, String> m = new HashMap<String, String>();
		m.put("mapMapOne", "one");
		m.put("mapMapTwo", "two");
		m.put("mapMapThree", "three");
		map.put("mapMap", m);
		
		list.add("listOne");
		list.add("listTwo");
		list.add("listThree");
		list.add(Arrays.asList(new String[]{"listListOne", "listListTwo", "listListThree"}));		
	}
	
	public static QueriedClass create(Object embedded)
	{
		return new QueriedClass(embedded);
	}
}
