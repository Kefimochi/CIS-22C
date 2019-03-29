
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
   private ListWithIteratorInterface<Edge> friendsList;
   public T password;
   private T status;
   private T name;
   private T img;

   public User(T vertexLabel) {
      friendsList = new LinkedListWithIterator<>();
      password = null;
      status = null;
      name = null;
      img = null;
   }

   public T getPassword() {
      return password;
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
      return img;
   }

   public void setImg(T img) {
      this.img = img;
   }

   public boolean connect(UserInterface<T> endVertex) {
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
            this.friendsList.add(new Edge(endVertex));
            result = true;
         } // end if
      } // end if
      return result;
   } // end connect

   public Iterator<UserInterface<T>> getNeighborIterator() {
      return new NeighborIterator();
   } // end getNeighborIterator

   public boolean hasNeighbor() {
      return !friendsList.isEmpty();
   } // end hasNeighbor

   private class NeighborIterator implements Iterator<UserInterface<T>> {
      private Iterator<Edge> edges;

      private NeighborIterator() {
         edges = friendsList.getIterator();
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

   public class Edge {
      private UserInterface<T> vertex; // Vertex at end of edge

      protected Edge(UserInterface<T> endVertex) {
         vertex = endVertex;
      } // end constructor

      protected UserInterface<T> getEndVertex() {
         return vertex;
      } // end getEndVertex

      protected double getWeight() {
         return 0.0;
      } // end getWeight

      protected void display() {
         Iterator<UserInterface<T>> i = getNeighborIterator();

         while (i.hasNext()) {
            User<T> v = (User<T>) i.next();
            System.out.print(v.getName() + " ");

         } // end while
         System.out.println();
      }
   } // end Edge

   public void display() // For testing
   {
      System.out.print(name + ": ");
      Iterator<UserInterface<T>> i = getNeighborIterator();

      while (i.hasNext()) {
         User<T> v = (User<T>) i.next();
         System.out.print(v.getName() + " ");

      } // end while
      System.out.println();
   } // end display

   public Iterator<User<T>.Edge> getFriendsIterator() {
      return friendsList.getIterator();
   }
} // end Vertex