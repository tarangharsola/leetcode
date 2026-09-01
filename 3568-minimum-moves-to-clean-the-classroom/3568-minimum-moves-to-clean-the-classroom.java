import java.util.*;

class Solution {
    public int minMoves(String[] a, int e) {
        int m=a.length,n=a[0].length(),s=0,k=0;
        int[][] id=new int[m][n];
        for(int[] x:id) Arrays.fill(x,-1);

        for(int i=0;i<m;i++) for(int j=0;j<n;j++){
            if(a[i].charAt(j)=='S') s=i*n+j;
            if(a[i].charAt(j)=='L') id[i][j]=k++;
        }

        int full=(1<<k)-1,c=m*n;
        int[][] best=new int[1<<k][c];
        for(int[] x:best) Arrays.fill(x,-1);

        Queue<int[]> q=new ArrayDeque<>();
        q.add(new int[]{s,0,e});
        best[0][s]=e;

        int[][] d={{1,0},{-1,0},{0,1},{0,-1}};

        for(int ans=0;!q.isEmpty();ans++) for(int z=q.size();z>0;z--){
            int[] x=q.poll();
            int p=x[0],mask=x[1],pow=x[2];

            if(mask==full) return ans;
            if(pow==0 || pow<best[mask][p]) continue;

            int r=p/n,c1=p%n;

            for(int[] d1:d){
                int nr=r+d1[0],nc=c1+d1[1];
                if(nr<0||nr>=m||nc<0||nc>=n||a[nr].charAt(nc)=='X') continue;

                int np=nr*n+nc, nm=mask|(id[nr][nc]>=0?1<<id[nr][nc]:0);
                int ne=a[nr].charAt(nc)=='R'?e:pow-1;

                if(ne>best[nm][np]){
                    best[nm][np]=ne;
                    q.add(new int[]{np,nm,ne});
                }
            }
        }
        return -1;
    }
}