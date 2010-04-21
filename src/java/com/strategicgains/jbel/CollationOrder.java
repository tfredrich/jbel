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
package com.strategicgains.jbel;

/**
 * CollationOrder offers two possible sort orders, ascending and descending.  It is utilized in the constructors of other
 * classes such as ExpressionComparator.
 * 
 * @author Todd Fredrich
 * @since Aug 22, 2005
 * @version $Revision: 1.1 $
 * @see com.strategicgains.jbel.expression.CollationExpression
 */
public class CollationOrder
{
	// PROTOCOL: CONSTANTS
	
	private static final int ASCENDING_ID = 0;
	private static final int DESCENDING_ID = 1;
	
	public static final CollationOrder ASCENDING = new CollationOrder(ASCENDING_ID);
	public static final CollationOrder DESCENDING = new CollationOrder(DESCENDING_ID);
	
	// PROTOCOL: VARIABLES
	
	private int directionId;
	
	// PROTOCOL: CONSTRUCTION
	
	private CollationOrder(int id)
	{
		directionId = id;
	}
	
	// PROTOCOL: ACCESSING
	
	/**
	 * Returns true if the sort order is ASCENDING.
	 * 
	 * @return true if ASCENDING order.
	 */
	public boolean isAscending()
	{
		return (directionId == ASCENDING_ID);
	}
	
	/**
	 * Returns true if the sort order is DESCENDING.
	 * 
	 * @return true if DESCENDING order.
	 */
	public boolean isDescending()
	{
		return (directionId == DESCENDING_ID);
	}

    /**
     * Returns a new value representing the given result in the selected 
     * collation order. Basically, the given result is multiplied by -1 if the
     * selected order is descending.
     * 
     * @param result an integer result from a comparison operation.
     * @return  a new value representing the given result in the 
     * selected sort order.
     */
    public int collateResult(int result)
    {
        return (isAscending() ? result : (result * -1));
    }
}
