import java.util.Iterator;
import java.util.Scanner;

public class UserDriver<T> {
	private static UndirectedGraph<User> network = new UndirectedGraph<>();
	private static final User u1 = new User("Kate");
	private static final User u2 = new User("Hanna");
	private static final User u3 = new User("Michael");
	private static final User u4 = new User("Baruch");
	private static final User u5 = new User("Caleb");
	private static final User u6 = new User("Hadas");
	private static final User u7 = new User("Kyle");

	public static void existingUsers() {
		network.addVertex(u1);
		network.addVertex(u2);
		network.addVertex(u3);
		network.addVertex(u4);
		network.addVertex(u5);
		network.addVertex(u6);
		network.addVertex(u7);

		network.addEdge(u1, u2);
		network.addEdge(u1, u3);
		network.addEdge(u1, u5);
		network.addEdge(u1, u6);
		network.addEdge(u1, u7);

		network.addEdge(u2, u3);

		network.addEdge(u4, u5);

		network.addEdge(u5, u6);
		network.addEdge(u5, u7);

		network.addEdge(u6, u7);
	}

	public static void main(String[] args) {
		String userInput = null;
		String userName = null;
		String userPassword = null;
		boolean networkActive = true;
		existingUsers();

		while (networkActive) {
			System.out.println("\n\nWelcome to the social network!");
			System.out.println("Do you already have an account? Please enter 'Yes' or 'No'.");
			userInput = getInput();
			// System.out.println("User input is " + userInput);

			if (userInput.equals("yes")) {
				System.out.println("\nPlease enter your name below:");
				userName = getInput();
				// Check if user exists, then ask about password.
				// Give a welcoming message

			} else if (userInput.equals("no")) {
				System.out.println("\nWould you like to create a new account?");
				System.out.println("Please enter 'Yes' or 'No'.");
				userInput = getInput();

				if (userInput.equals("yes")) {
					System.out.println("Please enter your name below:");
					userName = getInput();
					networkActive = insideSystem(userName, true);
				}
			} else {
				System.out.println("Invalid user input.");
				System.out.println("Would you like to start again? Please enter 'Yes' or 'No'.");
				userInput = getInput();

				if (userInput.equals("no")) {
					networkActive = false;
				}
			}
		}

		System.out.println("\n\nGoodbye!");

		// System.out.println("Testing the directed, weighted graph in Figure 28-18a.");
		// setGraphFig28_18a();
		// myGraph.displayEdges();
		// checkVertexAndEdgeCount(9, 13);
		// testEdgesFig28_18a();
		// System.out.println("-------------------------------------------------------");

		// testBFS(A);
		// System.out.println("A B D E G F H C I Expected");
		// System.out.println("-------------------------------------------------------");

		// testDFS(A);
		// System.out.println("A B E F C H I D G Expected");
		// System.out.println("-------------------------------------------------------");
	} // end main

	public static boolean insideSystem(String userName, boolean newUser) {
		String userInput = null;
		boolean networkActive = true;
		if (newUser) {
			System.out.println("Just a few more steps for your set up before you can join the Social Network!");
			newUser = !newUser;
		} else if (!newUser) {
			System.out.println("Welcome, " + userName + ", to the Social Network!");
			System.out.println("What would you like to do? Please enter a correlating number.");

			System.out.println("1. Update name\n2. Update image\n3. Update status");
			System.out.println("\n4. See list of friends\n5. Add friends");
			System.out.println("\n6. Get recommended friends\n7. See friends' friends\n8.Search for other profiles");
			userInput = getInput();

			if (userInput.equals("1")) {
				System.out.println("You chose option 1: Update name");
			} else if (userInput.equals("2")) {
				System.out.println("You chose option 2. Update image");
			} else if (userInput.equals("3")) {
				System.out.println("You chose option 3. Update status");
			} else if (userInput.equals("4")) {
				System.out.println("You chose option 4. See list of friends");
			} else if (userInput.equals("5")) {
				System.out.println("You chose option 5. Add friends");
			} else if (userInput.equals("6")) {
				System.out.println("You chose option 6. Get recommended friends");
			} else if (userInput.equals("7")) {
				System.out.println("You chose option 7. See friends' friends");
			} else if (userInput.equals("8")) {
				System.out.println("You chose option 8.Search for other profiles");
			} else {
				System.out.println("Invalid user input.");
				System.out.println("Would you like to start again? Please enter 'Yes' or 'No'.");
				userInput = getInput();

				if (userInput.equals("no")) {
					networkActive = false;
				}
			}
		}
		return networkActive;
	}

	public void checkUsername(String userName) {

	}

	public void checkPassword(String userName, String password) {

	}

	public void adminMode() {

	}

	public static void setGraphFig28_18a() {
		setVerticesFig28_18a(); // Graph cleared before setting vertices
		setEdgesFig28_18a();
	} // end setGraphFig28_18a

	public static void checkVertexAndEdgeCount(int numberOfVertices, int numberOfEdges) {
		// System.out.println(
		// "\nNumber of vertices = " + myGraph.getNumberOfVertices() + " (should be " +
		// numberOfVertices + ")");
		// System.out.println("Number of edges = " + myGraph.getNumberOfEdges() + "
		// (should be " + numberOfEdges + ")");
	} // end checkVertexAndEdgeCount

	public static void testEdgesFig28_18a() {
		// Check existing edges
		// boolean ok = true;
		// ok = checkEdge(A, B, ok);
		// ok = checkEdge(A, D, ok);
		// ok = checkEdge(A, E, ok);
		// ok = checkEdge(B, E, ok);
		// ok = checkEdge(C, B, ok);
		// ok = checkEdge(D, G, ok);
		// ok = checkEdge(E, F, ok);
		// ok = checkEdge(F, C, ok);

		// // Check some non-existing edges
		// ok = checkNoEdge(C, E, ok);
		// ok = checkNoEdge(C, F, ok);

		// if (ok)
		// System.out.println("Edges are OK.");
	} // end testEdgesFig28_18a

	private static <T> boolean checkEdge(T vertex1, T vertex2, boolean ok) {
		boolean check = ok;
		if (!network.hasEdge(vertex1, vertex2)) {
			System.out.println("hasEdge error " + vertex1 + " " + vertex2);
			check = false;
		} // end if

		return check;
	} // end checkEdge

	private static <T> boolean checkNoEdge(T vertex1, T vertex2, boolean ok) {
		boolean check = ok;
		if (network.hasEdge(vertex1, vertex2)) {
			System.out.println("hasEdge error " + vertex1 + " " + vertex2);
			check = false;
		} // end if

		return check;
	} // end checkNoEdge

	// public static void testBFS(String v) {
	// System.out.println("\n\nBreadth-First Traversal beginning at vertex " + v +
	// ": ");
	// QueueInterface<String> bfs = myGraph.getBreadthFirstTraversal(v);

	// printQueue(bfs);
	// } // end testBFS

	// public static void testDFS(String v) {
	// System.out.println("\n\nDepth-First Traversal beginning at vertex " + v + ":
	// ");
	// QueueInterface<String> dfs = myGraph.getDepthFirstTraversal(v);

	// printQueue(dfs);
	// } // end testDFS

	public static void setVerticesFig28_18a() {

		// myGraph.addVertex(A);
		// myGraph.addVertex(B);
		// myGraph.addVertex(C);
		// myGraph.addVertex(D);
		// myGraph.addVertex(E);
		// myGraph.addVertex(F);
		// myGraph.addVertex(G);
	} // end setVerticesFig28_18a

	public static void setEdgesFig28_18a() {
		// myGraph.addEdge(A, B, 2);
		// myGraph.addEdge(A, D, 5);
		// myGraph.addEdge(A, E, 4);

		// myGraph.addEdge(B, E, 1);

		// myGraph.addEdge(C, B, 3);

		// myGraph.addEdge(D, G, 2);

		// myGraph.addEdge(E, F, 3);

		// myGraph.addEdge(F, C, 4);

		/*
		 * Since additions are made to the end of each edge list, these lists appear as
		 * follows: A: B -> D -> E B: E C: B D: G E: F -> H F: C -> H G: H H: I I: F We
		 * can predict the BFS and DFS traversals knowing this.
		 */
	} // end setEdgesFig28_18a

	private static String getInput() { // Lined List of strings
		Scanner input;
		String inString = "";

		input = new Scanner(System.in);
		inString = input.nextLine().trim().toLowerCase();

		// Make sure there is at least one word in the rhyme
		if (inString.length() < 1 || inString.length() > 30) {
			System.out.println("Entered input is either too small or too large.");
			System.out.println("Please try following instructions again.");
		}
		return inString;
	}
}
