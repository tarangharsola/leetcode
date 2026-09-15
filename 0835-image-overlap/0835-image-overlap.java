class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i] |= img1[i][j] << j;
                b[i] |= img2[i][j] << j;
            }
        }

        int ans = 0;

        for (int dr = -(n - 1); dr <= n - 1; dr++) {
            for (int dc = -(n - 1); dc <= n - 1; dc++) {
                int overlap = 0;

                for (int i = 0; i < n; i++) {
                    int j = i + dr;

                    if (j < 0 || j >= n) continue;

                    int x = a[i];
                    int y = b[j];

                    if (dc > 0) {
                        x <<= dc;
                    } else {
                        y <<= -dc;
                    }

                    overlap += Integer.bitCount(x & y);
                }

                ans = Math.max(ans, overlap);
            }
        }

        return ans;
    }
}