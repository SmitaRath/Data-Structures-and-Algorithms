import java.util.ArrayList;

public class IDLList<E> {
	// Data fields for class IDLList
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;

	// definition for inner class Node @SmitaRath
	private class Node<E> {
		E data;
		Node<E> next;
		Node<E> prev;

		// Node constructor for creating new node with element @SmitaRath
		Node(E elem) {
			this.data = elem;
		}

		// Node constructor for creating new node with next and prev @SmitaRath
		Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}

	// Constructor for initializing the data fields
	public IDLList() {
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<>();
	}

	// that adds elem at position index (counting from wherever head is). It uses
	// the index for fast access.
	public boolean add(int index, E elem) {
		// checking if the index is not out of bounds
		if (index > size || index < 0) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		Node newNode = new Node(elem);

		// checking if the head is null, if yes setting node and tail as new element
		// @SmitaRath
		if (head == null) {
			head = newNode;
			tail = newNode;
		}
		// checking if the index is equal to size, if yes making new node as tail.
		// @SmitaRath
		else if (index == size) {
			tail.next = newNode;
			newNode.prev = tail;
			tail = tail.next;
		}
		// checking if the index is eqaul to zero if yes making new node as head
		// @SmitaRath
		else if (index == 0) {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		// after all the edge cases if the index is in between the list @SmitaRath
		else {
			// retrieving the oldnode from indices @SmitaRath
			Node currentNode = indices.get(index);
			newNode.next = currentNode;
			newNode.prev = currentNode.prev;
			currentNode.prev.next = newNode;
			currentNode.prev = newNode;

		}
		// addng in indices list for fast access. @SmitaRath
		indices.add(index, newNode);
		size++;
		return true;

	}

	/*
	 * public boolean add (E elem) that adds elem at the head (i.e. it becomes the
	 * first element of the list). @SmitaRath
	 */
	public boolean add(E elem) {
		Node newNode = new Node(elem);

		// checking if head is null setting head and tail as new node. @SmitaRath
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			// if head is not equal to null, making newNode as new head. @SmitaRath
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
		}
		size++;
		indices.add(0, newNode);

		return true;
	}

	/*
	 * public boolean append (E elem) that adds elem as the new last element of the
	 * list (i.e. at the tail). @SmitaRath
	 */
	public boolean append(E elem) {
		Node newNode = new Node(elem);

		// checking if head is null then setting head and tail as new Node @SmitaRath
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			// if not then making new node as tail. @SmitaRath
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		indices.add(size - 1, newNode);
		return true;
	}

	/*
	 * that returns the object at position index from the head. It uses the index
	 * for fast access. Indexing starts from 0, thus get(0) returns the head element
	 * of the list. @SmitaRath
	 */
	public E get(int index) {
		// checking if index is out of bounds @SmitaRath
		if (index >= size || index < 0) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		return indices.get(index).data;
	}

	// returns the object at the head. @SmitaRath
	public E getHead() {
		// if list is empty throw null pointer exception @SmitaRath
		if (head == null) {
			throw new NullPointerException();
		}

		return head.data;
	}

	// returns the object at the tail. @SmitaRath
	public E getLast() {
		// if list is empty throw null pointer exception @SmitaRath
		if (head == null) {
			throw new NullPointerException();
		}
		return tail.data;
	}

	// returns the list size @SmitaRath
	public int size() {
		return size;
	}

	// that removes and returns the element at the head @SmitaRath
	public E remove() {
		// if list is empty throw null pointer exception @SmitaRath
		if (head == null) {
			throw new NullPointerException();
		}

		E item = head.data;

		// if list has only one element @SmitaRath
		if (head.next == null) {
			head = null;
			tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}

		indices.remove(0);
		size--;
		return item;
	}

	// that removes and returns the element at the tail @SmitaRath
	public E removeLast() {
		// if list is empty throw null pointer exception @SmitaRath
		if (head == null) {
			throw new NullPointerException();
		}
		E item = tail.data;

		// if list has only one element @SmitaRath
		if (head.next == null) {
			head = null;
			tail = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}

		indices.remove(size - 1);
		size--;
		return item;

	}

	/*
	 * that removes and returns the element at the index index. Use the index for
	 * fast access. @SmitaRath
	 */
	public E removeAt(int index) {
		E item;
		// if index is out of bounds throw exception @SmitaRath
		if (index >= size || index < 0) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		Node removeNode = indices.get(index);
		item = indices.get(index).data;

		// if index is zero @SmitaRath
		if (index == 0) {
			// if only one element is present in the list, set head and tail as null
			// @SmitaRath
			if (head.next == null) {
				head = null;
				tail = null;
			}
			// if not delete the head and make head.next as new head @SmitaRath @SmitaRath
			else {
				head = head.next;
				head.prev = null;
			}

		}
		// if index is equal to tail, delete tail and make tail.prev as new tail
		// @SmitaRath
		else if (index == size - 1) {
			tail = tail.prev;
			tail.next = null;
		}
		// after checking all the edge cases, delete the element which is in between the
		// list @SmitaRath
		else {
			removeNode.prev.next = removeNode.next;
			removeNode.next.prev = removeNode.prev;
		}

		indices.remove(index);
		size--;
		return item;
	}

	// remove the item passed in the parameter if it is present in the list
	// @SmitaRath
	public boolean remove(E elem) {
		// if list is empty throw exception @SmitaRath
		if (head == null) {
			throw new NullPointerException();
		}
		Node removeNode = null;
		// search the element. @SmitaRath
		for (int i = 0; i < indices.size(); i++) {
			if (indices.get(i).data == elem) {
				removeNode = indices.get(i);
				indices.remove(i);
				break;
			}
		}

		// if element found @SmitaRath
		if (removeNode != null) {

			// if found element is head @SmitaRath
			if (removeNode == head) {
				// and list has only one element make head and tail as null @SmitaRath
				if (removeNode.next == null) {
					head = null;
					tail = null;
				}
				// else delete the head and make head.next as new head @SmitaRath
				else {
					head = head.next;
					head.prev = null;
				}
			}
			// if found node is tail, remove the tail and make tail.prev as new tail
			// @SmitaRath
			else if (removeNode == tail) {
				tail = tail.prev;
				tail.next = null;
			}
			// else remove the element in between @SmitaRath
			else {
				removeNode.prev.next = removeNode.next;
				removeNode.next.prev = removeNode.prev;
			}
			size--;
			return true;
		}
		// if element not found return false.
		else {
			return false;
		}

	}

	// overriding toString method to show list
	@Override
	public String toString() {
		Node curr = head;
		String returnString = "";
		while (curr != null) {
			returnString = returnString + curr.data + " ";
			curr = curr.next;
		}
		return returnString;
	}

}
