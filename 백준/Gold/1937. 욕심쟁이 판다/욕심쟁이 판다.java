import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static int[][] map, dp;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result = Math.max(result, dfs(i, j));
            }
        }

        System.out.println(result);
    }

    static int dfs(int i, int j) {
        // 가지 치기
        if (dp[i][j] != 0) { // 이미 저장된 값이 있다면 사용
            return dp[i][j];
        }

        dp[i][j] = 1; // 초기값

        int cur = map[i][j];

        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;

            int next = map[ni][nj];
            if (cur < next) {
                dp[i][j] = Math.max(dp[i][j], dfs(ni, nj) + 1);
            }
        }

        return dp[i][j];
    }
}