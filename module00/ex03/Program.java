import java.util.Scanner;

public class Program {

        static  void displyTheChart(long number)
        {
            long res;
            int  j = 1;
            while(number > 0)
            {
                res = number % 10;
                int i = 0;
                System.out.print("      Week " + j + " ");
                while(i < res)
                {
                    System.out.print("=");
                    i++;
                }
                System.out.print(">");
                System.out.println("");
                j++;
                number /= 10;
            }
        }

    public static void  main(String arg[])
    {
        long    holder = 0;
        int currentWeek = 0;
        long  multiplier = 1 ;


        Scanner  scanner = new Scanner(System.in);

        while (true)
        {
            String str;
            int  n; 
            System.out.print("->    ");
            str = scanner.next();
            
            if(str.equals("42"))
            {
                displyTheChart(holder);
                break;
            }
            if(str.equals("Week"))
            {
                int week = scanner.nextInt();
                if(currentWeek == 0)
                {
                    currentWeek = week;
                    if(currentWeek != 1)
                    {
                        System.out.println("IllegalArgument");
                        System.exit(-1);;
                    }

                }

                else if(week - currentWeek != 1)
                {
                    System.out.println("IllegalArgument");
                    System.exit(-1);;
                }
                
                System.out.print("->    ");
                int i = 0;
                int min = scanner.nextInt();
                if (min < 1 || min > 9) {
                    System.out.println("IllegalArgument");
                    System.exit(-1);
                }
                while(i < 4)
                    {
                        
                        n = scanner.nextInt();
                        if (n < 1 || n > 9) {
                            System.out.println("IllegalArgument");
                            System.exit(-1);
                        }
                        if (min > n)
                            min = n;
                        i++;
                    }
                    holder = holder + (min * multiplier);
                    multiplier *= 10;
                    currentWeek = week;

                    if (currentWeek == 18) {
                        displyTheChart(holder);
                        break;
                    }
                }
                else
                {
                    System.out.println("IllegalArgument");
                    System.exit(-1);
                }
                    
            }
    } 
 }