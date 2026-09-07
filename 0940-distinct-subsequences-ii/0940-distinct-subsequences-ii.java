class Solution {
    public int distinctSubseqII(String s) {
        long MOD = 1000000007;
        long total = 1;
        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int x = c - 'a';

            long old = total;
            total = (2 * total - last[x] + MOD) % MOD;
            last[x] = old;
        }

        return (int)((total - 1 + MOD) % MOD);
    }
}