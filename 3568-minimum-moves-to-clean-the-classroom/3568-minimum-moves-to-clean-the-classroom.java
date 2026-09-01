import java.util.*;

class Solution {
    public int minMoves(String[] a, int e) {
        int m=a.length,n=a[0].length(),sr=0,sc=0,k=0;
        int[][] id=new int[m][n];
        for(int[] x:id) Arrays.fill(x,-1);

        for(int i=0;i<m;i++) for(int j=0;j<n;j++) {
            char c=a[i].charAt(j);
            if(c=='S'){sr=i;sc=j;}
            if(c=='L') id[i][j]=k++;
        }

        int[][][] v=new int[m][n][1<<k];
        for(int[][] x:v) for(int[] y:x) Arrays.fill(y,-1);

        Queue<int[]> q=new ArrayDeque<>();
        q.add(new int[]{sr,sc,0,e});
        v[sr][sc][0]=e;

        int[][] d={{1,0},{-1,0},{0,1},{0,-1}};
        for(int ans=0;!q.isEmpty();ans++) for(int z=q.size();z>0;z--){
            int[] s=q.poll();
            if(s[2]==(1<<k)-1) return ans;
            if(s[3]==0) continue;

            for(int[] x:d){
                int r=s[0]+x[0],c=s[1]+x[1];
                if(r<0||r>=m||c<0||c>=n||a[r].charAt(c)=='X') continue;

                int en=s[3]-1,mask=s[2];
                if(a[r].charAt(c)=='R') en=e;
                if(a[r].charAt(c)=='L') mask|=1<<id[r][c];

                if(v[r][c][mask]<en){
                    v[r][c][mask]=en;
                    q.add(new int[]{r,c,mask,en});
                }
            }
        }
        return -1;
    }
}