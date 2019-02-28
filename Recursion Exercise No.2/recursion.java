public class Triangles {
	private static char character = '*';
	private static int base = 40;
	
	public static void main(String[] args) {
		createTriangle(character, base);
	}
	
	private static void createTriangle(char character, int base) {
		if (base > 0) {
			createTriangle(character, base - 1);
			for (int i = 0; i < base; i++) {
				System.out.print(character);
			}
			System.out.println();
		}
	}
}
