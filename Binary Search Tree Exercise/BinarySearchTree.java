import java.util.Iterator;
import java.util.NoSuchElementException;

import StackAndQueuePackage.*;


public class BinarySearchTree<T extends Comparable<? super T>>
extends BinaryTree<T> implements SearchTreeInterface<T> {
    public BinarySearchTree() {
        super();
    } // end default constructor

    public BinarySearchTree(T rootEntry) {
        super();
        setRootNode(new BinaryNode<>(rootEntry));
    } // end constructor

    public void setTree(T rootData) // Disable setTree (see Segment 25.6) {
        throw new UnsupportedOperationException();
    } // end setTree

    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree)
    {
        throw new UnsupportedOperationException();
    } // end setTree

    public T remove(T entry) {
        ReturnObject oldEntry = new ReturnObject(null);
        BinaryNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
        setRootNode(newRoot);

        return oldEntry.get();
    } // end remove

    private class ReturnObject {
        private T item;

        private ReturnObject(T entry) {
            item = entry;
        } // end constructor

        public T get() {
            return item;
        } // end get

        public void set(T entry) {
            item = entry;
        } // end set
    } // end ReturnObject

    public boolean contains(T entry) {
        return getEntry(entry) != null;
    }

    public T getEntry(T entry) {
        return findEntry(getRootNode(), entry);
    }

    private T findEntry(BinaryNode<T> rootNode, T entry) {
        T result = null; 
        if (rootNode != null) {
            T rootEntry = rootNode.getData();
            if (entry.equals(rootEntry)) {
                return rootEntry;
            } else if (entry.compareTo(rootEntry) < 0) { // If entry is smaller than rootEntry.
                result = findEntry(rootNode.getLeftChild(), entry);
            } else {
                result = findEntry(rootNode.getRightChild(), entry);
            }
        }
    }
    public T add(T newEntry) {
        return null;
    }
} // end BinarySearchTree