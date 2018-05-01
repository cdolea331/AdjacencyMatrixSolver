//
// Name: Olea, Carlos
// Project: 2
// Due: December 4 2017
// Course: cs-241-02-f17
//
// Description:
// Read a text input to create a directed graph, then find the shortest path between two cities on the graph.
//

package GraphPackage;

public interface StackInterface<T> {
	/**
	 * Adds a new entry to the top of this stack.
	 * 
	 * @param newEntry an object to be added to the stack
	 *
	 */
	void push(T newEntry);

	/**
	 * Removes and returns this stack’s top entry.
	 * 
	 * @return either the object at the top of the stack or, if the stack is empty before the operation, null
	 *
	 */
	T pop();

	/**
	 * Retrieves this stack’s top entry.
	 * 
	 * @return either the object at the top of the stack or null if the stack is empty
	 *
	 */
	T peek();

	/**
	 * Detects whether this stack is empty.
	 * 
	 * @return true if the stack is empty
	 */
	boolean isEmpty();

	/** Removes all entries from this stack */
	void clear();

} // end StackInterface