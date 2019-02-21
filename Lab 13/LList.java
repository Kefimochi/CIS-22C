
import java.io.*;

/** A linked implementation of the ADT List.
 * 
 * This code is from Chapter 14 of 
 * Data Structures and Abstractions with Java 4/e
 *      @author Frank M. Carrano 
 * 
 * Modifications were made by Charles Hoot:
 * The toString method is overwritten to give a nice display of the items in
 * the list in this format { <1> <2> <3> <4> }
 * 
 * An alternate display method has been created to print the list one item 
 * to a line along with the index
 * 
 * Stubs were added for the methods needed to complete Lab 13
 * 
  * @version 4.0
 */
class LList<T> implements ListInterface<T> {

    private Node firstNode;  // Reference to first node of chain
    private int numberOfEntries;

    public LList() {
        initializeDataFields();
    }

    public void clear() {
        initializeDataFields();
    }
    
    // Initialize the class's data fields to indicate an empty list.
    private void initializeDataFields() {
        firstNode = null;
        numberOfEntries = 0;
    }

    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            firstNode = newNode;
        } else {
            Node lastNode = getNodeAt(numberOfEntries);
            lastNode.setNextNode(newNode); // Make last node reference new node
        }
        numberOfEntries++;
    }

    public void add(int newPosition, T newEntry) {
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            Node newNode = new Node(newEntry);
            if (newPosition == 1) {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            } else { 
                Node nodeBefore = getNodeAt(newPosition - 1);
                Node nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            }
            numberOfEntries++;
        } else {
             throw new IndexOutOfBoundsException("Illegal position given to add operation.");
        }
    } 

    public T remove(int givenPosition) {
        T result = null; 
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            if (givenPosition == 1) { // Case 1: remove first entry
                result = firstNode.getData(); // Save entry to be removed
                firstNode = firstNode.getNextNode(); // Remove entry
            } else { // Case 2: Not first entry
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData(); // Save entry to be removed
                Node nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);  // Remove entry
            }
            numberOfEntries--;  
            return result;
        } 
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");

    } 

    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData())) {
                found = true;
            } else {
                currentNode = currentNode.getNextNode();
            }
        } 
        return found;
    } 

    public T getEntry(int givenPosition) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            return  getNodeAt(givenPosition).getData();
        } else 
             throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
    } 

    public T replace(int givenPosition, T newEntry) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            Node desiredNode = getNodeAt(givenPosition);
            T originalEntry = desiredNode.getData();
            desiredNode.setData(newEntry);
            return originalEntry;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
       }
    }

    public int getLength() {
        return numberOfEntries;
    }

    public boolean isEmpty() {
        boolean result;
        if (numberOfEntries == 0) 
        {
            assert firstNode == null;
            result = true;
        } else {
            assert firstNode != null;
            result = false;
        }
        return result;
    } 

    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        }
        return result;
    }

    // Return a reference to the node at a given position.
    // Precondition: List is not empty;
    //              1 <= givenPostion <= numberOfEntries
    private Node getNodeAt(int givenPosition) {
        assert (firstNode != null)
                && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
        Node currentNode = firstNode;
        // Traverse the chain to locate the desired node
        for (int counter = 1; counter < givenPosition; counter++) {
            currentNode = currentNode.getNextNode();
        }
        assert currentNode != null;
        return currentNode;
    } // end getNodeAt

    private class Node {

        private T data; // entry in bag
        private Node next; // link to next node

        public Node(T dataPortion) {
            this(dataPortion, null);
        } // end constructor

        public Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } // end constructor

        public T getData() {
            return data;
        } // end getData

        public void setData(T newData) {
            data = newData;
        } // end setData

        public Node getNextNode() {
            return next;
        } // end getNextNode

        public void setNextNode(Node nextNode) {
            next = nextNode;
        } // end setNextNode
    } // end Node

    /** Build a string representation of the list.
     *
     * @return A string showing the state of the list.
     */
    public String toString() {
        String result = "{ ";
        Node currentNode = firstNode;
        while (currentNode != null) {

            result = result + "<" + currentNode.data + "> ";
            currentNode = currentNode.next;

        }
        result = result + "}";

        return result;
    }

    /** Display the list with indices one to a line
     *  This will correctly display an infinite list,
     *  whereas the toString() method will never return.
     * 
     */
    public void display() {
        int index = 1;
        Node currentNode = firstNode;

        while (currentNode != null) {
            System.out.println(index + ":" + currentNode.getData());

            currentNode = currentNode.getNextNode();
            index++;
        }

    } // end display

    
   /** Check to see if two lists are the same.  
    * @param aList Another linked list to check this list against.
    * @return True if all the items in this list and the other list are equals.
    */
    public boolean equals(LList<T> aList) {
        boolean isEqual = false; // result of comparison of lists

        Node currOne = firstNode;
        Node currTwo = aList.firstNode;
        int counter;

        if (numberOfEntries == aList.numberOfEntries) {
            // Lists have equal lengths, so traverse both and compare items as you go:
            // (NOTE: loop is skipped if lists are empty)

            while ((currOne != null) && (currOne.getData()).equals(currTwo.getData())) {
                currOne = currOne.getNextNode();
                currTwo = currTwo.getNextNode();
            } // end while

            // If we made it to the end, the lists are equal
            isEqual = (currOne == null);
        }

        return isEqual;
    } // end equals

    
        
    /*********************************************************************
     * 
     * METHODS TO BE COMPLETED
     * 
     ***********************************************************************/
     

    /** Reverse the order of items in a list.
     */
    public void reverse() {
        Node previousNode = null;
        Node currentNode = firstNode; //first node
        Node temp;

        for (int i = 0; i < numberOfEntries; i++) {
            temp = currentNode.getNextNode(); //2ndnode
            currentNode.setNextNode(previousNode); 
            previousNode = currentNode;
            currentNode = temp;
        }
        firstNode = previousNode;
    }

    /** Cycle the first item to the end of the list.
     */
    public void cycle() {
        assert (numberOfEntries <= 0);
        if (numberOfEntries == 1) {   
        } else if (numberOfEntries == 2) {
            Node lastNode = firstNode.getNextNode();
            lastNode.setNextNode(firstNode);
            firstNode.setNextNode(null);
            firstNode = lastNode;
        } else {
            Node lastNode = firstNode;
            Node secondNode = firstNode.getNextNode();

            while (lastNode.getNextNode() != null) {
                lastNode = lastNode.getNextNode();
            }
            lastNode.setNextNode(firstNode);
            firstNode.setNextNode(null);
            firstNode = secondNode;
        }
    }
}
