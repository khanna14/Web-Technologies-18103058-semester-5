import java.util.*;

public class ans6{

	static int c; 
	  

	static int Hail(int N) 
	{ 
	    System.out.print(N + " "); 
	  
	    if (N == 1 && c == 0) { 
	  
	        return c; 
	    }  
	    else if (N == 1 && c != 0) { 
	
	        c++; 
	        return c; 
	    }  
	    else if (N % 2 == 0) { 
	  
	        c++; 
	        Hail(N / 2); 
	    }  
	    else if (N % 2 != 0) { 

	        c++; 
	        Hail(3 * N + 1); 
	    } 
	    return c; 
	} 
	  

	public static void main(String[] args) 
	{ 	
		System.out.println("Enter a +ve Number : - ");
		Scanner sc = new Scanner(System.in);
	    int N = sc.nextInt(); 
	    int x; 
	  

	    x = Hail(N); 
	
	    System.out.println(); 
	    System.out.println("Total Steps: " +x); 
	} 
}
