package com.strategicgains.jbel.query;

import java.util.ArrayList;
import java.util.List;

import com.strategicgains.jbel.expression.Expression;
import com.strategicgains.jbel.expression.FieldExpression;
import com.strategicgains.jbel.expression.FieldsExpression;
import com.strategicgains.jbel.expression.ElementAccessorExpression;

public class SelectQuery
implements Expression
{
	private List<Expression> commands;

	public SelectQuery()
    {
		commands = new ArrayList<Expression>();
    }

	public SelectQuery field(String name)
	{
		commands.add(new FieldExpression(name));
		return this;
	}

	public SelectQuery fields(String... names)
	{
		commands.add(new FieldsExpression(names));
		return this;
	}
	
	public SelectQuery get(int index)
	{
		commands.add(new ElementAccessorExpression(index));
		return this;
	}

	@Override
	public Object evaluate(Object argument)
	{
		Object result = argument;

		for (Expression expression : commands)
		{
			result = expression.evaluate(result);
		}
		
		return result;
	}
}
