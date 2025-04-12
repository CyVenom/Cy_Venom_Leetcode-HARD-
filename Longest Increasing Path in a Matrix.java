class Solution {
    private static final int[][] DIRS = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    private int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        int[][] dp = new int[m][n];
        int maxPath = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxPath = Math.max(maxPath, dfs(matrix, i, j, dp));
            }
        }

        return maxPath;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) return dp[i][j];

        int maxLen = 1;

        for (int[] dir : DIRS) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            if (ni >= 0 && ni < m && nj >= 0 && nj < n && matrix[ni][nj] > matrix[i][j]) {
                maxLen = Math.max(maxLen, 1 + dfs(matrix, ni, nj, dp));
            }
        }

        dp[i][j] = maxLen;
        return maxLen;
    }
}
