package matrixproblem;

public class MatrixDataGenerator {
	public int[][] generate(int row, int column, int minNum, int maxNum) {
		int[][] result = new int[row][column];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				result[i][j] = generateRandomInteger(minNum, maxNum);
			}
		}

		return result;
	}

	private int generateRandomInteger(int min, int max) {
		return (int) (Math.random() * max + min);
	}
}
