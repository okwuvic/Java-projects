/**
* Getting the run time for 1000 elements
* 
* Run time of the Java's insertion sort method: 7 milliseconds

* Run time of the Java's built-in sorting method: 1 milliseconds


* Getting the run time for 10000 elements
* 
* Run time of the Java's insertion sort method: 95 milliseconds

* Run time of the Java's built-in sorting method: 5 milliseconds
* 
 
* Getting the run time for 100000 elements
* 
* Run time of the Java's insertion sort method: 9371 milliseconds

* Run time of the Java's built-in sorting method: 25 milliseconds
* 

* This time is for 1000000 elements
* 
* Run time of the Java's insertion sort method: Program could not run.

* Run time of the Java's built-in sorting method: 63 milliseconds
* 
*/

package AssignmentUnit1;

import java.util.Arrays;

/*
 
* @author  Victor Nwankpa
* @version 1.0
* @since   2020-04-13
 
*/

public class BenchmarkingSortingAlgorithms {

	/**
	 * The BenchmarkingSortingAlgorithms method is used to test the performances of
	 * two algorithms using insertion sort method and java in-built Arrays.sort
	 * method.
	 * 
	 * @param maxArraySize  This is the parameter to initialize the array size.
	 * @param sortingArray1 This is the first array parameter of type int[].
	 * @param sortingArray2 This is the second array parameter of type int[].
	 * @param startTime1    The first parameter to store start time in milliseconds
	 * @param startTime2    The second parameter to store start time in milliseconds
	 * @param runTime1      Parameter that stores difference between the system time
	 *                      and the startTime1
	 * @param runTime2      Parameter that stores difference between the system time
	 *                      and the startTime2
	 */

	public static void main(String[] args) {
		// create two array of integers of size 1000

		int maxArraySize = 1000;

		int[] sortingArray1 = new int[maxArraySize]; // First Array
		int[] sortingArray2 = new int[maxArraySize]; // Second Array

		for (int i = 0; i < sortingArray1.length; i++) {

			// Filling two arrays with the same random numbers.
			sortingArray1[i] = (int) (Integer.MAX_VALUE * Math.random());
			sortingArray2[i] = (int) (Integer.MAX_VALUE * Math.random());
		}

		// to store execution time in milliseconds
		long startTime1 = System.currentTimeMillis();

		// sort first array using Insertion sort
		for (int i = 0; i < sortingArray1.length; ++i) {

			int j = i;

			while (j > 0 && sortingArray1[j - 1] > sortingArray1[j]) {

				int key = sortingArray1[j];
				sortingArray1[j] = sortingArray1[j - 1];
				sortingArray1[j - 1] = key;
				j = j - 1;
			}
		}

		long runTime1 = System.currentTimeMillis() - startTime1;
		System.out.println("Time taken by Java's insertion sort method: " + runTime1);

		/*
		 * .............................................................................
		 */

		// to store execution time in milliseconds
		long startTime2 = System.currentTimeMillis();

		// sort second array using java in-built Arrays.sort()
		Arrays.sort(sortingArray2);

		long runTime2 = System.currentTimeMillis() - startTime2;
		System.out.println("Time taken by Java's built-in sorting method: " + runTime2);

	}
}
