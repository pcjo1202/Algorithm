import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] board;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {
            String[] str = br.readLine().split("");
            for (int j = 1; j < M + 1; j++) {
                board[i][j] = Integer.parseInt(str[j - 1]);
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N + 1][M + 1][K + 1];

        queue.offer(new Point(1, 1, 0, 1));
        visited[1][1][0] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (cur.i == N && cur.j == M) {
                return cur.dist;
            }

            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if (ni <= 0 || nj <= 0 || ni > N || nj > M) continue;

                // 벽이 있을 때
                if (board[ni][nj] == 1) {
                    if (cur.k < K && !visited[ni][nj][cur.k + 1]) {
                        queue.offer(new Point(ni, nj, cur.k + 1, cur.dist + 1));
                        visited[ni][nj][cur.k + 1] = true;
                    }
                }
                // 벽이 없을 때
                else {
                    if (visited[ni][nj][cur.k]) continue;
                    queue.offer(new Point(ni, nj, cur.k, cur.dist + 1));
                    visited[ni][nj][cur.k] = true;
                }
            }
        }

        return -1;
    }

    static class Point {
        int i, j, k, dist;

        public Point(int i, int j, int k, int dist) {
            this.i = i;
            this.j = j;
            this.k = k;
            this.dist = dist;
        }
    }
}
