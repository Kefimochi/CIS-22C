import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDictionary<K, V> implements DictionaryInterface<K, V> {
	private Node firstNode; // Reference to first node of chain
	private int numberOfEntries;
	
	
	private class Node {
		private K key;
		private V value;
		private Node next;
		
		private Node(K searchKey, V dataValue) {
			key = searchKey;
			value = dataValue;
			next = null; 
		} 
		
		private Node(K searchKey, V dataValue, Node nextNode) {
			key = searchKey;
			value = dataValue;
			next = nextNode; 
		}
		
		private K getKey() {
			return key;
		}
		
		private V getValue() {
			return value;
		} 
		
		private void setValue(V newValue) {
			value = newValue;
		} 
		
		private Node getNextNode() {
			return next;
		} 
		
		private void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}
	
	private class KeyIterator implements Iterator<K> {
		private Node nextNode;
	
		private KeyIterator() {
			nextNode = firstNode;
		} 
		
		public boolean hasNext() {
			return nextNode != null;
		} 
		
		public K next() {
			K result;
			
			if (hasNext()) {
				result = nextNode.getKey();
				nextNode = nextNode.getNextNode();
			} else {
				throw new NoSuchElementException();
			} 
			return result;
		} 
		
		public void remove() {
			throw new UnsupportedOperationException();
		} 
	} 

	private class ValueIterator implements Iterator<V> {
		private Node nextNode;
	
		private ValueIterator() {
			nextNode = firstNode;
		} 
	
		public boolean hasNext()  {
			return nextNode != null;
		} 
	
		public V next() {
			V result;
	
			if (hasNext()) {
				result = nextNode.getValue();
				nextNode = nextNode.getNextNode();
			} else {
				throw new NoSuchElementException();
			}
			return result;
		} 
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		} 
	} 

	 
	
	@Override
	public V add(K key, V value) {
		V result = null;
		Node currentNode = firstNode;
		Node nodeBefore = null;
		
		while ((currentNode != null) && key.compareTo(currentNode.getKey()) > 0) {
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		}
		if ((currentNode != null) && key.equals(currentNode.getKey())) {
			result = currentNode.getValue();
			currentNode.setValue(value);
		} else {
			Node newNode = new Node(key, value);
			if (nodeBefore == null) {
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			} else {
				newNode.setNextNode(currentNode);
				nodeBefore.setNextNode(newNode);
			}
			numberOfEntries++;
		}
		return result;
	}
	
	@Override
	public V remove(K key) {
		V result = null;
		if (!isEmpty()) {
			Node currentNode = firstNode;
			Node nodeBefore = null;
			
			while ((currentNode != null) && !key.equals(currentNode.getKey())) {
				nodeBefore = currentNode;
				currentNode = currentNode.getNextNode();
			}
			
			if (currentNode != null) {
				Node nodeAfter = currentNode.getNextNode();
				if (nodeBefore == null) firstNode = nodeAfter;
				else nodeBefore.setNextNode(nodeBefore);
				
				result = currentNode.getValue();
				numberOfEntries--;
			}
		}
		return result;
	}
	
	@Override
	public V getValue(K key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}
	
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}
	
	public boolean isEmpty() {
		return (numberOfEntries == 0);
	}

	public int getSize() {
		return numberOfEntries;
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	} 
	
	private void initializeDataFields() {
		firstNode = null;
		numberOfEntries = 0;
	}

} // end LinkedDictionary