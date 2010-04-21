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
package com.strategicgains.jbel.function.math;

import java.math.BigDecimal;

import com.strategicgains.jbel.exception.FunctionException;
import com.strategicgains.jbel.function.UnaryFunction;

/**
 * Implements X^y.  Because BigDecimal does not support pow(), this function converts
 * the operands to doubles, executing Math.pow(double, double), returning a BigDecimal
 * from the result.
 * 
 * @author Todd Fredrich
 * @since Nov 21, 2005
 * @version $Revision$
 */
public class ExponentFunction
extends MathFunction
implements UnaryFunction
{
	public Object perform(Object argument)
		throws FunctionException
	{
		return (new BigDecimal(Math.pow(this.getBigDecimalValue(null).doubleValue(), getBigDecimalValue(argument).doubleValue())));
	}
}
