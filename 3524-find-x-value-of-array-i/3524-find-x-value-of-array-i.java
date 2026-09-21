class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] count = new long[k];

        for (int num : nums) {
            long[] temp = new long[k];
            int x = num % k;

            temp[x]++;

            for (int r = 0; r < k; r++) {
                temp[(r * x) % k] += count[r];
            }

            for (int r = 0; r < k; r++) {
                result[r] += temp[r];
            }

            count = temp;
        }

        return result;
    }
}