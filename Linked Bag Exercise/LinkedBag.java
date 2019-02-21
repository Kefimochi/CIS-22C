public class LinkedBag <T> implements BagInterface<T> {
    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    } 

    private Node getReferenceTo(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }
        if (!found) { return null; }
        return currentNode;
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T data) {
            this(data, null);
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        private T getData() {
            return data;
        }

        private void setData(T data) {
            this.data = data;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node next) {
            this.next = next;
        }

    }

	public int getCurrentSize() {
        return numberOfEntries;
    }
	
	public boolean isEmpty() {
        assert (numberOfEntries < 0);
        if (numberOfEntries == 0) {
            return true;
        } else {
            return false;
        }
    }
	
	public boolean add(T newEntry) {
        Node newNode = new Node(newEntry, firstNode);
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }

	public T remove() {
        if (numberOfEntries == 0) {
            return null;
        }
        T referencedData = firstNode.getData();
        firstNode = firstNode.getNextNode();
        numberOfEntries--;
        return referencedData;
    }
   
   public boolean remove(T anEntry) {
        assert (numberOfEntries > 0);
        if ((numberOfEntries) == 0) { return false; }
        Node desiredNode = getReferenceTo(anEntry);
        if (desiredNode == null) { return false; }
        desiredNode.setData(firstNode.getData());
        remove();
        return true;
   }
	
	public void clear() {
        while (numberOfEntries > 0) {
            remove();
        }
    }

	public int getFrequencyOf(T anEntry) {
        assert !isEmpty();
        int counter = 0;
        Node temp = firstNode;
        for (int i = 0; i < numberOfEntries; i++) {
            if(temp.getData() == anEntry) {
                counter++;
            }
            temp = temp.getNextNode();
        }

        return counter;
    }
	
	public boolean contains(T anEntry) {
        assert !isEmpty();
        Node temp = firstNode;
        for (int i = 0; i < numberOfEntries; i++) {
            if (temp.getData() == anEntry) {
                return true;
            }
            temp = temp.getNextNode();
        }
        return false;
    }

	public T[] toArray() {
        assert !isEmpty();
        T[] result = (T[]) new Object[numberOfEntries];
        Node temp = firstNode;
        for (int i = 0; i < numberOfEntries; i++) {
            result[i] = temp.getData();
            temp = temp.getNextNode();
        }
        return result;
    }

}