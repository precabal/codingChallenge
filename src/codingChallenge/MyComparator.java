package codingChallenge;

import java.util.Comparator;

//a custom comparator for sorting in descending order
public class MyComparator implements Comparator<Integer>
{
	//this is the opposite of the default comparator used by the PriorityQueue class
    public int compare( Integer x, Integer y )
    {
        return y - x;
    }
}