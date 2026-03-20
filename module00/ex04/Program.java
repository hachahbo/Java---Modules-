import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("-> ");
        
        // 1. Read the input string
        String input = scanner.next(); 
        
        // 2. Convert to char array (per the allowed methods list)
        char[] chars = input.toCharArray();
        
        // 3. Create the massive frequency matrix (65536 possible characters)
        int[] frequencies = new int[65536];
        
        // 4. THE SINGLE ITERATION RULE: 
        // We loop through the text exactly one time.
        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            
            // We use the character itself as the index! 
            // If currentChar is 'A', this automatically updates frequencies[65].
            frequencies[currentChar]++; 
        }
        
        // --- End of Phase 1 ---
        // Right now, your 'frequencies' array perfectly holds the counts 
        // of every single character typed, sorted automatically from 0 to 65535.
    }
}