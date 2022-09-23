/*
	Deck class - Tao Sheng CS445
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 30;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		
		deck = new int[numCards];
		for (int i = 0; i < numCards; i++)
		{
			deck[i] = i;
		}
	}
	
	public String toString()
	{
		String deckStr = "";
		for ( int i=0 ; i < deck.length ; ++i )
			deckStr += deck[i] + " ";
		return deckStr;
	}

	public void inShuffle()
	{
		int count = 0;
		int tempdeck[] = new int[deck.length];
		int mid = deck.length / 2;
		int start = 0;
		while (count != deck.length)
		{
			tempdeck[count] = deck[mid];
			mid++;
			count++;
			tempdeck[count] = deck[start];
			start++;
			count++;
		}
		count = 0;
		deck = tempdeck;


	}

	public void outShuffle()
	{
		int count = 0;
		int tempdeck[] = new int[deck.length];
		int mid = deck.length / 2;
		int start = 0;
		while (count != deck.length)
		{
			tempdeck[count] = deck[start];
			start++;
			count++;
			tempdeck[count] = deck[mid];
			mid++;
			count++;
		}
		count = 0;
		deck = tempdeck;

	}



	public String toBitString(int n)
	{
		if (n == 0 ) return ""; //base case
		else 
		{
			//(log base 2 of n) + 1 calculates # of bits needed to put N to binary
			//log base 2 of N = log(N) / log(2)
			int nBits = (int)(Math.log(n) / Math.log(2)) + 1;
			char [] charArr = new char [nBits];
			for (int i = 0; i < charArr.length; i++)
			{
				charArr[i] = '0';
			}


			while (n > 0)
			{
				int p = (int)(Math.log(n) / Math.log(2));
				//index ops
				int index = nBits - p -1;
				charArr[index] = '1';
				n = n - (int)(Math.pow(2,p));
				//find largest power (p) of 2 such that 2^p <- n
				// 2^p = n is log(base2) of n = P				
			}

			//test
			System.out.println();

			String str = new String(charArr);
			return str;
		}

	}

	
	// RETURNS TRUE IF DECK IN ORIGINAL SORTED:  0 1 2 3 ...
	public boolean inSortedOrder()
	{
		for (int i = 0; i < deck.length-1; i++ )
		{
			if (deck[i] > deck[i+1] || deck[i] == deck[i+1])
			{
				return false;
			}
		}
		return true;

	}




}	// END DECK CLASS
