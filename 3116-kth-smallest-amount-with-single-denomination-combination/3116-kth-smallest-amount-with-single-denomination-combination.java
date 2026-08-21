class Solution {
    long[] lcms;
    int[] signs;

    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int size = 1 << n;

        lcms = new long[size];
        signs = new int[size];

        lcms[0] = 1;

        for (int mask = 1; mask < size; mask++) {
            int bit = mask & -mask;
            int i = Integer.numberOfTrailingZeros(bit);
            int prev = mask ^ bit;

            long g = gcd(lcms[prev], coins[i]);
            long lcm = lcms[prev] / g * coins[i];

            lcms[mask] = lcm;
            signs[mask] = Integer.bitCount(mask) % 2 == 1 ? 1 : -1;
        }

        long low = 1;
        long high = (long) coins[0] * k;

        for (int coin : coins) {
            high = Math.min(high, (long) coin * k);
        }

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (count(mid, size) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long x, int size) {
        long total = 0;

        for (int mask = 1; mask < size; mask++) {
            if (lcms[mask] <= x) {
                total += signs[mask] * (x / lcms[mask]);
            }
        }

        return total;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}