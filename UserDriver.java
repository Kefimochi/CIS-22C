import java.util.Iterator;
import java.util.Scanner;

// Please use 'Kate' as login and '123' as password for testing. Or look up other users/passwords in code
// Created with care by Kate Efimova

public class UserDriver<T> {
	private static UndirectedGraph<String> network = new UndirectedGraph<>();
	private static final String u1 = "kate";
	private static final String u2 = "hanna";
	private static final String u3 = "michael";
	private static final String u4 = "baruch";
	private static final String u5 = "caleb";
	private static final String u6 = "hadas";
	private static final String u7 = "kyle";

	public static void main(String[] args) {

		String userInput = null;
		boolean networkActive = true;
		String userPassword = null;
		existingUsers();

		while (networkActive) {
			System.out.println("\n\nWelcome to the social network!");
			System.out.println("Do you already have an account? Please enter 'Yes' or 'No'.");
			userInput = getInput();

			if (userInput.equals("yes")) {
				System.out.println("\nPlease enter your name below:");
				userInput = getInput();
				System.out.println("\nPlease enter your password below:");
				userPassword = getInput();

				if (checkUsername(userInput) && checkPassword(userInput, userPassword)) {
					System.out.println("Congradulations! Your password matched your name");
					networkActive = insideSystem(userInput);
				}
			} else if (userInput.equals("no")) {
				System.out.println("\nWould you like to create a new account?");
				System.out.println("Please enter 'Yes' or 'No'.");
				userInput = getInput();

				if (userInput.equals("yes")) {
					System.out.println("Please enter your name below:");
					networkActive = setNewUser(getInput());

				} else if (userInput.equals("no")) {
					System.out.println("\nNo worries! Would you like to go back to main menu?");
					System.out.println("Please enter 'Yes' or 'No'.");

					if (getInput().equals("no")) {
						networkActive = false;
					}
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
	}

	public static boolean insideSystem(String userName) {
		String userInput = null;
		boolean networkActive = true;

		System.out.println("\n\nWelcome, " + userName + ", to the Social Network!");
		System.out.println("There are a total of " + network.getNumberOfVertices() + " people in the network");

		while (networkActive) {
			System.out.println("\nWhat would you like to do? Please enter a corresponding number.");

			System.out.println("1. Update name\n2. Update image\n3. Update status");
			System.out.println("4. See list of friends\n5. Add friends");
			System.out.println("6. See friends' friends\n7. Search for other profiles");
			System.out.println("8. Show current profile\n9. Exit the network\n10.Show all members and their friends");
			userInput = getInput();

			if (userInput.equals("1")) { // <-------- WORKS-------------------------------------------------
				System.out.println("\nYou chose option 1: Update name");
				System.out.println("Please enter your new name:");
				userInput = getInput();
				while (checkUsername(userInput)) {
					System.out.println("User with such name already exists. Please try again!");
					System.out.println("\nPlease enter your name below:");
					userInput = getInput();
				}
				network.renameKey(userName, userInput);
				userName = userInput;
				getAUser(userName).setName(userName);

				System.out.println("Your new name is: " + getAUser(userName).getName());

			} else if (userInput.equals("2")) { // <-------- WORKS-------------------------------------------
				System.out.println("\nYou chose option 2: Update image");
				System.out.println("Please enter URL of your new image:");
				getAUser(userName).setImg(getInput());

				System.out.println("Your new image is: " + getAUser(userName).getImg());

			} else if (userInput.equals("3")) { // <-------- WORKS-------------------------------------------
				System.out.println("\nYou chose option 3: Update status");
				System.out.println("Please enter your new status:");
				getAUser(userName).setStatus(getInput());

				System.out.println("Your new status is: " + getAUser(userName).getStatus());

			} else if (userInput.equals("4")) { // <-------- WORKS-------------------------------------------
				System.out.println("\nYou chose option 4: See list of friends");
				System.out.println("Will through an exception if its empty");
				network.displayFriends(userName);

			} else if (userInput.equals("5")) { // <-------- WORKS-------------------------------------------
				System.out.println("\nYou chose option 5: Add friends");
				network.displayEdges();
				System.out.println("\nType a name of a person you want to add as a friend:");
				userInput = getInput();

				if (checkUsername(userInput)) {
					System.out.println("\nCongrats! Friend added successfully.");
					network.addEdge(userName, userInput);
					network.displayFriends(userName);

				} else {
					System.out.println("\nSorry, no such user found! Pleade try again.");
				}
			} else if (userInput.equals("6")) {
				System.out.println("\nYou chose option 6: See friends' friends");
				network.displayFriendsOfFriends(userName);

			} else if (userInput.equals("7")) { // <-------- WORKS------------------------------------------
				System.out.println("\nYou chose option 7: Search for other profiles");
				System.out.println("Please enter the name of the person you're searching for:");
				userInput = getInput();

				if (checkUsername(userInput)) {

					System.out.println("\nCongrats! This person exists in the network");
					UserInterface<String> searchedUser = getAUser(userInput);

					System.out.println("Here's their profile");

					System.out.println("\n\nName: " + searchedUser.getName());
					System.out.println("Image URL: " + searchedUser.getImg());
					System.out.println("Status: " + searchedUser.getStatus());
					network.displayFriends(searchedUser.getName());

					System.out.println("\nWould you like to add them as a friend?");
					userInput = getInput();
					if (userInput.equals("yes")) {
						network.addEdge(userName, searchedUser.getName());
						network.displayFriends(userName);

					} else if (userInput.equals("no")) {
						System.out.println("\nGoing back to options screen!");
					}
				} else {
					System.out.println("Error 404, person not found. Please try again!");
				}
			} else if (userInput.equals("8")) { // <-------- WORKS------------------------------------------
				System.out.println("\nYou chose option 8: Show current profile");
				System.out.println("Printing all current profile information:");

				System.out.println("Name: " + getAUser(userName).getName());
				System.out.println("Image URL: " + getAUser(userName).getImg());
				System.out.println("Status: " + getAUser(userName).getStatus());
				network.displayFriends(userName);

			} else if (userInput.equals("9")) { // <-------- WORKS-------------------------------------------
				System.out.println("\nYou chose option 9: Exit the network");
				networkActive = false;

			} else if (userInput.equals("10")) { // <-------- WORKS------------------------------------------
				System.out.println("\nYou chose option 10: Show all members and their friends");
				network.displayEdges();

			} else {
				System.out.println("\nInvalid user input.");
				System.out.println("Would you like to start again? Please enter 'Yes' or 'No'.");
				userInput = getInput();

				if (userInput.equals("no")) {
					networkActive = false;
				}
			}
		}
		return networkActive;
	}

	public static UserInterface<String> getAUser(String user) {
		UserInterface<String> aUser = network.getUser(user);
		return aUser;
	}

	public static boolean checkUsername(String userName) {
		return network.hasVertex(userName);
	}

	public static boolean checkPassword(String userName, String password) {
		UserInterface<String> user = getAUser(userName); // User object w/ 'userName' as key
		if (user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	public static boolean setNewUser(String userName) {
		boolean networkActive = true;
		String userInput = null;

		while (checkUsername(userName)) {
			System.out.println("User with such name already exists. Please try again!");
			System.out.println("\nPlease enter your name below:");
			userName = getInput();
		}

		System.out.println("\nHello " + userName + "!");
		System.out.println("Please enter your new password below:");
		userInput = getInput();
		System.out.println("\nSetting up your account now");

		network.addVertex(userName);
		getAUser(userName).setName(userName);
		getAUser(userName).setPassword(userInput);

		System.out.println("Your name is: " + getAUser(userName).getName());
		System.out.println("Your password is: " + getAUser(userName).getPassword());

		System.out.println("\nWould you like to set up the rest of your profile?");
		System.out.println("Please enter 'Yes' or 'No'.");
		userInput = getInput();

		if (userInput.equals("yes")) {
			System.out.println("Please enter your new status below:");
			getAUser(userName).setStatus(getInput());

			System.out.println("\nYour new status is: " + getAUser(userName).getStatus() + "\n");

			System.out.println("Please enter your new URL image below:");
			getAUser(userName).setImg(getInput());

			System.out.println("\nYour new image is: " + getAUser(userName).getImg() + "\n");
		}
		System.out.println("\nWould you like to enter the Social Network?");
		userInput = getInput();

		if (userInput.equals("yes")) {
			insideSystem(userName);
		} else if (userInput.equals("no")) {
			System.out.println("Sorry to see you go!");
			networkActive = false;
		}

		return networkActive;
	}

	// public static void setGraphFig28_18a() {
	// setVerticesFig28_18a(); // Graph cleared before setting vertices
	// setEdgesFig28_18a();
	// } // end setGraphFig28_18a

	// public static void checkVertexAndEdgeCount(int numberOfVertices, int
	// numberOfEdges) {
	// System.out.println(
	// "\nNumber of vertices = " + network.getNumberOfVertices() + " (should be " +
	// numberOfVertices + ")");
	// System.out.println("Number of edges = " + network.getNumberOfEdges() + "
	// (should be " + numberOfEdges + ")");
	// } // end checkVertexAndEdgeCount

	// public static void testEdgesFig28_18a() {
	// // Check existing edges
	// boolean ok = true;
	// ok = checkEdge(A, B, ok);
	// ok = checkEdge(A, D, ok);
	// ok = checkEdge(A, E, ok);
	// ok = checkEdge(B, E, ok);
	// ok = checkEdge(C, B, ok);
	// ok = checkEdge(D, G, ok);
	// ok = checkEdge(E, F, ok);
	// ok = checkEdge(E, H, ok);
	// ok = checkEdge(F, C, ok);
	// ok = checkEdge(F, H, ok);
	// ok = checkEdge(G, H, ok);
	// ok = checkEdge(H, I, ok);
	// ok = checkEdge(I, F, ok);

	// // Check some non-existing edges
	// ok = checkNoEdge(A, I, ok);
	// ok = checkNoEdge(C, E, ok);
	// ok = checkNoEdge(C, F, ok);

	// if (ok)
	// System.out.println("Edges are OK.");
	// } // end testEdgesFig28_18a

	// private static boolean checkEdge(String vertex1, String vertex2, boolean ok)
	// {
	// boolean check = ok;
	// if (!myGraph.hasEdge(vertex1, vertex2)) {
	// System.out.println("hasEdge error " + vertex1 + " " + vertex2);
	// check = false;
	// } // end if

	// return check;
	// } // end checkEdge

	// private static boolean checkNoEdge(String vertex1, String vertex2, boolean
	// ok) {
	// boolean check = ok;
	// if (myGraph.hasEdge(vertex1, vertex2)) {
	// System.out.println("hasEdge error " + vertex1 + " " + vertex2);
	// check = false;
	// } // end if

	// return check;
	// } // end checkNoEdge

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

	// public static void testCheapestPath() {
	// // WEIGHTED graph in Figure 28-18a

	// setVerticesFig28_18a(); // graph cleared before setting vertices
	// setEdgesFig28_18a();

	// showPath(A, B);
	// showPath(A, C);
	// showPath(A, D);
	// showPath(A, E);
	// showPath(A, F);
	// showPath(A, G);
	// showPath(A, H);
	// showPath(A, I);
	// } // end testCheapestPath

	// private static void showPath(String vertex1, String vertex2) {
	// System.out.println("The cheapest path from " + vertex1 + " to " + vertex2 + "
	// is ");
	// double cost = myGraph.getCheapestPath(vertex1, vertex2, path);
	// printStack(path);
	// System.out.println("and has a cost of " + cost + ".");
	// System.out.println();
	// } // end showPath

	// public static void setVerticesFig28_18a() {
	// myGraph.clear();

	// myGraph.addVertex(A);
	// myGraph.addVertex(B);
	// myGraph.addVertex(C);
	// myGraph.addVertex(D);
	// myGraph.addVertex(E);
	// myGraph.addVertex(F);
	// myGraph.addVertex(G);
	// myGraph.addVertex(H);
	// myGraph.addVertex(I);
	// } // end setVerticesFig28_18a

	// public static void setEdgesFig28_18a() {
	// myGraph.addEdge(A, B, 2);
	// myGraph.addEdge(A, D, 5);
	// myGraph.addEdge(A, E, 4);

	// myGraph.addEdge(B, E, 1);

	// myGraph.addEdge(C, B, 3);

	// myGraph.addEdge(D, G, 2);

	// myGraph.addEdge(E, F, 3);
	// myGraph.addEdge(E, H, 6);

	// myGraph.addEdge(F, C, 4);
	// myGraph.addEdge(F, H, 3);

	// myGraph.addEdge(G, H, 1);

	// myGraph.addEdge(H, I, 1);

	// myGraph.addEdge(I, F, 1);

	// /*
	// * Since additions are made to the end of each edge list, these lists appear
	// as
	// * follows: A: B -> D -> E B: E C: B D: G E: F -> H F: C -> H G: H H: I I: F
	// We
	// * can predict the BFS and DFS traversals knowing this.
	// */
	// } // end setEdgesFig28_18a

	// public static void printStack(StackInterface<String> s) {
	// while (!s.isEmpty())
	// System.out.print(s.pop() + " ");
	// System.out.println();
	// } // end printStack

	// public static void printQueue(QueueInterface<String> q) {
	// while (!q.isEmpty())
	// System.out.print(q.dequeue() + " ");
	// System.out.println(" Actual");
	// } // end printQueue

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

	private static void existingUsers() {
		network.addVertex(u1);
		network.addVertex(u2);
		network.addVertex(u3);
		network.addVertex(u4);
		network.addVertex(u5);
		network.addVertex(u6);
		network.addVertex(u7);

		getAUser(u1).setName(u1);
		getAUser(u2).setName(u2);
		getAUser(u3).setName(u3);
		getAUser(u4).setName(u4);
		getAUser(u5).setName(u5);
		getAUser(u6).setName(u6);
		getAUser(u7).setName(u7);

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

		// getAUser(u1).setPassword("123");
		// System.out.println("Password should be 123: " + getAUser(u1).getPassword());

		getAUser(u1).setPassword("123");
		getAUser(u2).setPassword("password");
		getAUser(u3).setPassword("HelloWorld");
		getAUser(u4).setPassword("hiiii");
		getAUser(u5).setPassword("howdy!");
		getAUser(u6).setPassword("noice");
		getAUser(u7).setPassword("ALLONSY");

		getAUser(u1).setImg("https://fortunedotcom.files.wordpress.com/2018/07/gettyimages-961697338.jpg");
		getAUser(u2).setImg(
				"https://images.pexels.com/photos/415829/pexels-photo-415829.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
		getAUser(u5).setImg("https://engineering.unl.edu/images/staff/Kayla_Person-small.jpg");

		getAUser(u1).setStatus("We're all stories in the end");
		getAUser(u2).setStatus("LOLOLOLOLOLOL");
		getAUser(u3).setStatus("TARDIS!!");
		getAUser(u4).setStatus("Wait for it! :D");
		getAUser(u5).setStatus("My StAtUs iS Da BeSt!!!!");
		getAUser(u6).setStatus("hi");
		getAUser(u7).setStatus(":3");
	}
}
