import java.io.*;
import java.util.*;
    
public class CountingGame {

    public static void main(String args[]) {
        ListInterface<Integer> players = null;
        ListInterface<String> rhyme = null;
        int position = 1;
        int max;
        
        System.out.println("Please enter the number of players.");
        max = getInt("It should be an integer value greater than or equal to 2."); 
        System.out.println("\nConstructing list of players.");
        AList<Integer> tempList = new AList(max);
        players = tempList;

        System.out.print("The players list is: \n");
        for (int i = 1; i <= max; i++) { players.add(i); }
        System.out.println(players.toString());
        rhyme = getRhyme();

        while (players.getLength() > 1) {
            position = doRhyme(players, rhyme, position);
            System.out.print("The new players list is: \n");
            System.out.println(players.toString());
        }
        
        System.out.println("The winner is " + players.getEntry(1));
    }
    
    
    /**
     * Do the rhyme with the players in the list and remove the selected
     * player.
     *
     * @param  players   A list holding the players.
     * @param  rhyme   A list holding the words of the rhyme.
     * @param  startAt A position to start the rhyme at.
     * 
     * @return The position of the player eliminated.
     */
   public static int doRhyme(ListInterface<Integer> players, ListInterface<String> rhyme, int startAt) {
        assert (startAt >= 1);
        System.out.println("\nHere are players playing the game: \n");
        int counterPlayers = startAt;
        int counter = 1;
        
        while (rhyme.getLength() != counter - 1) {
            System.out.println("Player " + counterPlayers + ": " + rhyme.getEntry(counter));
            if (counterPlayers == players.getLength()) {
                counterPlayers = 1;
            } else {
                counterPlayers++;
            }
            counter++; //would be 4, then loop'd stop
        }

        System.out.println("counterPlayers = " + counterPlayers);
        if (counterPlayers == 1) {
            System.out.println("\nRemoving this player from the list: " + players.remove(players.getLength()));
            return 1;
        } else {
            System.out.println("\nRemoving this player from the list: " + players.remove(counterPlayers - 1));
            return counterPlayers - 1;
        }
    }
    
    
    
    /**
     * Get an integer value.
     *
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
    
    /**
     * getRhyme - Get the rhyme.
     *
     * @return    A list of words that is the rhyme.
     */
    private static ListInterface<String> getRhyme() {
        Scanner input;
        String inString = "";
        ListInterface<String> rhyme = new AList<String>();
        
        try {
            input = new Scanner( System.in );
            
            System.out.println("\nPlease enter a rhyme");
            inString = input.nextLine().trim();
            
            Scanner rhymeWords = new Scanner(inString);
            while(rhymeWords.hasNext()) {
                rhyme.add(rhymeWords.next());
            }
        }
        catch(Exception e) {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use a rhyme of size one");
        }

        // Make sure there is at least one word in the rhyme
        if(rhyme.getLength() < 1) {
            rhyme.add("Default");
        }   
        return (ListInterface<String>)rhyme;        
    }
}
