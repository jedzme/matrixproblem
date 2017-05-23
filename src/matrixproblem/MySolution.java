package matrixproblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySolution {
	private boolean[] visited;
	private int height, width;
	private int[][] matrix;

	private Map<Integer, List<Integer>> cache = new HashMap<>();

	public List<Integer> getLongestPath(int[][] matrix) {
		List<Integer> longestPath = new ArrayList<>();

		this.matrix = matrix;
		this.height = matrix.length;
		this.width = matrix[0].length;

		// Catch, 2D -> 1D: row * width + col; 1D -> 2D: x = 1D / width, y = 1D
		// % width.
		this.visited = new boolean[width * height];

		List<Integer> tempList = new ArrayList<>();
		for (int i = 0; i < width * height; i++) {
			// Run depth-first search on every element in the matrix.
			tempList = getMatrixValues(performDFS(i));

			if (tempList.size() > longestPath.size()) {
				longestPath = tempList;
			} else if (tempList.size() == longestPath.size()) {
				int tempListSteepness = tempList.get(0) - tempList.get(tempList.size() - 1);
				int longestPathSteepness = longestPath.get(0) - longestPath.get(longestPath.size() - 1);

				// compare difference of first and last element for steepest
				if (tempListSteepness > longestPathSteepness) {
					longestPath = tempList;
				} else if (tempListSteepness == longestPathSteepness) {
					// TODO: This will require to change data type of
					// getLongestPath to List of List
				}

			}

		}

		return longestPath;
	}

	private List<Integer> getMatrixValues(List<Integer> vertices) {
		List<Integer> values = new ArrayList<>();

		for (int n : vertices) {
			values.add(getMatrixValue(n));
		}
		return values;
	}

	private int getMatrixValue(int index) {
		int row = index / width;
		int column = index % width;
		int value = 0;
		if (isInTheGrid(row, column)) {
			value = matrix[row][column];
		}
		return value;
	}

	private List<Integer> performDFS(int index) {
		List<Integer> sequence = new ArrayList<>();
		List<Integer> tempList = new ArrayList<>();
		List<Integer> currentNeighborSequence = new ArrayList<>();

		int maxPathOfNeighbor = 0;

		if (!visited[index]) {
			sequence.add(index);
			// mark the index as visited
			visited[index] = true;
			// perform recursive dfs on all neighbors with lesser values
			for (int n : getLesserValueNeighbors(index)) {

				currentNeighborSequence = performDFS(n);

				if (maxPathOfNeighbor < currentNeighborSequence.size()) {
					maxPathOfNeighbor = currentNeighborSequence.size();
					tempList = currentNeighborSequence;
				} else if (maxPathOfNeighbor == currentNeighborSequence.size()) {
					int tempListSize = tempList.size();
					int currentNeighborSequenceSize = currentNeighborSequence.size();

					int tempListLastIndexValue = getMatrixValue(tempList.get(tempListSize - 1));
					int currentNeighSeqLastIndxVal = getMatrixValue(
							currentNeighborSequence.get(currentNeighborSequenceSize - 1));

					if (tempListSize > 0 && currentNeighborSequenceSize > 0) {
						// compare value of last element for steeper path
						if (currentNeighSeqLastIndxVal < tempListLastIndexValue) {
							tempList = currentNeighborSequence;
						}
					}

				}

			}
			sequence.addAll(tempList);
		} else {
			return cache.get(index);
		}

		cache.put(index, sequence);

		return sequence;
	}

	// Check adjacent (sequence checking: top & left then bottom & right) lesser
	// value vertices and return them
	private List<Integer> getLesserValueNeighbors(int i) {
		List<Integer> neighbors = new ArrayList<>();
		// Convert 1D array to 2D array
		int row = i / width, column = i % width;

		for (int j = -1; j <= 1; j++) {
			if (j == 0)
				continue;
			// check top and bottom
			if (isInTheGrid(row + j, column) && matrix[row + j][column] < matrix[row][column]) {
				neighbors.add((row + j) * width + column);
			}
			// check left and right
			if (isInTheGrid(row, column + j) && matrix[row][column + j] < matrix[row][column]) {
				neighbors.add(row * width + column + j);
			}
		}
		return neighbors;
	}

	// Check whether indices are in the matrix.
	private boolean isInTheGrid(int row, int column) {
		return (row >= 0 && row < height && column >= 0 && column < width);
	}

}
