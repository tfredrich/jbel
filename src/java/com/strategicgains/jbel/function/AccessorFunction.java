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
package com.strategicgains.jbel.function;

import java.lang.reflect.Field;
import java.util.Map;

import com.strategicgains.jbel.exception.EvaluationException;
import com.strategicgains.jbel.exception.FunctionException;

/**
 * A Function that, when evaluated, will retrieve the value of the named attribute for the given argument.
 * 
 * @author Todd Fredrich
 */
public class AccessorFunction
implements UnaryFunction
{
	/**
	 * The name of an object attribute from which a value will be retrieved.
	 */
	private String attributeName;
	
	/**
	 * Construct an accessor function for a given field name.
	 * 
	 * @param name the name of an attribute or field of an object.
	 */
	public AccessorFunction(String name)
	{
		this.attributeName = name;
	}

	/**
	 * Return the value of the attribute named by attributeName on the given argument object.
	 * 
	 * @param argument an object from which to retrieve a field value.
	 * @throws EvaluationException if the field cannot be retrieved.
	 * @see com.strategicgains.jbel.function.UnaryFunction#perform(java.lang.Object)
	 */
	public Object perform(Object argument)
	throws FunctionException
	{
		if (Map.class.isAssignableFrom(argument.getClass()))
		{
	    	return ((Map) argument).get(attributeName);		
		}

		try
        {
	    	Field f = argument.getClass().getDeclaredField(attributeName);
	    	f.setAccessible(true);
	        return f.get(argument);
        }
        catch (Exception e)
        {
        	throw new EvaluationException(attributeName, e);
        }
	}
}
