package matrixproblem;

import java.util.List;
import java.util.Scanner;

public class Main {

	// For testing
	// private static int[][] myArr = { { 4, 8, 7, 3 }, { 2, 5, 9, 3 }, { 6, 3,
	// 2, 5 }, { 4, 4, 1, 6 } };
	// private static int[][] myArr2 = { { 6, 2, 4, 2, 1 }, { 7, 7, 1, 8, 6, },
	// { 2, 8, 3, 9, 2 }, { 5, 8, 4, 2, 6 },
	// { 7, 7, 3, 4, 9 } };
	//
	// private static int[][] myArr3 = { { 6, 6, 4, 2, 9 }, { 7, 3, 1, 9, 7 }, {
	// 2, 3, 3, 9, 7 }, { 5, 8, 4, 2, 6},
	// { 7, 7, 3, 4, 1 } };

	public static void main(String args[]) {
		// For testing
		// int[][] matrix = myArr2;//matrixGen.generate(height, width, minNum,
		// maxNum);

		MySolution sol = new MySolution();
		MatrixDataGenerator matrixGen = new MatrixDataGenerator();

		Scanner sc = new Scanner(System.in);

		System.out.print("Input Height of Matrix: ");
		int height = sc.nextInt();
		System.out.print("Input Width of Matrix: ");
		int width = sc.nextInt();
		System.out.print("Minimum integer: ");
		int minNum = sc.nextInt();
		System.out.print("Maximum integer: ");
		int maxNum = sc.nextInt();
		System.out.println("");

		if (height > 0 && width > 0) {
			int[][] matrix = matrixGen.generate(height, width, minNum, maxNum);

			List<Integer> longestPath = sol.getLongestPath(matrix);
			System.out.println("Length of calculated path: " + longestPath.size());
			System.out.println(
					"Drop of calculated path: " + (longestPath.get(0) - longestPath.get(longestPath.size() - 1)));
			System.out.println("Calculated path: " + longestPath);
			
			System.out.println(" ");
			System.out.println("=======Matrix========");
			System.out.println(height + "  " + width);
			for (int row = 0; row < height; row++) {
				for (int column = 0; column < width; column++) {
					System.out.print(matrix[row][column]);
					System.out.print("  ");
				}
				System.out.println("");
			}
		}

		sc.close();

	}

}
