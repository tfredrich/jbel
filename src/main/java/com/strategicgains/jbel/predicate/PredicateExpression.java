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
package com.strategicgains.jbel.predicate;

import com.strategicgains.jbel.expression.Expression;

public abstract class PredicateExpression
extends BinaryPredicate
{
	public PredicateExpression(Expression leftExpression, Expression rightExpression)
	{
		super(leftExpression, rightExpression);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
    public int compare(Object object1, Object object2)
	{
		int result = 0;

		if (object1 == null || object2 == null)
		{
			if (object1 == null && object2 == null) result = 0;
			else if (object1 == null) result = -1;
			else result = 1;
		}
		else if (object1 instanceof Comparable && object2 instanceof Comparable)
		{
			result = ((Comparable)object1).compareTo(object2);
		}
		else
		{
			result = object1.toString().compareTo(object2.toString());
		}

		return result;
	}
}
