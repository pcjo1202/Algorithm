import java.io.*;
import java.util.*;

public class Solution {
    static int N, K, result;
    static int[] weight, cost;
    static int[][] dp;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            result = 0;

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            dp = new int[N + 1][K + 1];

            weight = new int[N + 1];
            cost = new int[N + 1];

            for (int i = 1; i < N + 1; i++) {
                st = new StringTokenizer(br.readLine());
                weight[i] = Integer.parseInt(st.nextToken());
                cost[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    if (weight[i] > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j - weight[i]] + cost[i], dp[i - 1][j]);
                    }
                }
            }

            result = dp[N][K];

            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }
}