/* CountInversions.java
   CSC 225 - Spring 2021
   Assignment 3 - Template for inversion counting

   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java CountInversions

   To conveniently test the algorithm with a large input, create a
   text file containing space-separated integer values and run the program with
	java CountInversions file.txt
   where file.txt is replaced by the name of the text file.
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;

//Do not change the name of the CountInversions class
public class CountInversions{
	/* CountInversions()
		Count and return the number of inversions in the input array A.

		The function may modify the input array A.

		Do not change the function signature (name/parameters).
	*/

	//This method uses insertion sort to count the number of inversions
	public static int CountInversions(int[] A){
		int count = 0;
		int i = 0;
		int j;
		int temp;
		//Loop through the whole array until element is found to be unordered
		while(i < A.length - 1){
			if(A[i+1] < A[i]){
				j = i;
				temp = A[j+1];
				/*loop towards start of array from unordered element until you find the spot it belongs
				 *bump every element up one spot as you loop backwards
				 *increment the inversion count every time you bump up an element -this is an inversion
				 */
				while((j > 0) && A[j] > temp){
					A[j+1] = A[j];
					count++;
					j--;
				}
				//put the element that was out of order at the index which it belongs
				A[j+1] = temp;
			}
			i++;
		}
		return count;
	}
	

	/* main()
	   Contains code to test the CountInversions function. Nothing in this function
	   will be marked. You are free to change the provided code to test your
	   implementation, but only the contents of the CountInversions() function above
	   will be considered during marking.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		Vector<Integer> inputVector = new Vector<Integer>();

		int v;
		while(s.hasNextInt() && (v = s.nextInt()) >= 0)
			inputVector.add(v);

		int[] array = new int[inputVector.size()];

		for (int i = 0; i < array.length; i++)
			array[i] = inputVector.get(i);

		System.out.printf("Read %d values.\n",array.length);


		long startTime = System.currentTimeMillis();

		int inversionCount = CountInversions(array);

		long endTime = System.currentTimeMillis();

		double totalTimeSeconds = (endTime-startTime)/1000.0;

		System.out.printf("Number of inversions: %d\n",inversionCount);
		System.out.printf("Total Time (seconds): %.2f\n",totalTimeSeconds);
	}
}
