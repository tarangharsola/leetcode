class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int i = 0;
        int j = 0;
        int prev = 0;
        int curr = 0;

        for (int k = 0; k <= (m + n) / 2; k++) {
            prev = curr;

            if (i < m && (j >= n || nums1[i] <= nums2[j])) {
                curr = nums1[i++];
            } else {
                curr = nums2[j++];
            }
        }

        if ((m + n) % 2 == 1) {
            return curr;
        }

        return (prev + curr) / 2.0;
    }
}