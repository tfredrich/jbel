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
 * Function defines a unary function that performs on a single object returning the results as an object.
 * 
 * @author Todd Fredrich
 * @since Aug 22, 2005
 * @version $Revision: 1.5 $
 */
public interface UnaryFunction
{
	/**
	 * Performs the function returning the results, based on the argument, as an object.
	 * 
	 * @param argument the argument for this function. May be null.
	 * @return an object representing the result of the function perform for the given argument.
	 * @throws FunctionException if the function could not be performed.
	 */
	public Object perform(Object argument)
	throws FunctionException;
}
