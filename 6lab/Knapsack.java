import java.io.*;
import java.util.*;
public class Knapsack 
{
    public static void main(String[] args) throws Exception
    {
        if (args.length != 1)
        {
            System.out.println("Must enter a single input file in the command line. Ex) input-1.txt");
            System.exit(0);
        }
        
        BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
        String setLine = inFile.readLine();
        
        int target = Integer.parseInt(inFile.readLine());
        
        int [] set = new int [16];
        int count = 0;
        for (String element: setLine.split(" "))
        {
            set[count] += Integer.parseInt(element);
            count++;
        }
        inFile.close();
        
        System.out.println(setLine);
        System.out.println(target);

        char bitmap;
        for (bitmap = 0xFFFF ; bitmap > 0; --bitmap)
		{
            int sum = 0;
            for (int i = 15 ; i >=0  ; --i )
            {
                boolean ifIthBitIsOne = (bitmap >> i) % 2 == 1;
				if (ifIthBitIsOne)
                    sum += set[i];
            }
            if (sum == target) printSubSet(set, bitmap);
        }

    } //end main

    public static void printSubSet( int [] inArr, char bits) {

        for (int i = 15; i >= 0; i--)
        {
            boolean elementToAddToSubSet = ( bits >> i) % 2 == 1;
            if (elementToAddToSubSet)
                System.out.print(inArr[i] + " ");
        }
        System.out.println("");
    }
} //end class


/*NOTES
        load array [16]
        def bitmap as char -> 16 bits
        for (bitmap = all 16 bits of ones; bitmap = 0; )       | how to assign into the bitmap all ones -> hexadecimal
            int tempsum = 0;
            for (int i = 15; i <= 0; i--)
            {
                if bitmap right shift by i places is odd
                    add set[i] into tempsum
                
            }
            if tempsum == target then printSubSet(set, bitmap);
*/