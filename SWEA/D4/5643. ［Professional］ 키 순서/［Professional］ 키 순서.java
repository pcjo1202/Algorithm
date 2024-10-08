import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, result;
    static int[][] dp;
    static int INF = 500;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            dp = new int[N + 1][N + 1];
            result = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                dp[a][b] = 1;
            }

            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if(i == j) continue;
                    if(dp[i][j] == 0){
                        dp[i][j] = INF;
                    }
                }
            }

            for (int k = 1; k < N + 1; k++) {
                for (int i = 1; i < N + 1; i++) {
                    for (int j = 1; j < N + 1; j++) {
                        dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
                    }
                }
            }

            for (int i = 1; i < N + 1; i++) {
                int hightCnt = 0;
                int lowCnt = 0;
                for (int j = 1; j < N + 1; j++) {
                    if(i == j) continue;
                    if(dp[i][j] != INF && dp[i][j] > 0){
                        hightCnt++;
                    }
                    if(dp[j][i] != INF && dp[j][i] > 0){
                        lowCnt++;
                    }
                }

                if(lowCnt + hightCnt == N -1){
                    result++;
                }
            }

            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }
}