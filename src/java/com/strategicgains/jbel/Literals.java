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

import com.strategicgains.jbel.expression.Expression;
import com.strategicgains.jbel.expression.LiteralExpression;
import com.strategicgains.jbel.predicate.LiteralPredicate;
import com.strategicgains.jbel.predicate.Predicate;

public final class Literals
{
	// CONSTANTS

	public static final Expression NULL = new LiteralExpression(null);
	public static final Expression ZERO = new LiteralExpression(0);
	public static final Predicate TRUE = new LiteralPredicate(true);
	public static final Predicate FALSE = new LiteralPredicate(false);
	
	// CONSTRUCTOR
	
	private Literals()
	{
		// do nothing--this class is non-instantiable.
	}
}
