import java.util.*;
import java.lang.String;

public class Palindrome {
	
	public static void main(String[] args) {
        String str;
        Palindrome pal = new Palindrome();
        
        System.out.println("Please enter any one word string. Only the first word will be captured if it will be longer.");
        str = getString();
        System.out.println("You entered: " + str);

        String[] cs = str.toLowerCase().split("(?!^)");
        for (int i = 0; i < cs.length; i++) {
            System.out.println("Entry: " + cs[i]);
        }
        System.out.println("\nChecking if the string is palindrome...");
        
        if (pal.isPalindrome(cs) == true) {
            System.out.println("Congrats! Your word is a palindrome\n");
        } else {
            System.out.println("Unfortunately the word you typed is not a palindrome\n");
        }
    }
    
    private static String getString() {
        Scanner input;
        String result = "Default";
        try {
            input = new Scanner(System.in);
            result = input.next();
        }       
        catch(Exception e) {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 'default' as the default value");
        }
        return result;                           
    }

    private boolean isPalindrome(String[] str) {
        QueueInterface<String> programQueue = new LinkedQueue<String>();
        StackInterface<String> programStack = new LinkedStack<String>();

        for (int i = 0; i < str.length; i++) {
           programQueue.enqueue(str[i]);
           programStack.push(str[i]);
        }

        for (int i = 0; i < str.length; i++) {
            String a = programQueue.dequeue();
            String b = programStack.pop();
            if (!a.equals(b)) {
                return false;
            } 
        }
        return true;
    }
}
