class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] freq = new int[51];
        int[] count = new int[51];

        for (int i = 0; i < k; i++) {
            freq[nums[i]]++;
        }

        for (int x = 0; x <= 50; x++) {
            if (freq[x] > 0) {
                count[x]++;
            }
        }

        for (int i = k; i < n; i++) {
            freq[nums[i - k]]--;
            freq[nums[i]]++;

            for (int x = 0; x <= 50; x++) {
                if (freq[x] > 0) {
                    count[x]++;
                }
            }
        }

        for (int x = 50; x >= 0; x--) {
            if (count[x] == 1) {
                return x;
            }
        }

        return -1;
    }
}