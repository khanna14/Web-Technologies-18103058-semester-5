public class ans4{
    public static void main(String[] args){
        int start=1;
        long sum = 1;
        while(start<Integer.MAX_VALUE){
            if(sum == (long)start*(long)start ){
            	if(start==1)
                System.out.println("Answer="+start);
            	else
                break;
            }
            
            start+=1;
            sum+=start;
        }
        if(sum!=(long)start*(long)start)
            System.out.println("Sum= " +sum+ ",n= " +start + " i.e limitation of system but not a solution");
        if(sum==(long)start*(long)start)
        	System.out.println("Sum= " +sum+ ",n= " +start);
    }
}