import java.util.Scanner;

public class quem
{
	public static void main(String[] args)
	{
		System.out.println("Enter string");
		Scanner scan= new Scanner(System.in);
		String m,n;
		m=scan.nextLine();
		
		System.out.println("Enter substring to be found");
		n=scan.next();

		int k1=m.length();
		int k2=n.length();
		int z=0,ans=0;
		
		if(k1<k2)
		System.out.println("0");
		
		else {
			int [] a;
			int [] b;
			a= new int[500];
			b=new int[500];
				for (int i=0;i<k2;i++)
					 b[(int)n.charAt(i)]++;
				for(int i=0;i<k2;i++){
				        int k=(int)m.charAt(i);
		          		if(a[k]<b[k])
			            		z++;
		          		a[k]++;

				          if(z==k2)
				            ans++;}
	
				          for(int i=k2;i<k1;i++){
	
				        int k=(int)m.charAt(i);
		        		int p=(int)m.charAt(i-k2);
	
				        if(a[p]<=b[p])
			        		z--;

				        a[p]--;

				        if(a[k]<b[k])
					        z++;

				        a[k]++;

				        if(z==k2)
					        ans++;}

		        System.out.println(ans);		
		}
			
	}
}
