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
package com.strategicgains.jbel.builder;

import java.util.Collection;

import com.strategicgains.jbel.CollationOrder;
import com.strategicgains.jbel.exception.EvaluationException;
import com.strategicgains.jbel.expression.AccessorExpression;
import com.strategicgains.jbel.expression.CollationExpression;

/**
 * CollationExpressionBuilder creates CollationExpressions that are suitable for use in Expressions.select(List,Expression,Expression)
 * as the last expression in the parameter list.  Resulting CollationExpression instances may also be used directly by Jbel.sort()
 * or simply call evaluate() on the expression to order a collection. 
 * 
 * @author Todd Fredrich
 * @since Aug 26, 2005
 * @version $Revision: 1.2 $
 */
public class CollationExpressionBuilder<T>
extends AbstractExpressionBuilder
{
	/**
	 * Create a new CollationExpressionBuilder().
	 */
	public CollationExpressionBuilder()
	{
		super();
		setExpression(new CollationExpression<T>());
	}

	/**
	 * Add an attribute to order on in ascending order.
	 * 
	 * @param attributeName the name of the object attribute to order on.
	 * @return CollationExpressionBuilder to facilitate chaining (e.g. builder.attribute("x").attribute("y"))
	 */
	public CollationExpressionBuilder<T> orderBy(String attributeName)
	{
		return orderBy(attributeName, CollationOrder.ASCENDING);
	}
	
	/**
	 * Add an attribute to order on in the specified sort order.
	 * 
	 * @param attributeName the name of the object attribute to order on.
	 * @param sortOrder the sort order to use (ASCENDING or DESCENDING)
	 * @return CollationExpressionBuilder to facilitate chaining (e.g. builder.attribute("x").attribute("y"))
	 */
	public CollationExpressionBuilder<T> orderBy(String attributeName, CollationOrder sortOrder)
	{
		((CollationExpression<T>) getExpression()).orderBy(attributeName, sortOrder);
		return this;
	}
	
	/**
	 * Add an attribute to sort on via an AccessorExpression in the specified sort order.
	 * 
	 * @param expression an AccessorExpression that retrieves a value from the object graph.
	 * @param sortOrder the sort order to use (ASCENDING or DESCENDING)
	 * @return CollationExpressionBuilder to facilitate chaining (e.g. builder.attribute("x").attribute("y"))
	 */
	public CollationExpressionBuilder<T> orderBy(AccessorExpression expression, CollationOrder sortOrder)
	{
		((CollationExpression<T>) getExpression()).orderBy(expression, sortOrder);
		return this;
	}
	
	/**
	 * Evaluate the underlying CollationExpression over a collection.
	 * 
	 * @param collection
	 * @return
	 * @throws EvaluationException
	 */
	public Collection<T> evaluate(Collection<T> collection)
	throws EvaluationException
	{
		return (Collection<T>) ((CollationExpression<T>) getExpression()).evaluate(collection);
	}
}
