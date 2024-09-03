import java.io.*;
import java.util.*;

/*
3
2 3
WLL
LLL
* */

public class Solution {
    static int N, M, result;
    static char[][] board;
    static int[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static class Pair {
        int i, j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            board = new char[N][M];
            visited = new int[N][M];

            for (int[] arr : visited) {
                Arrays.fill(arr, -1);
            }

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < M; j++) {
                    board[i][j] = str.charAt(j);
                }
            }

            Queue<Pair> queue = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 'W') { // 물부터 시작
                        queue.offer(new Pair(i, j));
                        visited[i][j] = 0;
                    }
                }
            }

            while (!queue.isEmpty()) {
                Pair cur = queue.poll();
                int ci = cur.i;
                int cj = cur.j;

                for (int d = 0; d < 4; d++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];

                    if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                    if (visited[ni][nj] != -1) continue;

                    queue.offer(new Pair(ni, nj));
                    visited[ni][nj] = visited[ci][cj] + 1;
                }

            }


            result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 'L') { // 물부터 시작
                        result += visited[i][j];
                    }
                }
            }

            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }
}