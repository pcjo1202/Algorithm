import java.util.*;

class Solution {
    static int[] di = {0, 1}; // 우, 하
    static int[] dj = {1, 0};
    static int[][] dp;
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        dp = new int[n + 1][m + 1]; // 경로 수 저장
        
        // 물 웅덩이 표시
        for(int[] arr : puddles){
            dp[arr[1]][arr[0]] = -1;
        }
        
        dp[1][1] = 1;
        
        for(int i = 1; i < n + 1; i++){
            for(int j = 1 ; j < m + 1 ; j++){
                // 물 웅덩이를 발견했을 때
                if(dp[i][j] == -1){
                    dp[i][j] = 0;
                    continue;
                }
                
                dp[i][j] += ( dp[i - 1][j] + dp[i][j - 1] ) % 1_000_000_007;
                
            }
        }
        
        
        return dp[n][m] ;
    }
    
}