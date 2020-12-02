import java.util.*;

public class ans3{
	public static int stringCompare(String str1, String str2) 
	{ 
		for (int i = 0; i < str1.length() &&  
				i < str2.length(); i++) { 
			if ((int)str1.charAt(i) ==  (int)str2.charAt(i)) { 
				continue; 
			}  
			else { 
				return (int)str1.charAt(i) -  (int)str2.charAt(i); 
			} 
		} 

		if (str1.length() < str2.length()) { 
			return (str1.length()-str2.length()); 
		}  
		else if (str1.length() > str2.length()) { 
			return (str1.length()-str2.length()); 
		} 

		else { 
			return 0; 
		} 
	} 

    public static void main(String[] args){
    	String[] arr;
    	Scanner sc = new Scanner ( System.in ) ; 
    	int n;
    	System.out.println("Enter the size of array");
    	n = sc.nextInt();
    	n=n+1;
    	arr = new String[n];
    	System.out.println("Enter the elements : ");
    	for(int i = 0 ; i < n ; i++ ) {
    		arr[i] = sc.nextLine();
    	}
    	
    	for(int i = 0 ; i < n ; i++ ) {
    		for(int j = i+1 ; j < n ; j++ ) {
    			if(stringCompare(arr[i],arr[j]) > 0) {
    				String t=arr[j];
    				arr[j]=arr[i];
    				arr[i]=t;
    			}
    			
    			else
    				continue;
    		}
    	}
		System.out.println("Sorted Array : - ");
    	for(int i=0;i<n;i++) {
			System.out.print(arr[i] + "   ");
    	}
    	
    }
}