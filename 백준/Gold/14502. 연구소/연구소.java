import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result;
    static int[][] board;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M]; // 1-base

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // 벽을 세울 수 있는 가능한 모든 곳 중 3개를 뽑아서 세워보고, 바이러스를 퍼트려본다.

        result = 0;

        comb(0, new boolean[N][M]);

        System.out.println(result);
    }

    static void bfs(boolean[][] visited) {
        int count = 0;

        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp[i] = board[i].clone();
        }

        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) temp[i][j] = 1;

                if (temp[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int ci = cur[0];
            int cj = cur[1];


            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;

                if (temp[ni][nj] == 0) {
                    temp[ni][nj] = 2;
                    queue.offer(new int[]{ni, nj});
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 0) {
                    count++;
                }
            }
        }
        result = Math.max(result, count);
    }

    static void comb(int depth, boolean[][] visited) {
        if (depth == 3) {
            bfs(visited);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    if (visited[i][j]) continue;

                    visited[i][j] = true;
                    comb(depth + 1, visited);
                    visited[i][j] = false;
                }
            }
        }
    }
}