package com.strategicgains.jbel.expression;

import java.util.Collection;

import com.strategicgains.jbel.exception.EvaluationException;

/**
 * @author toddf
 * @since Dec 14, 2012
 */
public class ElementAccessorExpression
extends AbstractExpression
{
	private int index;

	public ElementAccessorExpression(int index)
    {
		super();
		this.index = index;
    }

	@SuppressWarnings("rawtypes")
    public Object perform(Collection argument)
	{
    	return ((Collection) argument).toArray()[index];
	}

    @Override
	public Object evaluate(Object argument)
	{
	    if (argument.getClass().isArray())
	    {
	    	return ((Object[]) argument)[index];
	    }

	    throw new EvaluationException(argument.getClass() + " is not an array or Collection for index " + index);
	}
}
