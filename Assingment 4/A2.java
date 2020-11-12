class A2
{
    public static void main(String[] args)
    {
        int num = -1;
        byte b = (byte)num;
        char c = (char)b;
        int final_num = c;
        System.out.println("Initial number is: " + num);
        System.out.println("Number after converting to byte: " + b);
        System.out.println("Byte after being converted to character: " + c);
        System.out.println("Final number is: " + final_num);
    }
}
