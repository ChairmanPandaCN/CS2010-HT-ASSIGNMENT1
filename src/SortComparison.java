// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author
 *  @version HT 2019
 */

 class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]){
    	int length =a.length;
    	for(int i=1;i<length;i++) {
    		for(int j=i;j>0;j--) {
    			if(a[j]<a[j-1]) {
    				double temp =a[j-1];
    				a[j-1]=a[j];
    				a[j]=temp;
    			}
    		}
    	}
    	return a;
        //todo: implement the sort
    }//end insertionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
//    static double [] quickSort (double a[]){
//	
//		 //todo: implement the sort
//
//    }//end quicksort

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

//    static double[] mergeSortIterative (double a[]) {
//
//		 //todo: implement the sort
//	
//    }//end mergesortIterative
//    
    
    
    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[]) {
    	int start =0;
    	int end =a.length-1;
    	sort(a, start, end);
	return a;
   }//end mergeSortRecursive
    
    private static void sort(double a[],int start,int end) {
    	if(start<end) {
    		int middle =start+(end-start)/2;
    		sort(a, start, middle);
    		sort(a, middle+1, end);
    		merge(a, start, middle, end);
    	}
    }
    private static void merge(double a[],int start,int middle,int end) {
    	int firstHalf=middle-start+1;
    	int secondHalf=end-middle;
    }
    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){
    	int n=a.length;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(a[i]>a[j]){
                    double temp = a[i];
                    a[i]=a[j];
                    a[j]=temp;
                }
            }
        }
        return a;
    }//end selectionsort

    public static void main(String[] args) {

        //todo: do experiments as per assignment instructions
    }

 }//end class

