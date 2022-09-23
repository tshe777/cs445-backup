import java.io.*;
import java.util.*;

public class CDLL_List<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	
	public CDLL_List()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public CDLL_List( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
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



	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################

	// inserts new elem at front of list - pushing old elements back one place
	@SuppressWarnings("unchecked")
	public void insertAtFront(T data)
	{
		CDLL_Node<T> newNode = new CDLL_Node( data,null,null);
		if (head==null)
		{
			newNode.next = newNode;
			newNode.prev = newNode;
			head = newNode;

		} else {
			head.prev.next = newNode;
			newNode.next = head;
			newNode.prev = head.prev;
			head.prev = newNode;
			head = newNode;	
		}
		
	}
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		CDLL_Node<T> newNode = new CDLL_Node( data,null,null);
		if (head==null)
		{
			newNode.next =  newNode;
			newNode.prev = newNode;
			head = newNode;
			return;
		}
		else
		{
			insertAtFront(data);
			head = head.next;		
			return;	
		}
	}
	public boolean remove(T key)
	{
		if (head == null) return false;
		CDLL_Node <T> curr = head;
		if (head.data.equals(key))
		{
			removeAtFront();
			return true;
		}
		do
		{
			if (curr.data.equals(key))
			{
				curr.prev.next = curr.next;
				curr.next.prev = curr.prev;
				return true;
			}
			curr = curr.next;
		} while (curr != head);
		
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if (head == null) return false;
		if (this.size() == 1)
		{
			head = null;
			return true;
		}
		if (this.size() == 2)
		{
			head.next = head; head.prev = head;
			return true;
		}
		else
		{
			head.prev = head.prev.prev;
			head.prev.next = head;
			return true;
		}
	}
	@SuppressWarnings("unchecked")
	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if (head == null) return false;
		else{
			head = head.next;
			removeAtTail();
			return true;
		}
	}
	@SuppressWarnings("unchecked")
	public String toString()
	{
		String toString = "";  // NO <=> DELIMITERS REQUIRED ANYWHERE IN OUTPUT
		CDLL_Node <T> curr = head;
		if (curr == null) return toString;
		do
		{
			toString += curr.data;
            if (!curr.next.equals(head))
                toString += " ";
			curr = curr.next;
		} while (curr != head);
		return toString;  // JUST A SPACE BETEEN ELEMENTS LIKE IN LAB3
	}
	@SuppressWarnings("unchecked")
	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
	{
		if (head == null) return 0;
		CDLL_Node <T> curr = head;
		int count = 0;
		do 
		{
			count++;
			curr = curr.next;
		} while(curr != head);
		return count;
	}

	public boolean empty()
	{
		return (head == null);  // YOUR CODE HERE
	}

	public boolean contains( T key )
	{
		return (search(key) != null);  // YOUR CODE HERE
	}
	@SuppressWarnings("unchecked")
	public CDLL_Node<T> search( T key )
	{
		if (head == null) return null;

		CDLL_Node <T> curr = head;
		do
		{
			if (curr.data.equals(key))
			{
				return curr;
			}
			curr = curr.next;
		} while (curr != head);
		return null;
	}

	@SuppressWarnings("unchecked")  //CAST TO COMPARABLE THROWS WARNING
	public void insertInOrder(T  data)
	{
		Comparable cdata = (Comparable<T>)data;
		if (head == null || cdata.compareTo(head.data) < 0)
		{
			insertAtFront(data);
			return;
		}
		CDLL_Node <T> curr = head;
		do
		{
			if (cdata.compareTo(curr.next.data) < 0) break;	
			curr = curr.next;
		} while (curr.next != head);
		CDLL_Node<T> newNode = new CDLL_Node <T> (data, null, null);	
		curr.next.prev = newNode;
		newNode.next = curr.next;
		curr.next = newNode;
		newNode.prev = curr;
	}



	@SuppressWarnings("unchecked")
	public CDLL_List<T> union( CDLL_List<T> other )
	{
		CDLL_List<T> union = new CDLL_List<T>();
		CDLL_Node<T> curr = head;
		for (int i = 0; i < this.size(); i++)
		{
			if(!union.contains(curr.data))
			{
				union.insertInOrder(curr.data);
			}
			curr = curr.next;
		}
		curr = other.head;
		for (int i = 0; i < other.size(); i++)
		{
			if (!union.contains(curr.data))   
			{
				union.insertInOrder(curr.data);
			}
			curr = curr.next;
		}
		return union;
	}
	@SuppressWarnings("unchecked")
	public CDLL_List<T> inter( CDLL_List<T> other )
	{
		CDLL_List<T> inter = new CDLL_List<T>();
		CDLL_Node <T> curr = head;
		for (int i = 0; i < this.size(); i++)
		{
			if (other.contains(curr.data) && inter.contains(curr.data))
				inter.insertInOrder(curr.data);
			curr = curr.next;
		}
		return inter;
	}
	@SuppressWarnings("unchecked")
	public CDLL_List<T> diff( CDLL_List<T> other )
	{
		CDLL_List<T> diff = new CDLL_List<T>();
		CDLL_Node<T> curr = head;
		for (int i = 0; i < this.size(); i++)
		{
			if (!other.contains(curr.data) && diff.contains(curr.data))
			{
				diff.insertInOrder(curr.data);
			}
			curr = curr.next;
		}
		return diff;
	}
	@SuppressWarnings("unchecked")
	public CDLL_List<T> xor( CDLL_List<T> other )
	{
		return this.union(other).diff(this.inter(other));
	}

} //END LINKEDLIST CLASS

// A D D   C D L L  N O D E   C L A S S  D O W N   H E R E 
// R E M O V E  A L L  P U B L I C  &  P R I V A T E (except toString)
// R E M O V E  S E T T E R S  &  G E T T E R S 
// M A K E  T O  S T R I N G  P U B L I C



class CDLL_Node<T>
{
	T data;
	CDLL_Node<T> prev, next; // EACH CDLL_Node PTS TO ITS PREV  & NEXT

	CDLL_Node()
	{
	this( null, null, null );  // 3 FIELDS TO INIT
	}

	CDLL_Node(T data)
	{
	this( data, null, null);
	}

	CDLL_Node(T data, CDLL_Node<T> prev, CDLL_Node<T> next)
	{
	this.data = data;
	this.prev = prev;
	this.next = next;
	}

	public String toString()
	{
		return ""+this.data;
	} 
	 
} //EOF