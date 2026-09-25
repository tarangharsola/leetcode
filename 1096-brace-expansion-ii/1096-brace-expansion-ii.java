import java.util.*;

class Solution {
    int i = 0;
    String s;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        Set<String> result = parse();
        return new ArrayList<>(new TreeSet<>(result));
    }

    Set<String> parse() {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        while (i < s.length() && s.charAt(i) != '}') {
            char c = s.charAt(i);

            if (c == ',') {
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                i++;
            } 
            else if (c == '{') {
                i++;
                Set<String> next = parse();
                i++;

                Set<String> temp = new HashSet<>();

                for (String a : current) {
                    for (String b : next) {
                        temp.add(a + b);
                    }
                }

                current = temp;
            } 
            else {
                StringBuilder word = new StringBuilder();

                while (i < s.length() && Character.isLetter(s.charAt(i))) {
                    word.append(s.charAt(i));
                    i++;
                }

                String w = word.toString();

                Set<String> temp = new HashSet<>();

                for (String str : current) {
                    temp.add(str + w);
                }

                current = temp;
            }
        }

        result.addAll(current);
        return result;
    }
}