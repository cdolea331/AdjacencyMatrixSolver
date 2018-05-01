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

public interface QueueInterface<T> {
	/**
	 * Adds a new entry to the back of the queue.
	 * 
	 * @param newEntry
	 *            an object to be added
	 */
	void enqueue(T newEntry);

	/**
	 * Removes and returns the entry at the front of this queue.
	 * 
	 * @return either the object at the front of the queue or, if the queue is empty
	 *         before the operation, null
	 */
	T dequeue();

	/**
	 * Retrieves the entry at the front of this queue.
	 * 
	 * @return either the object at the front of the queue or, if the queue is
	 *         empty, null
	 */
	T getFront();

	/**
	 * Detects whether this queue is empty.
	 * 
	 * @return true if the queue is empty, or false otherwise
	 */
	boolean isEmpty();

	/** Removes all entries from this queue. */
	void clear();

} // end QueueInterface
