import java.util.*;

class Solution {
    HashMap<String, Integer> map = new HashMap<>();

    public int numDistinct(String s, String t) {
        return solve(s, t, 0, 0);
    }

    public int solve(String s, String t, int i, int j) {
        if (j == t.length()) {
            return 1;
        }

        if (i == s.length()) {
            return 0;
        }

        String key = i + "," + j;

        if (map.containsKey(key)) {
            return map.get(key);
        }

        int ans;

        if (s.charAt(i) == t.charAt(j)) {
            ans = solve(s, t, i + 1, j + 1)
                + solve(s, t, i + 1, j);
        } else {
            ans = solve(s, t, i + 1, j);
        }

        map.put(key, ans);

        return ans;
    }
}