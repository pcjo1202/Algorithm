import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, result;
    static int[][] board;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0, -1, -1, 1, 1}; // 8방
    static int[] dj = {0, 0, -1, 1, -1, 1, -1, 1};
    static Queue<Node> queue;

    static class Node {
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                if (board[i][j] == 0) continue; // 0이면 봉우리 불가능 하므로 패스
                if (bfs(i, j)) {
                    result += 1;
                }
            }
        }

        System.out.println(result);
    }

    static boolean bfs(int i, int j) {
        queue = new ArrayDeque<>();
        queue.offer(new Node(i, j));
        visited[i][j] = true;

        boolean flag = true;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curValue = board[cur.i][cur.j];

            for (int d = 0; d < 8; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;

                int nextValue = board[ni][nj];
                if (nextValue > curValue) {
                    flag = false; // 주변에 더 높은 곳이 있으면 산봉우리 아님
                }

                if (!visited[ni][nj] && nextValue == curValue) {
                    queue.offer(new Node(ni, nj));
                    visited[ni][nj] = true;
                }
            }
        }
        return flag;
    }

}