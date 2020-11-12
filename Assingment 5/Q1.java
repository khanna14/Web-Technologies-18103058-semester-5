public class Q1 extends Thread
{
    public static void main(String[] args)
    {
        q1 c = new q1();
        c.start();
    }

    public void run()
    {
        int i=0;
        for(i=1;i<101;i++)
        {
            System.out.println(i);
            try
            {
                Thread.sleep(1000);
            }catch(Exception e){}
        }
    }

}