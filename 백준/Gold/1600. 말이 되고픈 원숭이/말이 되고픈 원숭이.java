import java.io.*;
import java.util.*;

public class Main {
    static int K, W, H;
    static int[][] board;
    static int[] di = {-1, 1, 0, 0}; // 상하좌우 인접
    static int[] dj = {0, 0, -1, 1};
    static int[] hi = {-2, -1, 1, 2, 2, 1, -1, -2}; // 말처럼 이동 오른쪽 위 부터 시계방향으로 순서대로
    static int[] hj = {1, 2, 2, 1, -1, -2, -2, -1}; //

    static class Point {
        int i, j, cnt, horse;

        public Point(int i, int j, int cnt, int horse) {
            this.i = i;
            this.j = j;
            this.cnt = cnt; // 이동한 횟수
            this.horse = horse;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine()); // 말처럼 움직일 수 있는 최대 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        System.out.println(bfs());

    }

    static int bfs() {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[H][W][K + 1]; // 현재까지 이동한 횟수;

        queue.offer(new Point(0, 0, 0, 0));
        visited[0][0][0] = true; // 말 이동 횟수를 저장

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int ci = cur.i;
            int cj = cur.j;

            // 종료 조건 마지막 지점에 도착했을 때
            if (ci == H - 1 && cj == W - 1) {
                return cur.cnt;
            }

            // 탐색 : 말처럼 가거나, 인접해서 가거나,
            // 최대 말의 횟수를 넘었다면, 바로 인접으로
            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if (ni < 0 || ni >= H || nj < 0 || nj >= W) continue;
                if (board[ni][nj] == 1) continue;
                if (visited[ni][nj][cur.horse]) continue;

                visited[ni][nj][cur.horse] = true;
                queue.offer(new Point(ni, nj, cur.cnt + 1, cur.horse));
            }

            if (cur.horse < K) {
                for (int d = 0; d < 8; d++) {
                    int ni = ci + hi[d];
                    int nj = cj + hj[d];

                    if (ni < 0 || ni >= H || nj < 0 || nj >= W) continue;
                    if (board[ni][nj] == 1) continue;
                    if (visited[ni][nj][cur.horse + 1]) continue;

                    visited[ni][nj][cur.horse + 1] = true;
                    queue.offer(new Point(ni, nj, cur.cnt + 1, cur.horse + 1));
                }
            }
        }
        return -1;
    }
}