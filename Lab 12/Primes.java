import java.io.*;
import java.util.*;

/**
 * Primes is a program that will compute prime numbers using the sieve of Eratosthenes.
 * 
 * @author Charles Hoot
  * @version 4.0
 */

    
public class Primes {

    public static void main(String args[]) {
        System.out.println("Please enter the maximum value to test for primality");
        int maxValue = getInt("It should be an integer value greater than or equal to 2.");

        AList<Integer> candidates = new AList(maxValue);
        AList<Integer> primes = new AList(maxValue);
        AList<Integer> composites = new AList(maxValue);

        for (int i = 1; i <= maxValue - 1; i++) {
            candidates.add(i, i + 1);
        }

        System.out.println("Printing out the candidates:");
        System.out.println(candidates.toString() + "\n");

        System.out.println("Printing out discovered primes:");
        while(!candidates.isEmpty()) {
            Integer tempPrime = candidates.remove(1);
            System.out.println("Discovered prime: " + tempPrime);
            primes.add(tempPrime); 
            getComposites(candidates, composites, tempPrime);
        }

        System.out.println();

        System.out.println("Printing composites list:");
        System.out.println(composites.toString());

        System.out.println();

        System.out.println("Printing primes list:");
        System.out.println(primes.toString());

        System.out.println();

        System.out.println("Printing candidates list:");
        System.out.println(candidates.toString());
        System.out.println("\nCandidates list has to be empty");
    }
    
    /**
     * getComposites - Remove the composite values from candidates list and
     * put them in the composites list.
     *
     * @param  candidates   A list of integers holding the possible values.
     * @param  composites   A list of integers holding the composite values.
     * @param  prime   An Integer that is prime.
     */
    public static void getComposites(ListInterface<Integer> candidates,
        ListInterface<Integer> composites, Integer prime) {

        for (int i = 1; i <= candidates.getLength(); i++) {
            if (candidates.getEntry(i) % prime == 0) {
                composites.add(candidates.remove(i)); //'remove' returns T value of a removed element
            }
        }
    }

    /**
     * Get an integer value.
     * @return     An integer. 
     */
    private static int getInt(String rangePrompt) {
        Scanner input;
        int result = 10;        //Default value is 10
        try {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();  
        }
        catch(NumberFormatException e) {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }        
        catch(Exception e) {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;             
    }    
}
