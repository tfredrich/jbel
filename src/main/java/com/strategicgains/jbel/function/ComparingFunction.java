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

import com.strategicgains.jbel.CollationOrder;
import com.strategicgains.jbel.exception.EvaluationException;
import com.strategicgains.jbel.exception.FunctionException;
import com.strategicgains.jbel.expression.AccessorExpression;

public class ComparingFunction
implements BinaryFunction
{
    //SECTION: INSTANCE VARIABLES

	/**
	 * An expression that will retrieve values from objects to compare.
	 */
	private AccessorExpression accessorExpression;
	
	/**
	 * The sort order for the comparison (e.g. ASCENDING or DESCENDING).
	 */
	private CollationOrder sortOrder;
	
	/**
	 * Construct a new comparing function with a given accessor expression and sort order.
	 * 
	 * @param accessorExpression an expression to retrieve a field value from an object.
	 * @param sortOrder the sort order for the comparison.
	 */
	public ComparingFunction(AccessorExpression accessorExpression, CollationOrder sortOrder)
	{
		this.accessorExpression = accessorExpression;
		this.sortOrder = sortOrder;
	}

	/**
	 * Evaluates the comparison of the given attribute and sort order for the two passed-in objects.
	 * If both objects are null, they are equivelent. If only one is null, the other one is greater.
	 * If they are of type Comparable, the results of compareTo() are returned.  Otherwise, the returned
	 * field values are converted to strings with the toString() method and compared as strings.
	 * 
	 * @return an Integer < 0 if argument1 < argument2, > 0 if argument1 > argument2, ==0 if argument1 == argument2.
	 * @throws EvaluationException if the accessor expression cannot be evaluated.
	 * @see com.strategicgains.jbel.function.BinaryFunction#perform(java.lang.Object, java.lang.Object)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
    public Object perform(Object argument1, Object argument2)
	throws FunctionException
	{
		int result = 0;
		Object attribute1 = getComparisonAttribute(argument1);
		Object attribute2 = getComparisonAttribute(argument2);

		if (attribute1 == null 
			|| attribute2 == null)
		{
			if (attribute1 == null && attribute2 == null) result = 0;
			else if (attribute1 == null) result = -1;
			else result = 1;
		}
		else if (attribute1 instanceof Comparable 
			&& attribute2 instanceof Comparable)
		{
			result = ((Comparable)attribute1).compareTo(attribute2);
		}
		else
		{
			result = String.valueOf(attribute1).compareTo(String.valueOf(attribute2));
		}
	
		return Integer.valueOf(sortOrder.collateResult(result));
	}
	
	/**
	 * An internal method that retrieves the field value of the given object argument.
	 * 
	 * @param object an object from which to retrieve a value.
	 * @return the value of the field specified by the attributeName member.
	 * @throws EvaluationException if the field does not exist in the object.
	 */
	private Object getComparisonAttribute(Object object)
	throws FunctionException
	{
		try
		{
			return accessorExpression.evaluate(object);
		}
		catch (EvaluationException e)
		{
			throw new FunctionException("Failure to evaluate accessor", e);
		}
	}
}
