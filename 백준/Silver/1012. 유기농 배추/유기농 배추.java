import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, count;
    static int[][] map;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken()); // 가로 길이
            N = Integer.parseInt(st.nextToken()); // 세로 길이
            K = Integer.parseInt(st.nextToken()); // 배추 위치의 길인

            map = new int[N][M];
            visited = new boolean[N][M];
            count = 0;

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int b = Integer.parseInt(st.nextToken()); // 가로
                int a = Integer.parseInt(st.nextToken()); // 세로

                map[a][b] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 배추가 있으면서, 방문하지 않은 곳이라면
                    if (map[i][j] == 1 && !visited[i][j]) {
                        solve(i, j);
                        count++;
                    }
                }
            }
            sb.append(String.format("%d\n", count));

        }
        System.out.println(sb);
    }

    static void solve(int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int ci = cur[0];
            int cj = cur[1];

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;

                if (map[ni][nj] == 1 && !visited[ni][nj]) {
                    queue.offer(new int[]{ni, nj});
                    visited[ni][nj] = true;
                }
            }
        }
    }
}