public class LinkedDeque<T> implements DequeInterface<T> {
	private DLNode firstNode; // References node at front of deque
	private DLNode lastNode; // References node at back of deque
	
	public LinkedDeque() {
		firstNode = null;
		lastNode = null;
	}
	
	private class DLNode {
		private T data; // Deque entry
		private DLNode next; // Link to next node
		private DLNode previous; // Link to previous node
	
		private DLNode(T dataPortion) {
	    data = dataPortion;
	    next = null; 
	    previous = null; 
	  } 
	
	  private DLNode(DLNode previousNode, T dataPortion, DLNode nextNode) {
  		data = dataPortion;
	    next = nextNode; 
	    previous = previousNode;
	  }

    public T getData() {
		  return data;
	  } 
	
	  public void setData(T newData) {
	    data = newData;
	  } 
	
	  public DLNode getNextNode() {
	    return next;
	  } 
	
	  public void setNextNode(DLNode nextNode) {
	    next = nextNode;
	  } 
	
	  public DLNode getPreviousNode() {
	  	return previous;
	  } 
	
	   public void setPreviousNode(DLNode previousNode) {
	    previous = previousNode;
	  } 
	}

	public void addToFront(T newEntry) {
		DLNode newNode = new DLNode(null, newEntry, firstNode);
		if (isEmpty()) {
			lastNode = newNode;
		} else {
			firstNode.setPreviousNode(newNode);
		}
		firstNode = newNode;
	}

	public void addToBack(T newEntry) {
		DLNode newNode = new DLNode(lastNode, newEntry, null);
		if (isEmpty()) {
			firstNode = newNode;
		} else {
			lastNode.setNextNode(newNode);
		}
		lastNode = newNode;
	}

	public T removeFront() {
		T front = getFront();
		assert firstNode != null;
		firstNode = firstNode.getNextNode();
		if (firstNode == null) {
			lastNode = null;
		} else {
			firstNode.setPreviousNode(null);
		}
		return front;
	}

	public T removeBack() {
		T back = getBack();
		assert lastNode != null;
		lastNode = lastNode.getPreviousNode();
		if (lastNode  == null) {
			firstNode = null;
		} else {
			lastNode.setNextNode(null);
		}
		return back;
	}

	public T getFront() {
		return firstNode.getData();
	}

	public T getBack() {
		return lastNode.getData();
	}

	public boolean isEmpty() {
		return (firstNode == null) && (lastNode == null);
	}

	public void clear() {
		firstNode = null;
		lastNode = null;
	} 

} 
