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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author toddf
 * @since Dec 16, 2012
 */
public class FieldsExpression
implements Expression
{
	private List<FieldExpression> fields = new ArrayList<FieldExpression>();

	public FieldsExpression(String... selections)
    {
		super();
		
		for (String select : selections)
		{
			fields.add(new FieldExpression(select));
		}
    }

	@Override
	public Object evaluate(Object argument)
	{
		Map<String, Object> results = new HashMap<String, Object>();

		for (FieldExpression field : fields)
		{
			results.put(field.getName(), field.evaluate(argument));
		}
		
		return results;
	}

}
