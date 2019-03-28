
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class of vertices for a graph.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 4.0
 */
class User<T> implements UserInterface<T> {
   private ListWithIteratorInterface<Edge> edgeList; // Edges to neighbors
   private T password;
   private T status;
   private T name;
   private T img;

   public User(T vertexLabel) {
      edgeList = new LinkedListWithIterator<>();
      password = null;
      status = null;
      name = null;
      img = null;
   }

   public T getPassword() {
      return name;
   }

   public void setPassword(T password) {
      this.password = password;
   }

   public T getStatus() {
      return status;
   }

   public void setStatus(T status) {
      this.status = status;
   }

   public T getName() {
      return name;
   }

   public void setName(T name) {
      this.name = name;
   }

   public T getImg() {
      return status;
   }

   public void setImg(T img) {
      this.img = img;
   }

   private class WeightIterator implements Iterator<Double> {
      private Iterator<Edge> edges;

      private WeightIterator() {
         edges = edgeList.getIterator();
      } // end default constructor

      public boolean hasNext() {
         return edges.hasNext();
      } // end hasNext

      public Double next() {
         Double edgeWeight = new Double(0);
         if (edges.hasNext()) {
            Edge edgeToNextNeighbor = edges.next();
            edgeWeight = edgeToNextNeighbor.getWeight();
         } else
            throw new NoSuchElementException();

         return edgeWeight;
      } // end next

      public void remove() {
         throw new UnsupportedOperationException();
      } // end remove
   } // end WeightIterator

   public Iterator<Double> getWeightIterator() {
      return new WeightIterator();
   } // end getWeightIterator

   public boolean connect(UserInterface<T> endVertex, double edgeWeight) {
      boolean result = false;

      if (!this.equals(endVertex)) {
         Iterator<UserInterface<T>> neighbors = this.getNeighborIterator();
         boolean duplicateEdge = false;

         while (!duplicateEdge && neighbors.hasNext()) {
            UserInterface<T> nextNeighbor = neighbors.next();
            if (endVertex.equals(nextNeighbor)) // if endVertex already exists in the edge list, then connected
               duplicateEdge = true;
         } // end while

         if (!duplicateEdge) {
            this.edgeList.add(new Edge(endVertex, edgeWeight));
            result = true;
         } // end if
      } // end if

      return result;
   } // end connect

   public boolean connect(UserInterface<T> endVertex) {
      return connect(endVertex, 0);
   } // end connect

   public Iterator<UserInterface<T>> getNeighborIterator() {
      return new NeighborIterator();
   } // end getNeighborIterator

   public boolean hasNeighbor() {
      return !edgeList.isEmpty();
   } // end hasNeighbor

   public boolean equals(Object other) {
      boolean result;

      if ((other == null) || (getClass() != other.getClass()))
         result = false;
      else {
         // The cast is safe within this else clause
         @SuppressWarnings("unchecked")
         User<T> otherVertex = (User<T>) other;
         result = name.equals(otherVertex.name);
      } // end if

      return result;
   } // end equals

   private class NeighborIterator implements Iterator<UserInterface<T>> {
      private Iterator<Edge> edges;

      private NeighborIterator() {
         edges = edgeList.getIterator();
      } // end default constructor

      public boolean hasNext() {
         return edges.hasNext();
      } // end hasNext

      public UserInterface<T> next() {
         UserInterface<T> nextNeighbor = null;

         if (edges.hasNext()) {
            Edge edgeToNextNeighbor = edges.next();
            nextNeighbor = edgeToNextNeighbor.getEndVertex();
         } else
            throw new NoSuchElementException();

         return nextNeighbor;
      } // end next

      public void remove() {
         throw new UnsupportedOperationException();
      } // end remove
   } // end NeighborIterator

   protected class Edge {
      private UserInterface<T> vertex; // Vertex at end of edge
      private double weight;

      protected Edge(UserInterface<T> endVertex, double edgeWeight) {
         vertex = endVertex;
         weight = edgeWeight;
      } // end constructor

      protected UserInterface<T> getEndVertex() {
         return vertex;
      } // end getEndVertex

      protected double getWeight() {
         return weight;
      } // end getWeight
   } // end Edge

   public void display() // For testing
   {
      System.out.print(name + " ");
      Iterator<UserInterface<T>> i = getNeighborIterator();
      Iterator<Double> w = getWeightIterator();

      while (i.hasNext()) {
         User<T> v = (User<T>) i.next();
         System.out.print(v + " " + w.next() + " ");

      } // end while
      System.out.println();
   } // end display
} // end Vertex