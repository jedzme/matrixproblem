package matrixproblem;

public class Main {

	private static int[][] myArr = { { 4, 8, 7, 3 }, { 2, 5, 9, 3 }, { 6, 3, 2, 5 }, { 4, 4, 1, 6 } };

	public static void main(String args[]) {
		MySolution sol = new MySolution();
		System.out.println(sol.getLongestPath(myArr));
	}

}
