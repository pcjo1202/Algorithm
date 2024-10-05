import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result;
    static int[][] map, dp;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N]; // 지나간 경로 체크(몇번 지나갔는지 확인)

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        result = dfs(0, 0);

//        for (int[] arr : dp) {
//            System.out.println(Arrays.toString(arr));
//        }

        System.out.println(result);
    }

    static int dfs(int i, int j) {
        // 메모이제이션 이용
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // 기저조건 (도착 했을 때)
        if (i == M - 1 && j == N - 1) {
            return 1;
        }
        // 탐색 조건
        dp[i][j] = 0; // 초기 방문

        int cur = map[i][j];

        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (ni < 0 || ni >= M || nj < 0 || nj >= N) continue;

            int next = map[ni][nj]; // 다음 이동 할 수 있는 값

            if (cur > next) { // 다음 값이 작을 때만 이동
                dp[i][j] += dfs(ni, nj);
            }
        }
        return dp[i][j];
    }
}