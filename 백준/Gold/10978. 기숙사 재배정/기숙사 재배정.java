import java.io.*;
import java.util.*;

public class Main {
    static int T, N;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        dp = new long[21];
        dp[2] = 1;
        for (int i = 3; i < 21; i++) {
            dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
        }

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine()); // 학생, 기숙사 수
            System.out.println(dp[N]);
        }
    }
}
