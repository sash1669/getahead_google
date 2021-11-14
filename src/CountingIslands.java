public class CountingIslands {
	public static int countIslands(boolean[][] grid) {
	if (grid.length == 0) {
	return 0;
	}
	int numberOfIslands = 0;
	for (int x = 0; x < grid.length; x++) {
	for (int y = 0; y < grid[0].length; y++) {
	if (grid[x][y]) { // We found a new island
	numberOfIslands += 1;
	visitBfs(grid, x, y); // Mark the connected component
	}
	}
	}
	return numberOfIslands;
	}
	/** Mark all cells as "water" for visited islands. */
	private static void visitBfs(boolean[][] grid, int startX, int startY) {
	grid[startX][startY] = false;
	int[] dx = {-1, 0, 1, 0};
	int[] dy = {0, -1, 0, 1};
	for (int i = 0; i < dx.length; i++) {
	int x = startX + dx[i];
	int y = startY + dy[i];
	if (isInBounds(grid, x, y) && grid[x][y]) {
	visitBfs(grid, x, y);
	}
	}
	}
	private static boolean isInBounds(boolean[][] grid, int pointX, int pointY) {
	if (pointX < 0 || pointX >= grid.length
	|| pointY < 0 || pointY >= (grid.length == 0 ? 0 : grid[0].length)) {
	return false;
	}
	return true;
	}
}
