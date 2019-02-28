import java.util.EmptyStackException;


public final class LinkedStack<T> implements StackInterface<T> {
	private Node topNode;
	
	public LinkedStack() {
		topNode = null;
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
		
		private void setData(T data) {
			this.data = data;
		}
		
		private T getData() {
			return data;
		}
		
		private void setNextNode(Node next) {
			this.next = next;
		}
		
		private Node getNextNode() {
			return next;
		}
		
	}
	
	public void push(T newEntry) {
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	}
	
	public T pop() {
		T top = peek();
		assert topNode != null;
		topNode = topNode.getNextNode();
		return top;
	}
	
	public boolean isEmpty() {
		return topNode == null;
	}
	
	public void clear() {
		topNode = null;
	}

	public T peek() {
		if (topNode != null) {
			return topNode.getData();
		} else {
			throw new EmptyStackException();
		}
	}
}
