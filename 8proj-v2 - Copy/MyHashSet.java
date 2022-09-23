import java.io.*;
import java.util.*;

public class MyHashSet implements HS_Interface {
	private int numBuckets;
	private Node[] bucketArray;
	private int size; // total # keys stored in set right now
	final int RANDOM_PRIME_NUMBER = 61;
	private final int MAX_ACCEPTABLE_AVE_BUCKET_SIZE = 20;

	public MyHashSet(int numBuckets) {
		size = 0;
		this.numBuckets = numBuckets;
		bucketArray = new Node[numBuckets]; // array of linked lists
		System.out.format(
				"IN CONSTRUCTOR: INITIAL TABLE LENGTH=%d RESIZE WILL OCCUR EVERY TIME AVE BUCKET LENGTH EXCEEDS %d\n",
				numBuckets, MAX_ACCEPTABLE_AVE_BUCKET_SIZE);
	}

	public boolean contains(String key) {
		Node curr = bucketArray[hashOf(key)];
		while (curr != null) {
			if (curr.data.equals(key)) {
				return true;
			} else {
				curr = curr.next;
			}
		}
		return false;
	}

	public boolean add(String key) {
		if (contains(key) == true) {
			return false;
		}
		int addIndex = hashOf(key);
		bucketArray[addIndex] = new Node (key, bucketArray[addIndex]);
		++size;
		if (size > MAX_ACCEPTABLE_AVE_BUCKET_SIZE * this.numBuckets)
			upSize_ReHash_AllKeys(); // DOUBLE THE ARRAY .length THEN REHASH ALL KEYS
		return true;
	}

	public boolean remove(String key) {
		int index = hashOf(key);
		Node curr = bucketArray[index];
		if (curr == null) return false;
		if (curr.data.equals(key))
		{
			bucketArray[index] = curr.next;
			return true;
		}
		while (curr.next != null)
		{
			if (curr.next.data.equals(key)) {
				curr.next = curr.next.next;
				--size;
				return true;
			} else {
				curr = curr.next;
			}
		}
		return false;
	}

	// RETURNS INT IN RANGE [0..numBuckets-1]
	private int hashOf(String key) // h MUST BE IN [0..numBuckets-1]
	{
		int sum = 0;
		for (int i = 0; i < key.length(); i++) {
			sum = key.charAt(i) + (sum >> RANDOM_PRIME_NUMBER) + (sum * RANDOM_PRIME_NUMBER);
		}
		return Math.abs(sum) % this.numBuckets; //hashing with new numbuckets
	}

	private void upSize_ReHash_AllKeys() {
		System.out.format("KEYS HASHED=%10d UPSIZING TABLE FROM %8d to %8d REHASHING ALL KEYS\n", size,
				bucketArray.length, bucketArray.length * 2);
		Node[] biggerArray = new Node[bucketArray.length * 2];
		this.numBuckets = biggerArray.length;// <== DONT FORGET TO DO THIS AS SOON AS YOU UPSIZE
		for (int i = 0; i < bucketArray.length; i++) {
			Node head = bucketArray[i];
			for (Node curr = head; curr != null; curr = curr.next) {
				int upsizeIndex = hashOf(curr.data);
				biggerArray[upsizeIndex] = new Node (curr.data, biggerArray[upsizeIndex]);
			}
		}
		this.bucketArray = biggerArray; 
	} // END UPSIZE & REHASH 

	public void clear() { // FOR EACH BUCKET IN BUCKETARRAY, SET THAT HEADPOINTER TO NULL
		for (int i = 0; i < this.numBuckets; i++) {
			bucketArray[i] = null;
		}
	}

	public int size() {
		return (this.size);
	}

	public boolean isEmpty() {
		return (size() == 0);
	}
} // END MyHashSet CLASS

class Node {
	String data;
	Node next;

	public Node(String data, Node next) {
		this.data = data;
		this.next = next;
	}
}
