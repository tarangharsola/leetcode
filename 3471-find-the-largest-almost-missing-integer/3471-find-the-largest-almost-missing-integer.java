class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        if(n == k) {
            int max = nums[0];

            for(int i = 1; i < n; i++) {
                max = Math.max(max, nums[i]);
            }

            return max;
        }

        if(k == 1) {
            int[] freq = new int[51];

            for(int x : nums) {
                freq[x]++;
            }

            int max = -1;

            for(int x : nums) {
                if(freq[x] == 1) {
                    max = Math.max(max, x);
                }
            }

            return max;
        }

        if(nums[0] == nums[n - 1]) {
            return -1;
        }

        boolean first = true;
        boolean last = true;

        for(int i = 1; i < n - 1; i++) {
            if(nums[i] == nums[0]) {
                first = false;
            }

            if(nums[i] == nums[n - 1]) {
                last = false;
            }
        }

        if(first && last) {
            return Math.max(nums[0], nums[n - 1]);
        }

        if(first) {
            return nums[0];
        }

        if(last) {
            return nums[n - 1];
        }

        return -1;
    }
}