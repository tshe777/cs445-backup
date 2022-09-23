import java.io.*;
import java.util.*;

public class LinkedList<T> {
	private Node<T> head; // pointer to the front (first) element of the list

	public LinkedList() {
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList(String fileName, boolean orderedFlag) {
		head = null;
		try {
			BufferedReader infile = new BufferedReader(new FileReader(fileName));
			while (infile.ready()) {
				if (orderedFlag)
					insertInOrder((T) infile.readLine()); // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail((T) infile.readLine()); // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER
															// PRESERVED
			}
			infile.close();
		} catch (Exception e) {
			System.out.println("FATAL ERROR CAUGHT IN C'TOR: " + e);
			System.exit(0);
		}
	}

	// -------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data) {
		head = new Node<T>(data, head);
	}

	// we use toString as our print

	public String toString() {
		String toString = "";

		for (Node curr = head; curr != null; curr = curr.next) {
			toString += curr.data; // WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U W R I T E T H E S E M E T H O D S
	// ########################

	@SuppressWarnings("unchecked")
	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU MUST WRITE LOOP
	{
		Node<T> curr = head;
		int size = 0;
		while (curr != null) {
			curr = curr.next;
			size++;
		}
		return size; // loop for physical size
	}

	public boolean empty() {
		return (size() == 0); // check if size () = 0
	}

	public boolean contains(T key) {
		return (search(key) != null); // return boolean from search loop (if search returns null, then return false)
	}

	@SuppressWarnings("unchecked")
	public Node<T> search(T key) {
		if (head == null)
			return null;
		Node<T> curr = head;
		while (curr != null) {
			if (curr.data.equals(key)) {
				return curr;
			}
			curr = curr.next;
		}
		return null;
	}

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data) {
		if (head == null || size() == 0) {
			insertAtFront(data);
		} else {
			Node<T> curr = head; // set a cursor -> go all the way back and then insert
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = new Node<T>(data);
		}
	}

	@SuppressWarnings("unchecked") // CAST TO COMPARABLE THROWS WARNING
	public void insertInOrder(T data) // recursion possible
	{
		Comparable cdata = (Comparable<T>) data;
		if (head == null || cdata.compareTo(head.data) < 0) {
			insertAtFront(data);
			return;
		}
		Node<T> curr = head;
		while (curr.next != null) {
			if (cdata.compareTo(curr.next.data) < 0)
				break;
			curr = curr.next;
		}
		curr.next = new Node<T>(data, curr.next);

		// NOTE: when doing data.compareTo(), first have to cast
		// Comparable Cdata = (Comparable)(data); //casting to a comparable type
		// or casting directly into the while loops and if loops
		/*
		 * base case 1 [empty]: call insertAtFront base case 2 [not empty && new data
		 * should be in the front(means data.compareTo(head.data) shows that the data is
		 * less than what's in head)]: call insertAtFront ^ can combine base cases into
		 * an OR boolean and both would just call insertAtFront else [not empty &&
		 * insertion is somewhere not in the front]: 1. T curr = head; 2. while
		 * (curr.next != null && data.compareTo(curr.next.data)) (last part is comparing
		 * key with data, if key > data, keep going) iterate cursor 3. make new node
		 * after curr and before curr.next
		 */
	}

	@SuppressWarnings("unchecked")
	public boolean remove(T key) {
		if (head == null || size() == 0)
			return false;
		if (head.data.equals(key)) {
			removeAtFront();
			return true;// if head is pointing to the one to be deleted -> removeAtFront then return
						// true;
		} else {
			Node<T> curr = head;
			while (curr.next != null && !curr.next.data.equals(key)) {
				curr = curr.next;
			}
			if (curr.next == null) {
				return false;
			}
			curr.next = curr.next.next;
			return true;

			// set cursor and loop until 1 before

		}

	}

	@SuppressWarnings("unchecked")
	public boolean removeAtTail() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if (size() == 0)
			return false; // YOUR CODE HERE
		else if (size() == 1) {
			removeAtFront();
			return true;
		} else {
			Node<T> curr = head;
			while (curr.next != null) {
				curr = curr.next;
			}
			remove(curr.data);
			// loop until one before the end then call remove
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if (head == null || empty())
			return false;
		else {
			head = head.next;
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	public LinkedList<T> union(LinkedList<T> other) {
		LinkedList<T> union = new LinkedList<T>();
		Node<T> curr = head;
		for (int i = 0; i < this.size(); i++) {
			if (!union.contains(curr.data)) {
				union.insertInOrder(curr.data);
			}
			curr = curr.next;
		}
		curr = other.head;
		for (int i = 0; i < other.size(); i++) {
			if (!union.contains(curr.data)) {
				union.insertInOrder(curr.data);
			}
			curr = curr.next;
		}
		return union;
	}

	@SuppressWarnings("unchecked")
	public LinkedList<T> inter(LinkedList<T> other) {
		LinkedList<T> inter = new LinkedList<T>();
		Node<T> curr = head;
		for (int i = 0; i < this.size(); i++) {
			if (other.contains(curr.data)) {
				inter.insertInOrder(curr.data);
			}
			curr = curr.next;
		}

		return inter;
	}

	@SuppressWarnings("unchecked")
	public LinkedList<T> diff(LinkedList<T> other) {
		LinkedList<T> diff = new LinkedList<T>();
		Node<T> curr = head;
		for (int i = 0; i < this.size(); i++) {
			if (!other.contains(curr.data)) {
				diff.insertInOrder(curr.data);
			}
			curr = curr.next;
		}
		return diff;
	}

	@SuppressWarnings("unchecked")
	public LinkedList<T> xor(LinkedList<T> other) {
		return this.union(other).diff(this.inter(other));
	}

} // END LINKEDLIST CLASS

class Node<T> {
	T data;
	Node next;

	Node() {
		this(null, null);
	}

	Node(T data) {
		this(data, null);
	}

	Node(T data, Node next) {
		this.data = data;
		this.next = next;
	}

	public String toString() {
		return "" + this.data;
	}

} // EOF