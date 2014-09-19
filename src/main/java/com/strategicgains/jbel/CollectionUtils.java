/*
	Copyright 2006-2008 Strategic Gains, Inc.

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
package com.strategicgains.jbel;

import java.util.Collection;
import java.util.List;

import com.strategicgains.jbel.builder.CollationExpressionBuilder;
import com.strategicgains.jbel.builder.PredicateBuilder;
import com.strategicgains.jbel.exception.EvaluationException;
import com.strategicgains.jbel.exception.FunctionException;
import com.strategicgains.jbel.expression.CollationExpression;
import com.strategicgains.jbel.function.UnaryFunction;
import com.strategicgains.jbel.predicate.Predicate;

/**
 * The JBEL collection-manipulation and utility entry point.
 * 
 * @author Todd Fredrich
 * @since Aug 22, 2005
 */
public abstract class CollectionUtils
{
	//SECTION: FACTORIES - CONVENIENCE
	
	public static PredicateBuilder newSelectExpressionBuilder()
	{
		return new PredicateBuilder();
	}
	
	public static CollationExpressionBuilder newCollationExpressionBuilder()
	{
		return new CollationExpressionBuilder();
	}
	
	/**
	 * Constructs a new, empty Collection with identical type of the passed-in collection.
	 *  
	 * @param collection an example Collection.
	 * @return A new, empty Collection of the same type as collection.
	 */
	public static <T> Collection<T> newCollectionOfType(Collection<T> collection)
	{
		Collection<T> result = null;

		try
		{
			result = (Collection<T>) collection.getClass().newInstance();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}


	//SECTION: EXPRESSIONS

	/**
	 * Return a new Collection containing some information from all the
	 * objects in the passed-in collection, calling the operation for 
	 * each of the individual elements in the original collection to
	 * construct the resulting collection. The resulting collection will
	 * have the same size, but each element in the collection may (most likely)
	 * have a different footprint.
	 * 
	 * @param collection the collection from which to derive information.
	 * @param function the operation to perform on each element in the passed-in collection.
	 * @return a collection containing the results of the operation on each element.
	 * @throws EvaluationException 
	 */
	public static <T> Collection<T> collect(Collection<T> collection, UnaryFunction function)
	throws EvaluationException
	{
		CollectFunction<T> collectFunction = new CollectFunction<T>(function, newCollectionOfType(collection));
		doForEach(collection, collectFunction);
		return collectFunction.getResults();
	}

	/**
	 * Return the first object in the collection that satisfies the predicate.  Otherwise, returns null.
	 * 
	 * @param collection the collection in which to detect an object.
	 * @param predicate a predicate that returns true for the desired object.
	 * @return the first object in the collection satisfying the predicate, or null.
	 * @throws EvaluationException 
	 */
	public static <T> T detect(Collection<T> collection, Predicate predicate)
	throws EvaluationException
	{
		for (T element : collection)
		{
			if (predicate.test(element))
			{
				return element;
			}
		}
		
		return null;
	}

	/**
	 * For each element in the collection perform the given operation.
	 * 
	 * @param collection the collection containing elements on to perform the operation.
	 * @param function the operation to perform on each element in the collection.
	 * @throws EvaluationException 
	 */
	public static <T> void doForEach(Collection<T> collection, UnaryFunction function)
	throws EvaluationException
	{
		for (T element : collection)
		{
			function.perform(element);
		}
	}

	/**
	 * Derive a sub-set of the collection where the predicate returns false for each of the elements in the new collection.
	 * 
	 * @param collection a collection from which to derive a sub-set.
	 * @param predicate the predicate that returns false for selected elements.
	 * @return a sub-set of the passed-in collection.
	 */
	public static <T> Collection<T> reject(Collection<T> collection, Predicate predicate)
	throws EvaluationException
	{
		RejectFunction<T> rejectFunction = new RejectFunction<T>(predicate, newCollectionOfType(collection));
		doForEach(collection, rejectFunction);
		return rejectFunction.getResults();
	}

	/**
	 * Derive a sub-set of the collection where the predicate returns true for each of the elements in the new collection.
	 * 
	 * @param collection a collection from which to derive a sub-set.
	 * @param predicate the predicate that returns true for selected elements.
	 * @return a sub-set of the passed-in collection.
	 */
	public static <T> Collection<T> select(Collection<T> collection, Predicate predicate)
	throws EvaluationException
	{
		SelectFunction<T> selectFunction = new SelectFunction<T>(predicate, newCollectionOfType(collection));
		doForEach(collection, selectFunction);
		return selectFunction.getResults();
	}

	/**
	 * Select a sub-set of objects from the list using the given expression and order the results.  For each object in the 
	 * list where the expression evaluates to true, that object will be in the resulting list.
	 * 
	 * @param objects a list of objects.
	 * @param expression a predicate expression that returns true of false.
	 * @param collationExpression an expression to sort the resulting list.
	 * @return an ordered sub-set of the objects list.
	 * @throws EvaluationException if the expression cannot be evaluated.
	 */
	public static <T> List<T> select(List<T> objects, Predicate expression, CollationExpression<T> collationExpression)
	throws EvaluationException
	{
		List<T> selected = (List<T>) select(objects, expression);
		java.util.Collections.sort(selected, collationExpression);
		
		return selected;
	}
	
	
	// SECTION: INNER CLASSES
	
	private static class CollectFunction<T>
	implements UnaryFunction
	{
		private UnaryFunction subFunction;
		private Collection<T> results;
		
		public CollectFunction(UnaryFunction function, Collection<T> resulting)
		{
			this.subFunction = function;
			this.results = resulting;
		}
		
		public Object perform(Object argument)
			throws FunctionException
		{
			results.add((T) subFunction.perform(argument));
			return null;
		}
		
		public Collection<T> getResults()
		{
			return results;
		}
	}
	
	private static class SelectFunction<T>
	implements UnaryFunction
	{
		Predicate predicate;
		Collection<T> results;
		
		public SelectFunction(Predicate predicate, Collection<T> resultingCollection)
		{
			this.predicate = predicate;
			results = resultingCollection;
		}
		
		public Object perform(Object argument)
		throws FunctionException
		{
			if (isSelected(argument))
			{
				results.add((T) argument);
			}
			
			return null;
		}

		protected boolean isSelected(Object argument)
		throws FunctionException
		{
			try
			{
				return predicate.test(argument);
			}
			catch (EvaluationException e)
			{
				throw new FunctionException(e);
			}
		}

		public Collection<T> getResults()
		{
			return results;
		}
	}
	
	private static class RejectFunction<T>
	extends SelectFunction<T>
	{
		public RejectFunction(Predicate predicate, Collection<T> resultingCollection)
		{
			super(predicate, resultingCollection);
		}

		protected boolean isSelected(Object argument)
		throws FunctionException
		{
			return (!super.isSelected(argument));
		}
	}
}
