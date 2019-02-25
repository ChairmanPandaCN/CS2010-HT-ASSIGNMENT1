import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author
 *  @version HT 2019
 */
/**
 * result(3 times average):
 *                      Insert          Quick         MergeRecursive        MergeIterative          Selection
 *10 random           0.580436ms     0.015559ms         0.019814ms            0.033428ms           0.012156ms
 *100 random          0.115966ms     0.040721ms         0.065276ms            0.054944ms           0.111831ms
 *1000 random         4.420679ms     0.413417ms         0.307784ms            0.222814ms           1.789932ms
 *1000 few unique     0.653979ms     0.086549ms         0.119370ms            0.119977ms           0.605235ms
 *1000 nearly ordered 0.723954ms     0.075687ms         0.059972ms            0.219037ms           0.773470ms
 *1000 reverse order  0.864516ms     0.711743ms         0.045385ms            0.046832ms           0.643647ms
 *1000 sorted         0.680371ms     0.494786ms         0.048655ms            0.035538ms           0.494780ms 
 *
 *a.The order of input has impact on all the sorting algorithms here. The insert mainly has difference when the 
 *input is 1000 random, that's because insert sort put the small element into the previous sorted array,so just few
 *operations are needed in the other four situations. The select sort has the similar reason, but just the number 
 *to the selected and the times of moving are less. 
 *  
 *  The quick sort needs less time when the input is few unique or nearly ordered, but it needs even more time
 *  when the input is reverse order and also a little time longer if the input is already sorted. That's because
 *  so when the input is already in order, then the pivots are be chosen one by one, when the input is shuffle,
 *  only few pivots are be chosen, so it needs more compares for ordered array(or reverse).
 *  
 *  The mergeSortRecursive needs less time when the input is already sorted or nearly sorted, that's because here's
 *  nearly no operation of merge or the merge operation is very easy.
 *  But when it comes to MergeIterative sort, the nearly ordered needs the same time of a random input, that's because
 *  the bottom up merge sort needs more operations for all the blocks, although they are already nearly sorted.
 *
 *b.Quick sort. That's because when the input is in reverse order, not only every element will be chosen as pivots,
 *but also every to corresponding elements are needed to be exchanged. But if the input is nearly ordered then very
 *few elements are chosen as pivots and very less exchange operations are made.
 *
 *c.Insertion sort, then the number of random inputs comes from 100 to 1000, it had 40 times longer running time.
 *
 *d.Revursive does better when the inputs are nearly ordered.
 *
 *e.Bsically the MergeIterative is the fastest.
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
	public static final String N10 = "numbers10.txt";
	public static final String N100 = "numbers100.txt";
	public static final String N1000 = "numbers1000.txt";
	public static final String N1000D = "numbers1000Duplicates.txt";
	public static final String N1000NO = "numbersNearlyOrdered1000.txt";
	public static final String N1000R = "numbersReverse1000.txt";
	public static final String N1000S = "numbersSorted1000.txt";


	//~ Constructor ........................................................
	@Test
	public void testConstructor()
	{
		new SortComparison();
	}

	//~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the methods work for empty arrays
	 */
	@Test
	public void testEmpty()
	{
		double[] a = new double[0];
		a = SortComparison.insertionSort(a);
		assertEquals( "Checking empty array for insertionSort", "", toString(a) );

		a = new double[0];
		a = SortComparison.quickSort(a);
		assertEquals( "Checking empty array for quickSort", "", toString(a) );

		a = new double[0];
		a = SortComparison.mergeSortIterative(a);
		assertEquals( "Checking empty array for mergeSortIterative", "", toString(a) );

		a = new double[0];
		a = SortComparison.mergeSortRecursive(a);
		assertEquals( "Checking empty array for mergeSortRecursive", "", toString(a) );

		a = new double[0];
		a = SortComparison.selectionSort(a);
		assertEquals( "Checking empty array for selectionSort", "", toString(a) );
	}

	// ----------------------------------------------------------
	/**
	 * Check that the methods work for 10 elements arrays
	 */
	@Test
	public void Elements10Test()
	{
		double[] a = {2377.88,2910.66,8458.14,1522.08,5855.37,1934.75,8106.23,1735.31,4849.83,1518.63};
		double[] test=a.clone();
		test = SortComparison.insertionSort(test);
		assertEquals( "Checking 10 elements array for insertionSort", "1518.63,1522.08,1735.31,1934.75,2377.88,2910.66,4849.83,5855.37,8106.23,8458.14", toString(test) );

		test=a.clone();
		test = SortComparison.quickSort(test);
		assertEquals( "Checking 10 elements array for quickSort", "1518.63,1522.08,1735.31,1934.75,2377.88,2910.66,4849.83,5855.37,8106.23,8458.14", toString(test) );

		test=a.clone();
		test = SortComparison.mergeSortIterative(test);
		assertEquals( "Checking 10 elements array for mergeSortIterative", "1518.63,1522.08,1735.31,1934.75,2377.88,2910.66,4849.83,5855.37,8106.23,8458.14", toString(test) );

		test=a.clone();
		test = SortComparison.mergeSortRecursive(test);
		assertEquals( "Checking 10 elements array for mergeSortRecursive", "1518.63,1522.08,1735.31,1934.75,2377.88,2910.66,4849.83,5855.37,8106.23,8458.14", toString(test) );

		test=a.clone();
		test = SortComparison.selectionSort(test);
		assertEquals( "Checking 10 elements array for selectionSort", "1518.63,1522.08,1735.31,1934.75,2377.88,2910.66,4849.83,5855.37,8106.23,8458.14", toString(test) );

	}




	// TODO: add more tests here. Each line of code and ech decision in Collinear.java should
	// be executed at least once from at least one test.

	public static String toString(double a[])
	{
		if(a.length<=0)
		{
			return "";
		}
		StringBuilder theString = new StringBuilder();
		theString.append(a[0]);
		for(int i=1;i<a.length;i++)
		{
			theString.append(","+a[i]);
		}
		return theString.toString();

	}
	// ----------------------------------------------------------
	/**
	 *  Main Method.
	 *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
	 *
	 */
	// The result will be displayed in the console.


}

