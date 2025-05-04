import java.io.*;

public class Main {
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][10];

        // 첫 번째 자리 (길이 1) 초기화
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        // 점화식 적용
        for (int i = 2; i < N + 1; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 10007;
                }
            }
        }

        // 정답 계산
        int result = 0;
        for (int j = 0; j < 10; j++) {
            result = (result + dp[N][j]) % 10007;
        }

        System.out.println(result);
    }
}
