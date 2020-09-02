import java.util.*;

public class ans2{

	  static void countSort(int[] arr) 
	    { 
	        int max = 20;
	        int min = 0; 
	        int range = 21; 
	        int count[] = new int[range]; 
	        int output[] = new int[arr.length]; 
	        for (int i = 0; i < arr.length; i++) { 
	            count[arr[i]]++; 
	        } 
	  
	        for (int i = 1; i < count.length; i++) { 
	            count[i] += count[i - 1]; 
	        } 
	  
	        for (int i = arr.length - 1; i >= 0; i--) { 
	            output[count[arr[i]] - 1] = arr[i]; 
	            count[arr[i]]--; 
	        } 
	  
	        for (int i = 0; i < arr.length; i++) { 
	            arr[i] = output[i]; 
	        } 
	    } 
	  
	    static void printArray(int[] arr) 
	    { 
	        for (int i = 0; i < arr.length; i++) { 
	            System.out.print(arr[i] + " "); 
	        } 
	        System.out.println(""); 
	    } 
	  
	    // Driver code 
	    public static void main(String[] args) 
	    { 
	        Scanner sc=new Scanner(System.in);
	    	
	        System.out.println("Enter the size of input array");
	        int n=sc.nextInt();
	        int[] arr =new int[n];
	        for(int i=0;i<n;i++){
	        	arr[i]=sc.nextInt();
	        }
	        countSort(arr); 
	        printArray(arr); 
	    } 

}
