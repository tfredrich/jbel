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

import com.strategicgains.jbel.exception.FunctionException;

/**
 * A BinaryFunction performs over two given arguments, returning the results as an object.
 * 
 * @author Todd Fredrich
 * @since Aug 22, 2005
 * @version $Revision$
 */
public interface BinaryFunction
{
	/**
	 * Performs the function returning the results, based on the two arguments, as an object.
	 * 
	 * @param argument1 the argument for this function. May be null.
	 * @param argument2 the argument for this function. May be null.
	 * @return an object representing the result of performing the function for the given arguments.
	 * @throws FunctionException if the function could not be evaluated.
	 */
	public Object perform(Object argument1, Object argument2)
		throws FunctionException;
}
