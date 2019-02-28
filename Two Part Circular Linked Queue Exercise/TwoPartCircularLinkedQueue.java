public final class TwoPartCircularLinkedQueue<T> implements QueueInterface<T> { 
	private Node queueNode; // References first node in queue
	private Node freeNode; // References node after back of queue
	
	public TwoPartCircularLinkedQueue() {
		freeNode = new Node(null, null);
		freeNode.setNextNode(freeNode);
		queueNode = freeNode;
	}
	
	class Node {
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
		
		private Node getNextNode() {
			return next;
		}
		
		private void setData(T data) {
			this.data = data;
		}
		
		private void setNextNode(Node next) {
			this.next = next;
		}
	
	}
	
	public void enqueue(T newEntry) {
		freeNode.setData(newEntry);
		if (isChainFull()) {
			Node newNode = new Node(null, freeNode.getNextNode());
			freeNode.setNextNode(newNode);
		}
		freeNode = freeNode.getNextNode();
	}
	
	public T dequeue() {
		T front = getFront();
		assert !isEmpty();
		queueNode.setData(null);
		queueNode = queueNode.getNextNode();
		return front;
	}
	
	public T getFront() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			return queueNode.getData();
		}
	}

	public boolean isEmpty() {
		return queueNode == freeNode;
	}

	public void clear() {
		while (!isEmpty()) {
			dequeue();
		}
	} 
	
	public boolean isChainFull() {
		return queueNode == freeNode.getNextNode();
	}
}