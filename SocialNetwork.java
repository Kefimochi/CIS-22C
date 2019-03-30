import java.util.Iterator;

public class SocialNetwork<T> implements BasicGraphInterface<T> {
    private DictionaryInterface<T, UserInterface<T>> vertices;
    private int edgeCount;

    public SocialNetwork() {
        vertices = new LinkedDictionary<>();
        edgeCount = 0;
    }

    protected void resetVertices() {
        Iterator<UserInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext()) {
            UserInterface<T> nextVertex = vertexIterator.next();
        }
    }

    // Used for testing
    public void displayEdges() {
        System.out.println("\nHere's a list of all members and their friends:");
        Iterator<UserInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext()) {
            ((User<T>) (vertexIterator.next())).display();
        }
    }

    public void displayFriends(T name) {
        System.out.println("Here's a list of all " + name + "'s' friends:");
        Iterator<User<T>.Edge> vertexIterator = getUser(name).getFriendsIterator();
        while (vertexIterator.hasNext()) {
            System.out.print(vertexIterator.next().getUser() + " ");
        }
        System.out.println();
    }

    public void displayFriendsOfFriends(T name) {
        Iterator<User<T>.Edge> myFriends = getUser(name).getFriendsIterator();
        while (myFriends.hasNext()) {
            User<T>.Edge myFriend = myFriends.next();
            System.out.print(myFriend.getUser() + ":");
            Iterator<User<T>.Edge> theirFriends = getUser(myFriend.getUser()).getFriendsIterator();

            while (theirFriends.hasNext()) {
                User<T>.Edge theirFriend = theirFriends.next();
                System.out.print(" " + theirFriend.getUser());
            }
            System.out.println();
        }
    }

    public boolean addVertex(T vertexLabel) {
        UserInterface<T> addOutcome = vertices.add(vertexLabel, new User<>(vertexLabel));
        return addOutcome == null; // Was addition to dictonary successfull?
    }

    public boolean addEdge(T begin, T end) {
        boolean result = false;
        UserInterface<T> beginVertex = vertices.getValue(begin); // value is an object!!!
        UserInterface<T> endVertex = vertices.getValue(end); // by reference
        if ((beginVertex != null) && (endVertex != null)) {
            result = beginVertex.connect(endVertex);
        }
        if (result) {
            edgeCount++;
        }
        return result;
    }

    public boolean hasEdge(T begin, T end) {
        boolean found = false;
        UserInterface<T> beginVertex = vertices.getValue(begin);
        UserInterface<T> endVertex = vertices.getValue(end);
        if ((beginVertex != null) && (endVertex != null)) {
            Iterator<UserInterface<T>> neighbors = beginVertex.getNeighborIterator();
            while (!found && neighbors.hasNext()) {
                UserInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    found = true;
                }
            }
        }
        return found;
    }

    // Gets User object from the dictionary's value
    public UserInterface<T> getUser(T user) {
        return vertices.getValue(user);
    }

    // Removes an old key and adds a new one
    public void renameKey(T existingUser, T newName) {
        vertices.add(newName, vertices.remove(existingUser));
    }

    // To search if user name already exists
    public boolean hasVertex(T vertex) {
        return vertices.contains(vertex);
    }

    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    public int getNumberOfVertices() {
        return vertices.getSize();
    }

    public int getNumberOfEdges() {
        return edgeCount;
    }

    public void clear() {
        vertices.clear();
        edgeCount = 0;
    }
}