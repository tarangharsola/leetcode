class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        int[] dp = new int[n + 1];
        int[][] suffixMax = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffixMax[i + 1][i + 1] = Integer.MIN_VALUE;
            suffixMax[i][i + 1] = -prefix[i];

            int prefixMax = 0;
            int k = i + 1;

            for (int j = i + 2; j <= n; j++) {
                while (k < j &&
                       prefix[k] - prefix[i] <= prefix[j] - prefix[k]) {

                    prefixMax = Math.max(
                        prefixMax,
                        dp[k] + prefix[k]
                    );

                    k++;
                }

                int q;

                if (prefix[k - 1] - prefix[i] == prefix[j] - prefix[k - 1]) {
                    q = k - 1;
                } else {
                    q = k;
                }

                dp[j] = Math.max(
                    prefixMax - prefix[i],
                    suffixMax[q][j] + prefix[j]
                );

                suffixMax[i][j] = Math.max(
                    suffixMax[i + 1][j],
                    dp[j] - prefix[i]
                );
            }
        }

        return dp[n];
    }
}