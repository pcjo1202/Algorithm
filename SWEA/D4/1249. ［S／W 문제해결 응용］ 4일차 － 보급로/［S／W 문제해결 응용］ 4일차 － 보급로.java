import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int N, result;
    static int[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static class Node {
        int i, j, cost;

        public Node(int i, int j, int cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            result = 0;

            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            boolean[][] visited = new boolean[N][N];
            PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
            pq.offer(new Node(0, 0, 0));
            visited[0][0] = true;

            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                if (cur.i == N - 1 && cur.j == N - 1) {
                    result = cur.cost;
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    int ni = cur.i + di[d];
                    int nj = cur.j + dj[d];

                    if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                    if (visited[ni][nj]) continue;

                    pq.offer(new Node(ni, nj, cur.cost + map[ni][nj]));
                    visited[ni][nj] = true;
                }
            }


            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }
}