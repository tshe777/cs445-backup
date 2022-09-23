import java.io.*;
import java.util.*;
//tas242 EXC 2 - towers of hanoi with tower stack methods to move from pos 1 to 2
public class Hanoi
{
	static int numOfMoves=0;
	public static void main( String args[] ) throws Exception
	{
		if ( args.length < 1 ) die("USAGE: must enter a small postive int on cmd line\n");
		int numOfDisks = Integer.parseInt( args[0] );
		Tower<Integer> src = new Tower<Integer>(); 
		Tower<Integer> dest = new Tower<Integer>(); 
		Tower<Integer> spare = new Tower<Integer>(); 
		for ( int i=numOfDisks; i > 0; --i )
			src.push( i ) ; // auto boxing will convert int 1 to Integer 1
		 
		System.out.format( "%3d\t %-20s\t%-20s\t%-20s\n",numOfMoves,src,dest,spare);
		moveTower( numOfDisks, src, dest, spare );
	
	} // END MAIN
	
	static void  moveTower( int disk, Tower<Integer> src, Tower<Integer> dest, Tower<Integer> spare ) throws Exception
	{   
		if (disk == 1)
		{
			dest.push(src.pop().label);			//pop off 2nd and push to top of 3rd
			System.out.format( "%3d:\t %-20s\t%-20s\t%-20s\n",++numOfMoves,src,dest,spare);
			return;
		}
		moveTower(disk-1, src, spare, dest);		
		dest.push(src.pop().label);				//pop off 2nd and push to top of 3rd 
		System.out.format( "%3d:\t %-20s\t%-20s\t%-20s\n",++numOfMoves,src,dest,spare);
		moveTower(disk-1, spare, dest, src);
	}
	static void die( String errMsg )
	{
		System.out.println( errMsg );
		System.exit(0);
	}
} // END OF TOWERTESTER CLASS
