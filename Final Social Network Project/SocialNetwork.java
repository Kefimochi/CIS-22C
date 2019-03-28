import java.util.Iterator;

public class SocialNetwork<T> implements SocialNetworkInterface<T> {
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
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        }
    }

    // Used for testing
    public void displayEdges() {
        System.out.println("\nEdges exist from the first vertex in each line to the other vertices in the line.");
        System.out.println("(Edge weights are given; weights are zero for unweighted graphs):\n");
        Iterator<UserInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext()) {
            ((User<T>) (vertexIterator.next())).display();
        }
    }

    public boolean addVertex(T vertexLabel) {
        UserInterface<T> addOutcome = vertices.add(vertexLabel, new User<>(vertexLabel));
        return addOutcome == null; // Was addition to dictonary successfull?
    }

    public boolean addEdge(T begin, T end, double edgeWeight) {
        boolean result = false;
        UserInterface<T> beginVertex = vertices.getValue(begin); // value is an object!!!
        UserInterface<T> endVertex = vertices.getValue(end); // by reference
        if ((beginVertex != null) && (endVertex != null)) {
            result = beginVertex.connect(endVertex, edgeWeight);
        }
        if (result) {
            edgeCount++;
        }
        return result;
    }

    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 0);
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