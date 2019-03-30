
import java.util.Iterator;

/**
 * A class that implements the ADT undirected graph.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 4.0
 */
public class UndirectedGraph<T> extends SocialNetwork<T> implements BasicGraphInterface<T> {
	public UndirectedGraph() {
		super();
	} // end default constructor

	public boolean addEdge(T u1, T u2) {
		return super.addEdge(u1, u2) && super.addEdge(u2, u1);
		// Assertion: edge count is twice its correct value due to calling addEdge twice
	} // end addEdge

	public int getNumberOfEdges() {
		return super.getNumberOfEdges() / 2;
	} // end getNumberOfEdges

} // end UndirectedGraph

// To make addEdge more efficient, DirectedGraph needs to provide accessors
// to its data fields. (See Project 3, Chapter 29.)