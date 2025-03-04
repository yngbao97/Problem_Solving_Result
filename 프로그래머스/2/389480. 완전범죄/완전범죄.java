import java.lang.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        
        int cnt = info.length;
        int[][] dp = new int[cnt][m];
        
        int max = 0;
        for (int i = 0; i < cnt; i++) max += info[i][0];
        
        for (int j = 0; j < info[0][1] && j < m; j++) dp[0][j] = max;
        for (int j = info[0][1]; j < m; j++) dp[0][j] = max - info[0][0];
        
        for (int i = 1; i < cnt; i++) {
            for (int j = 0; j < info[i][1] && j < m; j++) dp[i][j] = dp[i-1][j];
            for (int j = info[i][1]; j < m; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-info[i][1]] - info[i][0]);
            }
        }
        
        if (dp[cnt - 1][m - 1] >= n) return -1;
        return dp[cnt - 1][m-1];
    }
}