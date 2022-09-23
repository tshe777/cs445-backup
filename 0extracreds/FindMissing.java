//TAS242 EXC 1 - find missing int in sorted array without a key and using binary search
import java.io.*;
import java.util.*;

public class FindMissing
{
    public static void main (final String args[]) {

        for (int x = 2; x <= 9; x++)
        {
            int [] testing = new int[9];
            for (int i = 1; i < 10; i++)
            {
                if (i < x)
                {
                    testing[i-1] = i;
                }
                else{
                    testing[i-1] = i + 1;
                }
            }
            int n = search(testing.length, testing);
            for (int element: testing)
            {
                System.out.print(element + " ");
            }
            System.out.println("missing "+ n);
        }

        for (int x = 2; x <= 10; x++)
        {
            int [] testing = new int[10];
            for (int i = 1; i < 11; i++)
            {
                if (i < x)
                {
                    testing[i-1] = i;
                }
                else{
                    testing[i-1] = i + 1;
                }
            }
            int n = search(testing.length, testing);
            for (int element: testing)
            {
                System.out.print(element + " ");
            }
            System.out.println("missing "+ n);
        }




    }
    static int search (int right, int[] list)
    {
        //BINARY SEARCH LOG2N TIME
        int l = 0; 
        int r = right-1;
        int mid;
        while(l < r) 
        {
            mid = (l+r) /2;

            if ((list[mid] - mid) == 1)   
            {
                l = mid + 1;
            }
            else
            {
                r = mid;
            }
        }
        return list[l]-1;
    }

}