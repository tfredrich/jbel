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

public class Address
{
	private String street;
	private String city;
	private State state;
	private String zipCode;
	
	public Address (String street, String city, State state, String zipCode)
	{
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public String getStreet()
	{
		return street;
	}

	public String getCity()
	{
		return city;
	}

	public State getState()
	{
		return state;
	}

	public String getZipCode()
	{
		return zipCode;
	}
}
