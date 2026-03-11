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
            currentWeek = 0;
            if(str.equals("Week"))
            {
                int week = scanner.nextInt();
                if(week - currentWeek != 1)
                {
                    System.out.println("IllegalArgument");
                    break;
                }

                System.out.print("->    ");
                int i = 0;
                int min = scanner.nextInt();
                while(i < 4)
                {
                    n = scanner.nextInt();
                    if (min > n)
                        min = n;
                    i++;
                }
                holder = (holder * 10) + min;
                currentWeek = week;
            }
        }
    } 
 }