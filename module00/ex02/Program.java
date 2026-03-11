import java.util.Scanner;


public class Program{

    static int  counter = 0;

    static boolean  isPrime(int  n)
    {
        if(n <= 1) {
            return false;
        }
    
        int i = 2;
        while(i * i <= n)
        {
            if(n % i == 0) {
                return false;
            }
            i++;
        }
        return true;
    }
    static int Sumquery(int  n)
    {
        int sum;

        sum = 0;
        while(n > 0)
        {
            sum += n % 10;
            n /= 10; 
        }
        return sum;
    }
   
    public static void  main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
         int n;
         int sum = 0;

        while(true) {
            System.out.print("-> ");
            
            if (!scanner.hasNextInt()) {
                System.exit(-1); 
            }
            
            n = scanner.nextInt();
            if (n == 42) {
                break;
            }
            sum = Sumquery(n);
            if(isPrime(sum))
            {
                counter++;
            }
        }
        System.out.println("Count of coffee-request : "+ counter);

    }
}