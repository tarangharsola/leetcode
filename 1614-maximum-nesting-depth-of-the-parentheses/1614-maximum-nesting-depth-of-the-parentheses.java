class Solution {
    public int maxDepth(String s) {
        int n = s.length();

        int openCounts = 0;
        int res = 0;
        for(int i = 0; i<n; i++){
            if(s.charAt(i) == '('){
                openCounts += 1;
                res = Math.max(res,openCounts);
            }
            else if(s.charAt(i) == ')'){
                openCounts -=1 ;
            }
            else{
                continue;
            }
        }
        return res;        
    }
}