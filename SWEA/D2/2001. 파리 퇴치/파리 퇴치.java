import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, result;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            result = 0;

            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solve(0, 0);

            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    static void solve(int i, int j) {
        if (i > N - M) {
            return;
        }
        if (j > N - M) {
            solve(i + 1, 0);
            return;
        }

        int temp = 0;

        for (int k = 0; k < M; k++) {
            for (int l = 0; l < M; l++) {
                temp += board[i + k][j + l];
            }
        }
        result = Math.max(temp, result);
        solve(i, j + 1);
    }
}