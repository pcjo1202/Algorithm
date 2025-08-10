import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char temp = str.charAt(j);
                board[i][j] = temp;
            }
        }

        // 사이클을 체크하는 방법
        // 조건 : 무방향 -> 유니온 파인드?, bfs ? 배열이니까 bfs?

        visited = new boolean[N][M];
        boolean flag = false;
        out:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dfs(i, j, -1, -1, board[i][j], 1)) {
                    flag = true;
                    break out;
                }
            }
        }

        System.out.println(flag ? "Yes" : "No");

    }

    static boolean dfs(int i, int j, int prevI, int prevJ, char color, int cnt) {
        visited[i][j] = true;

        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
            if (board[ni][nj] != color) continue;

            if (!visited[ni][nj]) {
                if (dfs(ni, nj, i, j, color, cnt + 1)) return true;
            } else if (!(ni == prevI && nj == prevJ) && cnt >= 4) {
                // 처음 위치로 돌아왔을 때
                return true;
            }
        }
        return false;
    }
}