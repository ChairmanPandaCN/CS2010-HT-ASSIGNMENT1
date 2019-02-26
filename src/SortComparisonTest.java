
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author sikai Lu
 **/
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
 * 10 random           0.359421ms     0.009581ms         0.011769ms            0.036677ms           0.005343ms
 * 100 random          0.217628ms     0.056305ms         0.111239ms            0.129953ms           0.227103ms
 * 1000 random         6.406719ms     0.346926ms         0.313853ms            0.211758ms           3.88676ms
 * 1000 few unique     2.832629ms     0.151496ms         0.240223ms            0.331945ms           1.439335ms
 * 1000 nearly ordered 0.46284ms      0.079208ms         0.099645ms            0.213785ms           3.022641ms
 * 1000 reverse order  0.857343ms     0.766135ms         0.142554ms            0.199873ms           0.488557ms
 * 1000 sorted         0.32315ms      0.398049ms         0.021796ms            0.210377ms           0.279299ms
 *
 *a.	The order of input doesn't matter for selection sort ,since it compares the number in that index slot 
 *with the rest elements in the array to gain the current highest number in the list.This rules also applied to Insertion sort.
 * 
 *		Quick sort takes much longer time than it should,if the order of input is reverse or already sorted.It degenerates into bubble sort,since all the elements
 * are used as pivot once and each time it could only sort a corresponding joint one and the pivot in order. 	
 *    
 *  	MergeSortRecursive works the best if the input is already sorted or nearly sorted,since the number of operation done by merge() is significantly less.
 *  	
 *  	MergeIterative takes roughly constant time no matter of the order of inputs,since it takes roughly same amount of merge() method for different input with the same size.
 *  
 *
 *b.	Quick sort takes much longer time than it should,if the order of input is reverse or already sorted.It degenerates into bubble sort,since all the elements
 * are used as pivot once and each time it could only sort a corresponding joint one and the pivot in order. 	
 *
 *c.	Selective sort, then the number of random inputs comes from 100 to 1000, it takes 716 times longer running time.
 *
 *d.	MergeRecursive does better when the inputs are nearly ordered.
 *
 *e.	MergeRecursive takes less time with improvements in term of only 1000 inputs
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
		double[] a = {4.06,9.44,8.13,7.3,1.07,3.37,4.49,4.28,9.04,4.76};
		double[] test=a.clone();
		test = SortComparison.insertionSort(test);
		assertEquals( "insertionSort", "1.07,3.37,4.06,4.28,4.49,4.76,7.3,8.13,9.04,9.44", toString(test) );

		test=a.clone();
		test = SortComparison.quickSort(test);
		assertEquals( "quickSort", "1.07,3.37,4.06,4.28,4.49,4.76,7.3,8.13,9.04,9.44", toString(test) );

		test=a.clone();
		test = SortComparison.mergeSortIterative(test);
		assertEquals( "mergeSortIterative", "1.07,3.37,4.06,4.28,4.49,4.76,7.3,8.13,9.04,9.44", toString(test) );

		test=a.clone();
		test = SortComparison.mergeSortRecursive(test);
		assertEquals( "mergeSortRecursive", "1.07,3.37,4.06,4.28,4.49,4.76,7.3,8.13,9.04,9.44", toString(test) );

		test=a.clone();
		test = SortComparison.selectionSort(test);
		assertEquals( "electionSort", "1.07,3.37,4.06,4.28,4.49,4.76,7.3,8.13,9.04,9.44", toString(test) );

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

	
	public static void main(String[] args)throws Exception
	{
		try {
			double[] array = new double[10];
			int index = 0;
			BufferedReader br = new BufferedReader(new FileReader(N10));
			String theNum;
			theNum = br.readLine();
			while(theNum!= null)
			{
				double num = Double.parseDouble(theNum);
				array[index] = num;
				index++;
				theNum = br.readLine(); 	
			}
			br.close();
			// test for 10 random numbers.
			double[] testArray = array.clone();
			long startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
			long endTime = System.nanoTime();
			double runTime = (endTime-startTime)/1000000.0;
			System.out.println("                      Insert          Quick         MergeRecursive        MergeIterative          Selection");
			System.out.print("10 random           "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("     "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortRecursive(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("            "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");


			// test for 100 random numbers.
			br = new BufferedReader(new FileReader(N100));
			array = new double[100];
			index = 0;
			theNum = br.readLine();
			while(theNum!= null)
			{
				double num = Double.parseDouble(theNum);
				array[index] = num;
				index++;
				theNum = br.readLine(); 	
			}
			br.close();
			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("100 random          "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("     "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortRecursive(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("            "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");

			// test for 1000 random numbers
			br = new BufferedReader(new FileReader(N1000));
			array = new double[1000];
			index = 0;
			theNum = br.readLine();
			while(theNum!= null)
			{
				double num = Double.parseDouble(theNum);
				array[index] = num;
				index++;
				theNum = br.readLine(); 	
			}
			br.close();
			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("1000 random         "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("     "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortRecursive(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("            "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");


			// test for 1000 few unique numbers
			br = new BufferedReader(new FileReader(N1000D));
			array = new double[1000];
			index = 0;
			theNum = br.readLine();
			while(theNum!= null)
			{
				double num = Double.parseDouble(theNum);
				array[index] = num;
				index++;
				theNum = br.readLine(); 	
			}
			br.close();
			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("1000 few unique     "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("     "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortRecursive(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("            "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");


			// test for 1000 nearly ordered numbers
			br = new BufferedReader(new FileReader(N1000NO));
			array = new double[1000];
			index = 0;
			theNum = br.readLine();
			while(theNum!= null)
			{
				double num = Double.parseDouble(theNum);
				array[index] = num;
				index++;
				theNum = br.readLine(); 	
			}
			br.close();
			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("1000 nearly ordered "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("     "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortRecursive(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("            "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");

			// test for 1000 reverse numbers
			br = new BufferedReader(new FileReader(N1000R));
			array = new double[1000];
			index = 0;
			theNum = br.readLine();
			while(theNum!= null)
			{
				double num = Double.parseDouble(theNum);
				array[index] = num;
				index++;
				theNum = br.readLine(); 	
			}
			br.close();
			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("1000 reverse order  "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("     "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortRecursive(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("            "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");
			
			
			// test for 1000 sorted numbers
			br = new BufferedReader(new FileReader(N1000S));
			array = new double[1000];
			index = 0;
			theNum = br.readLine();
			while(theNum!= null)
			{
				double num = Double.parseDouble(theNum);
				array[index] = num;
				index++;
				theNum = br.readLine(); 	
			}
			br.close();
			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("1000 sorted         "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("     "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortRecursive(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("            "+runTime+"ms");

			testArray = array.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");



		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	}



