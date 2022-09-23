import java.io.*;
import java.util.*;

public class LinkedList<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// COPY ALL NODES FROM OTHER LIST INTO THIS LIST. WHEN COMPLETED THIS LIST IDENTICAL TO OTHER
	public LinkedList( LinkedList<T> other )
	{
		head = new Node<T> (other.head.getData(), other.head.getNext());
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	@SuppressWarnings("unchecked") 
	public LinkedList( String fileName ) 
	{
		try 
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{  
				insertAtTail( (T)infile.readLine() );  
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}	
	
	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place
	@SuppressWarnings("unchecked")
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print
	@SuppressWarnings("unchecked")
	public String toString()
	{

		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.getNext())
		{
			toString += curr.getData();		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.getNext() != null)
				toString += " -> ";
		}

		return toString + "\n";
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		if (size() == 0)
		{
			insertAtFront(data);
			return;
		} else {
			Node<T> cur = head;
			while (cur.getNext() != null)
				cur = cur.getNext();
			cur.setNext(new Node<T>(data, null));
		}
	}

	// OF COURSE MORE EFFICIENT TO KEEP INTERNAL COUNTER BUT YOU COMPUTE IT DYNAMICALLY WITH A TRAVERSAL LOOP
	@SuppressWarnings("unchecked")
	public int size()
	{	
		int count = 0;
		Node <T> cur = head;
		while(cur != null)
		{
			cur = cur.getNext();
			count++;
		}
		return count;
		// WALK THE LIST AND COUNT THE NODES
	}
	
	// MUST CALL SEARCH AND IF SEARCH RETURNS NULL, THIS METHOD RETURNS FALSE, OTHERWIASE RETURN TRUE

	public boolean contains( T key )
	{	
		return (search(key) != null);		
	}

	// TRAVERSE LIST FRONT TO BACK LOOKING FOR THIS DATA VALUE.
	// RETURN REF TO THE FIRST NODE THAT CONTAINS THIS KEY. DO -NOT- RETURN REF TO KEY ISIDE NODE
	// RETURN NULL IF NOT FOUND
	@SuppressWarnings("unchecked")
	public Node<T> search( T key )
	{
		if (head == null) return null;
		Node<T> cur = head;
		while (cur.getNext() != null)
		{
			if(cur.getData().equals(key))
			{
				return cur;
			}
			cur = cur.getNext();
		}
		return null;		
		
		//WALK THE LIST LOOKING FOR 1ST OCCURANCE OF NODE WITH DATA VALUE EQUAL TO KEY
	}

} //EOF