import java.io.*;
import java.util.*;

public class Solution {
    static int N, result;
    static int[] numbers, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            numbers = new int[N];
            result = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            dp = new int[N];

            solve1();
//            solve2();
//            System.out.println(Arrays.toString(dp));

            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }

    static void solve1() {
        // DP
        // dp[i] : numbers[i]를 가장 큰 수로 한 LIS의 길이
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (numbers[j] < numbers[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

//        System.out.println(Arrays.toString(dp));
        // 가장 긴 길이 구하기
        for (int res : dp) {
            result = Math.max(result, res);
        }
    }

    static void solve2() {
        // 이분 탐색
    }

}