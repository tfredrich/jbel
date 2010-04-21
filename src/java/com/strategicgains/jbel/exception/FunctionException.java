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
package com.strategicgains.jbel.exception;

/**
 * FunctionException is thrown when a function fails to perform for one reason or another.
 * 
 * @author Todd Fredrich
 * @since Jan 26, 2006
 * @version $Revision: 1.1 $
 */
public class FunctionException
	extends EvaluationException
{
	private static final long serialVersionUID = -8548285345596618678L;

	public FunctionException() 
	{
		super();
	}

	public FunctionException(String message, Throwable rootCause) 
	{
		super(message, rootCause);
	}

	public FunctionException(String message)
	{
		super(message);
	}

	public FunctionException(Throwable rootCause)
	{
		super(rootCause);
	}	
}
