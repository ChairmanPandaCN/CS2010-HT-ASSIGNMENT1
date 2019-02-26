import javax.imageio.event.IIOReadWarningListener;
import javax.security.auth.login.LoginException;

// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author
 *  @version HT 2019
 */

 class SortComparison {
    public static final int CUTOFF =15;
    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]){
    	for(int i=1;i<a.length;i++) {
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
    static double[] quickSort(double numbers[]) {
    	recursiveQuick(numbers, 0, numbers.length-1);
		return numbers;
	}

    
    	public static void recursiveQuick(double[] numbers, int lo, int hi) {
    	if(hi <= lo) {
    	return;
    	}
    	int pivotPos = partition(numbers, lo, hi);
    	recursiveQuick(numbers, lo, pivotPos-1);
    	recursiveQuick(numbers, pivotPos+1, hi);
    	}
	private static int partition(double[] numbers, int lo, int hi) {
		int i = lo;
		int j = hi+1;
		double pivot = numbers[lo];
		while(true) {
		while(numbers[++i]<pivot) {
		if(i == hi) break;
		}
		while(pivot<numbers[--j]) {
		if(j == lo) break;
		}
		if(i >= j) break;
		double temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
		}
		numbers[lo] = numbers[j];
		numbers[j] = pivot;
		return j;
		}

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
     *
import java.util.Timsort @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSortIterative (double a[]) {
    	 for(int size=1;size<a.length;size=size+size) {
    		 for(int start=0;start<a.length-size;start+=size+size) {
    			 int mid=start+size-1;
    			 int end =Math.min(start+size+size-1, a.length-1);
    			 merge(a,start,mid,end);
    		 }
    	 }
		 //todo: implement the sort
    	return a;
    }//end mergesortIterative
    
    
    
    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.does better when the inputs are nearly ordered.
     * @return after the method returns, the array must be in ascending sorted order.
     * 
     **/
//    static void insertionForMergeSort(double[]a ,int start,int end) {
//    	for(int i=start+1;i<=end;i++) {
//    		for(int j=i;j>start;j--) {
//    			if(a[j]<a[j-1]) {
//    				double temp =a[j-1];
//    				a[j-1]=a[j];
//    				a[j]=temp;
//    			}
//    		}
//    	}
//    }
//    static double[] ImprovedmergeSortRecursive(double a[]) {
//    	int end=a.length-1;
//    	improvedSplit(a, 0, end);
//    	return a;
//    }
//    static void improvedSplit(double a[],int start,int end) {
//        if(start<end) {    
//            if(end<=start+CUTOFF) {
//            	insertionForMergeSort(a,start,end);
//            	return;
//            }
//        	int middle=start+(end-start)/2;
//    	    improvedSplit(a, start, middle);
//    		improvedSplit(a, middle+1, end);
//    		if(a[middle]<=a[middle+1]) {
//    			return;
//    		}
//    		merge(a, start, middle, end);
//    	}
//    }
//    
    static double[] mergeSortRecursive(double a[]) {
    	int end=a.length-1;
    	split(a, 0, end);
    	return a;
    }
    static void split(double a[],int start,int end) {
        if(start<end) {    
        	int middle=start+(end-start)/2;
    	    split(a, start, middle);
    		split(a, middle+1, end);
    		if(a[middle]<=a[middle+1]) {
    			return;
    		}
    		merge(a, start, middle, end);
    	}
    }
    
    static void merge(double a[],int start,int middle,int end) {
    	int firstHalf=middle-start+1;
    	int secondHalf=end-middle;
    	
    	double[] left = new double[firstHalf];
    	double[] right = new double[secondHalf];
    	
    	for(int i=0;i<firstHalf;i++) {
    		left[i]=a[start+i];
    	}
    	for(int j=0;j<secondHalf;j++) {
    		right[j]=a[middle+1+j];
    	}
    	
    	int i=0;//index of left subarray
    	int j=0;//index of right subarray
    	int k=start;//index of aux array
    	
    	while(i<firstHalf&&j<secondHalf) {
    		if(left[i]<=right[j]) {
    			a[k]=left[i];
    			i++;
    		}else {
    			a[k]=right[j];
    			j++;
    		}
    		k++;
    	}
    	
    	while(i<firstHalf) {
    		a[k]=left[i];
    		i++;
    		k++;
    	}
    	while(j<secondHalf) {
    		a[k]=right[j];
    		j++;
    		k++;
    	}	
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

 }//end class

