class Solution {
    private int m, n;
    private char[][] grid;
    private boolean[][][] vis;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        if ((m + n - 1) % 2 == 1) {
            return false;
        }

        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        this.grid = grid;
        vis = new boolean[m][n][m + n];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int balance) {
        balance += grid[i][j] == '(' ? 1 : -1;

        if (balance < 0) {
            return false;
        }

        int remaining = (m - 1 - i) + (n - 1 - j);

        if (balance > remaining) {
            return false;
        }

        if ((remaining - balance) % 2 != 0) {
            return false;
        }

        if (i == m - 1 && j == n - 1) {
            return balance == 0;
        }

        if (vis[i][j][balance]) {
            return false;
        }

        vis[i][j][balance] = true;

        if (i + 1 < m && dfs(i + 1, j, balance)) {
            return true;
        }

        if (j + 1 < n && dfs(i, j + 1, balance)) {
            return true;
        }

        return false;
    }
}