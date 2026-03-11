import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    System.out.print("-> ");
    if (!scanner.hasNextInt()) return; 
    int n = scanner.nextInt();

    if(n <= 1) {
      System.out.println("IllegalArgument");
      System.exit(-1);
    }
    
    int i = 2;
    while(i * i <= n) {
      if(n % i == 0) {
        System.out.println("false " + (i - 1));
        return; 
      }
      i++;
    }
    System.out.println("true " + (i - 1));
  }
}