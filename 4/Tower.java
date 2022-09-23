import java.io.*;
import java.util.*;

public class Tower<T>
{
	private Disk<T> base;  // pointer to first disk at BASE of the tower (i.e. the old head pointer)
	private Disk<T> top;   // pointer to last disk at TOP of the tower   (i.e. the old tail pointer)

	public Tower()
	{	base = null; // compiler does this anyway. just for emphasis
	}

	public boolean empty()
	{
		return (base==null);
	}

	// i.e. the old insertAtTail or now insertAtTop we call it a PUSH
	public void push(T label)
	{
		if (empty())
		{
			base = new Disk <T>(label, null);
			top = base;
		}
		else{
			top.next = new Disk <T>(label, null);
			top = top.next;
			}
		/*base case
		if list empty
			make new node (disk)
			set base pointer to the new node
			set top pointer to the new node
		ELSE
			make a new disk and make top point to it 
			change top pointer
		*/
	}

	// i.e. the old removeAtTail or now removeAtTop we call it a POP
	public Disk<T> pop() throws Exception
	{
		if (empty())
		{
			throw new Exception("FATAL EXCEPTION: TRIED POPPING FROM AN EMPTY STACK");
		}
		else if (base.next == null)
		{
			Disk <T> curr = base;
			base = null;
			return curr;
		} 
		else 
		{
			Disk <T> curr = base;
			while (curr.next.next !=null)
			{
				curr = curr.next;
			}
			curr.next = null;
			return curr;
		}

		// base case 1: THROW FATAL EXCEPTION IF TOWER IS EMPTY cus it shouldn't work if it's empty (whatever called it is busted)
		/*
			similar to remove@tail method 
			needs to go thru until the one before and take off and return value
			set top to the pointer (one before the element that was popped)

		*/
	}

	// prints the tower base to top, left to right,  respectively //
	public String toString()
	{	if (base == null ) 	return "EMPTY\t";
		String toString = "";
		for ( Disk<T> curr = base; curr != null ; curr=curr.next )
			toString += curr.label + " ";

		return toString;
	}
} // END TOWER CLASS

/*###############################################################################*/

class Disk<T>
{
	T label;
	Disk<T> next;

	Disk(T data)
	{	this( data, null );
	}

	Disk(T label, Disk<T> next)
	{	this.label = label;
		this.next = next;
	}

} // END DISK CLASS