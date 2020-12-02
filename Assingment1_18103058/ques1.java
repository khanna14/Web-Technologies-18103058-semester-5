import java.util.Scanner;

public class ques1
{
	public static void main(String[] args)
	{
		System.out.println("Enter string");
		Scanner scan= new Scanner(System.in);
		String str,sub_str;
		str=scan.nextLine();
		
		System.out.println("Enter substring");
		sub_str=scan.next();

		int k1=str.length();
		int k2=sub_str.length();
		int z=0,ans=0;
		
		if(k1<k2)
		System.out.println("0");
		
		else {
			int [] countStr;
			int [] countSubstr;
			countStr = new int[150];
			countSubstr = new int[150];
				for (int i=0;i<k2;i++)
					 countSubstr[(int)sub_str.charAt(i)]++;
				for(int i=0;i<k2;i++){
				        int k=(int)str.charAt(i);
		          		if(countStr[k]<countSubstr[k])
			            		z++;
		          		countStr[k]++;

				          if(z==k2)
				            ans++;}
	
				          for(int i=k2;i<k1;i++){
	
				        int k=(int)str.charAt(i);
		        		int p=(int)str.charAt(i-k2);
	
				        if(countStr[p]<=countSubstr[p])
			        		z--;

				        countStr[p]--;

				        if(countStr[k]<countSubstr[k])
					        z++;

				        countStr[k]++;

				        if(z==k2)
					        ans++;}

		        System.out.println(ans);		
		}
			
	}
}
