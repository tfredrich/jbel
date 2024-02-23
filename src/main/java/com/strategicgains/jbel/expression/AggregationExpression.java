/*
	Copyright 2012 Strategic Gains, Inc.

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
package com.strategicgains.jbel.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Aggregates (extracts) several fields/properties from an object into a Map.
 * 
 * @author toddf
 * @since Dec 16, 2012
 */
public class AggregationExpression
implements Expression
{
	private List<AccessorExpression> accessors = new ArrayList<>();

	public AggregationExpression(String... fields)
    {
		super();
		attributes(fields);
    }

	public void attributes(String... fields)
    {
	    for (String select : fields)
		{
			accessors.add(new AccessorExpression(select));
		}
    }

	@Override
	public Object evaluate(Object argument)
	{
		if (Collection.class.isAssignableFrom(argument.getClass()))
		{
			return evaluateOverCollection((Collection<?>) argument);
		}
		else
		{
			return evaluateOverObject(argument);
		}
	}

	private Object evaluateOverCollection(Collection<?> collection)
    {
		List<Object> results = new ArrayList<>();
		
		for (Object element : collection)
		{
			results.add(evaluateOverObject(element));
		}

		return results;
    }

	private Object evaluateOverObject(Object argument)
    {
		Map<String, Object> results = new HashMap<>();

		for (AccessorExpression accessor : accessors)
		{
			results.put(accessor.getAttributeName(), accessor.evaluate(argument));
		}
		
		return results;
    }

}
