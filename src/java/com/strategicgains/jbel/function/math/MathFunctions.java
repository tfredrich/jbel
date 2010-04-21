/**
 * 
 */
package com.strategicgains.jbel.function.math;

import com.strategicgains.jbel.function.BinaryFunction;
import com.strategicgains.jbel.function.UnaryFunction;

/**
 * @author Todd Fredrich
 * @version $Revision: 1.1 $
 */
public interface MathFunctions
{
	// CONSTANTS
	
	public final static BinaryFunction ADD = new AddFunction();
	public final static UnaryFunction ABS = new AbsFunction();
	public final static UnaryFunction EXP = new ExponentFunction();
	public final static UnaryFunction NEGATE = new NegateFunction();
	public final static BinaryFunction SUBTRACT = new SubtractFunction();
	public final static BinaryFunction MULTIPLY = new MultiplyFunction();
	public final static BinaryFunction DIVIDE = new DivideFunction();

}
