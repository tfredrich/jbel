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

public class State
{
	// PROTOCOL: CONSTANTS
	
	public static final State AK = new State("Alaska", "AK");
	public static final State CA = new State("California", "CA");
	public static final State CO = new State("Colorado", "CO");
	public static final State OR = new State("Oregon", "OR");
	public static final State WA = new State("Washington", "WA");
	
	// PROTOCOL: VARIABLES
	
	private String name;
	private String abbreviation;
	
	// PROTOCOL: CONSTRUCTION
	
	private State (String name, String abbreviation)
	{
		this.name = name;
		this.abbreviation = abbreviation;
	}

	// PROTOCOL: ACCESSING

	public String getAbbreviation()
	{
		return abbreviation;
	}

	public String getName()
	{
		return name;
	}
}
