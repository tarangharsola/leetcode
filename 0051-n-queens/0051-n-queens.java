class Solution {
    private List<List<String>> ans;
    private int n;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        ans = new ArrayList<>();
        dfs(0, 0, 0, 0, new int[n]);
        return ans;
    }

    private void dfs(int row, int cols, int diag, int antiDiag, int[] queens) {
        if (row == n) {
            List<String> board = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                char[] r = new char[n];
                Arrays.fill(r, '.');
                r[queens[i]] = 'Q';
                board.add(new String(r));
            }

            ans.add(board);
            return;
        }

        int available = ((1 << n) - 1) & ~(cols | diag | antiDiag);

        while (available != 0) {
            int bit = available & -available;
            available -= bit;

            int col = Integer.numberOfTrailingZeros(bit);
            queens[row] = col;

            dfs(
                row + 1,
                cols | bit,
                (diag | bit) << 1,
                (antiDiag | bit) >> 1,
                queens
            );
        }
    }
}