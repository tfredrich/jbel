package com.strategicgains.jbel.function;

import java.util.Collection;

import com.strategicgains.jbel.exception.EvaluationException;

/**
 * @author toddf
 * @since Dec 14, 2012
 */
public class ElementAccessorFunction
implements UnaryFunction
{
	private int index;

	public ElementAccessorFunction(int index)
    {
		super();
		this.index = index;
    }

    @SuppressWarnings("rawtypes")
    @Override
	public Object perform(Object argument)
	{
    	Class<?> argClass = argument.getClass();

	    if (argClass.isArray())
	    {
	    	return ((Object[]) argument)[index];
	    }
	    else if (Collection.class.isAssignableFrom(argClass))
	    {
	    	return ((Collection) argument).toArray()[index];
	    }

	    throw new EvaluationException(argument.getClass() + " is not an array or Collection for index " + index);
	}
}
