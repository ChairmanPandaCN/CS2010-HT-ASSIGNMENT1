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
    public static final int CUTOFF =1;
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
     * 
     **/
    static void insertionForMergeSort(double[]a ,int start,int end) {
    	for(int i=start+1;i<=end;i++) {
    		for(int j=i;j>start;j--) {
    			if(a[j]<a[j-1]) {
    				double temp =a[j-1];
    				a[j-1]=a[j];
    				a[j]=temp;
    			}
    		}
    	}
    }
    static double[] ImprovedmergeSortRecursive(double a[]) {
    	int end=a.length-1;
    	ImprovedSplit(a, 0, end);
    	return a;
    }
    static void ImprovedSplit(double a[],int start,int end) {
        if(start<end) {    
            if(end<=start+CUTOFF-1) {
            	insertionForMergeSort(a,start,end);
            	return;
            }
        	int middle=start+(end-start)/2;
    	    ImprovedSplit(a, start, middle);
    		ImprovedSplit(a, middle+1, end);
    		if(a[middle]<=a[middle+1]) {
    			return;
    		}
    		ImprovedMerge(a, start, middle, end);
    	}
    }
    
    static void ImprovedMerge(double a[],int start,int middle,int end) {
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

    public static void main(String[] args) {
    	double[] arr = new double[1000000];
	    double[] arr2 = new double[1000000];
	    double[] arr3 = new double[1000000];
	    double[] arr4 = new double[1000000];
	    for(int count=0;count<arr.length;count++) {
	    	arr[count]=(int)((Math.random()*9+1)*1000000);
	    	arr2[count]=arr[count];
	    	arr3[count]=arr[count];
	    	arr4[count]=arr[count];
	    }
//	    System.out.println("The test array:");
//	    for(int i=0;i<arr.length;i++){
//	        System.out.print(arr[i]+" ");
//        }
//	    System.out.print("\n");
	    
        
	    long startTime=System.currentTimeMillis();   //start   
	    mergeSortRecursive(arr); 
        long endTime=System.currentTimeMillis(); //end  
        System.out.println("MergeSortRecursion-running time "+(endTime-startTime)+"ms");
        
        startTime=System.currentTimeMillis();//start;
        ImprovedmergeSortRecursive(arr2);
        endTime = System.currentTimeMillis();//end;
        System.out.println("ImprovedMergeSortRecursive-running time "+(endTime-startTime)+"ms");
        
//        System.out.println("The sorted array should be:");
//        for(int i=0;i<arr.length;i++) {
//        	System.out.print(arr[i]+" ");
//        }
//        System.out.println("");
        
//        startTime=System.currentTimeMillis();//start;
//        insertionSort(arr2);
//        endTime = System.currentTimeMillis();//end;
//        System.out.println("InsertionSort-running time "+(endTime-startTime)+"ms");
//        
//        startTime=System.currentTimeMillis();//start;
//        selectionSort(arr3);
//        endTime =System.currentTimeMillis();//end
//        System.out.println("SelectionSort-running time "+(endTime-startTime)+"ms");
        
        //todo: do experiments as per assignment instructions
    }

 }//end class

