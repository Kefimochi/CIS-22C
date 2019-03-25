import java.util.Iterator;

public class DirectedGraph<T> implements GraphInterface<T> {
    private DictionaryInterface<T, VertexInterface<T>> vertices;
    private int edgeCount;

    public DirectedGraph() {
        vertices = new LinkedDictionary<>();
        edgeCount = 0;
    }

    protected void resetVertices() {
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext()) {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        }
    }

    /** Performs a breadth-first traversal of this graph.
       @param origin  An object that labels the origin vertex of the traversal.
       @return  A queue of labels of the vertices in the traversal, with
                the label of the origin vertex at the queue's front. */
    public QueueInterface<T> getBreadthFirstTraversal(T origin) {
        // Assumes graph is not empty
        resetVertices();
        
        QueueInterface<T> traversalOrder = new LinkedQueue<T>();
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();

        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin);
        vertexQueue.enqueue(originVertex);

        while (!vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();

            Iterator<VertexInterface<T>> neighborIterator = frontVertex.getNeighborIterator();
            while (neighborIterator.hasNext()) {
                VertexInterface<T> nextNeighbor = neighborIterator.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    traversalOrder.enqueue(nextNeighbor.getLabel());
                    vertexQueue.enqueue(nextNeighbor);
                }
            }
        }

        return traversalOrder;
    }

    public QueueInterface<T> getDepthFirstTraversal(T origin) {
        // Assumes graph is not empty
        resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<T>();
        StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();

        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin); // Enqueue vertex label
        vertexStack.push(originVertex); // Enqueue vertex

        while (!vertexStack.isEmpty()) {
            VertexInterface<T> topVertex = vertexStack.peek();
            VertexInterface<T> nextNeighbor = topVertex.getUnvisitedNeighbor();

            if (nextNeighbor != null) {
                nextNeighbor.visit();
                traversalOrder.enqueue(nextNeighbor.getLabel());
                vertexStack.push(nextNeighbor);
            } else { // All neighbors are visited 
                vertexStack.pop(); 
            } 
        }
        return traversalOrder;
    }

    public StackInterface<T> getTopologicalOrder()  {
        resetVertices();

        StackInterface<T> vertexStack = new LinkedStack<>();
        int numberOfVertices = getNumberOfVertices();
        for (int counter = 1; counter <= numberOfVertices; counter++) {
            VertexInterface<T> nextVertex = findTerminal();
            nextVertex.visit();
            vertexStack.push(nextVertex.getLabel());
        }

        return vertexStack; 
    }

    /** Precondition: path is an empty stack (NOT null) */
    public int getShortestPath(T begin, T end, StackInterface<T> path) {
        resetVertices();
        boolean done = false;
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();

        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);

        originVertex.visit();
        // Assertion: resetVertices() has executed setCost(0)
        // and setPredecessor(null) for originVertex

        vertexQueue.enqueue(originVertex);

        while (!done && !vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();

            Iterator<VertexInterface<T>> neighbors = 
            frontVertex.getNeighborIterator();
            while (!done && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();

                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    nextNeighbor.setCost(1 + frontVertex.getCost());
                    nextNeighbor.setPredecessor(frontVertex);
                    vertexQueue.enqueue(nextNeighbor);
                }

                if (nextNeighbor.equals(endVertex)) {
                    done = true;
                }
            }
        }

        // Traversal ends; construct shortest path
        int pathLength = (int)endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while

        return pathLength;
    } // end getShortestPath


    /** Precondition: path is an empty stack (NOT null) */
    public double getCheapestPath(T begin, T end, StackInterface<T> path)  {
        resetVertices();
        boolean done = false;

        // Use EntryPQ instead of Vertex because multiple entries contain
        // the same vertex but different costs - cost of path to vertex is EntryPQ's priority value
        PriorityQueueInterface<EntryPQ> priorityQueue = new HeapPriorityQueue<>();

        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);

        priorityQueue.add(new EntryPQ(originVertex, 0, null));

        while (!done && !priorityQueue.isEmpty()) {
            EntryPQ frontEntry = priorityQueue.remove();
            VertexInterface<T> frontVertex = frontEntry.getVertex();

            if (!frontVertex.isVisited()) {
                frontVertex.visit();
                frontVertex.setCost(frontEntry.getCost());
                frontVertex.setPredecessor(frontEntry.getPredecessor());

                if (frontVertex.equals(endVertex)) {
                    done = true;
                } else {
                    Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
                    Iterator<Double> edgeWeights = frontVertex.getWeightIterator();
                    while (neighbors.hasNext()) {
                        VertexInterface<T> nextNeighbor = neighbors.next();
                        Double weightOfEdgeToNeighbor = edgeWeights.next();

                        if (!nextNeighbor.isVisited()) {
                            double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
                            priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
                        }
                    }
                }
            }
        }

        // Traversal ends, construct cheapest path
        double pathCost = endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while

        return pathCost;
    }

    protected VertexInterface<T> findTerminal() {
        boolean found = false;
        VertexInterface<T> result = null;

        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();

        while (!found && vertexIterator.hasNext()) {
            VertexInterface<T> nextVertex = vertexIterator.next();

            // If nextVertex is unvisited AND has only visited neighbors)
            if (!nextVertex.isVisited()) { 
                if (nextVertex.getUnvisitedNeighbor() == null ) { 
                    found = true;
                    result = nextVertex;
                }
            }
        }

        return result;
    }

    // Used for testing
    public void displayEdges() {
        System.out.println("\nEdges exist from the first vertex in each line to the other vertices in the line.");
        System.out.println("(Edge weights are given; weights are zero for unweighted graphs):\n");
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext()) {
            ((Vertex<T>)(vertexIterator.next())).display();
        }
    }

    private class EntryPQ implements Comparable<EntryPQ> {
        private VertexInterface<T> vertex; 
        private VertexInterface<T> previousVertex; 
        private double cost; // cost to nextVertex

        private EntryPQ(VertexInterface<T> vertex, double cost, VertexInterface<T> previousVertex) {
            this.vertex = vertex;
            this.previousVertex = previousVertex;
            this.cost = cost;
        }

        public VertexInterface<T> getVertex() {
            return vertex;
        }

        public VertexInterface<T> getPredecessor() {
            return previousVertex;
        }

        public double getCost() {
            return cost;
        }

        public int compareTo(EntryPQ otherEntry) {
            // Using opposite of reality since our priority queue uses a maxHeap;
            // could revise using a minheap
            return (int)Math.signum(otherEntry.cost - cost);
        } 

        public String toString() {
            return vertex.toString() + " " + cost;
        }
    }

    public boolean addVertex(T vertexLabel) {
        VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
        return addOutcome == null; //Was addition to dictonary successfull?
    }

    public boolean addEdge(T begin, T end, double edgeWeight) {
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin); //value is an object!!!
        VertexInterface<T> endVertex = vertices.getValue(end); //by reference
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
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        if ((beginVertex != null) && (endVertex != null)) { 
            Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
           while (!found && neighbors.hasNext()) {
               VertexInterface<T> nextNeighbor = neighbors.next();
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