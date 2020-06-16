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
 * EvaluationException is thrown when an expression fails to evaluate for one reason or another.
 * 
 * @author Todd Fredrich
 * @since Aug 22, 2005
 * @version $Revision: 1.1 $
 */
public class EvaluationException
extends ExpressionException
{
	private static final long serialVersionUID = -2846961186226290514L;

	public EvaluationException() 
	{
		super();
	}

	public EvaluationException(String message, Throwable rootCause) 
	{
		super(message, rootCause);
	}

	public EvaluationException(String message)
	{
		super(message);
	}

	public EvaluationException(Throwable rootCause)
	{
		super(rootCause);
	}	
}
