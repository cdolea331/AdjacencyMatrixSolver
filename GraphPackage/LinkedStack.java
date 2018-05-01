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

/**
 * Stack data structure using linked lists
 *
 */
public class LinkedStack<T> implements StackInterface<T> {

	private Node topNode;

	public LinkedStack() {
		topNode = null;
	}

	@Override
	public void push(T newEntry) {
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	} // end push

	@Override
	public T pop() {
		T top = peek();
		if (topNode != null)
			topNode = topNode.getNextNode();
		return top;
	} // end pop

	@Override
	public T peek() {
		T top = null;
		if (topNode != null)
			top = topNode.getData();
		return top;
	} // end peek

	@Override
	public boolean isEmpty() {
		return topNode == null;
	} // end isEmpty

	@Override
	public void clear() {
		topNode = null;
	} // end clear

	private class Node {
		private T data; // entry in stack
		private Node next; // link to next node

		private Node(T dataPortion) {
			this(dataPortion, null);
		} // end constructor

		private Node(T dataPortion, Node nextNode) {
			setData(dataPortion);
			setNextNode(nextNode);
		} // end constructor

		private T getData() {
			return data;
		}

		private void setData(T data) {
			this.data = data;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node next) {
			this.next = next;
		}

	} // end Node


} // end LinkedStack