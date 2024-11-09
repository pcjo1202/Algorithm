import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int result;
    static boolean[][][] visited;
    static int[] di = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dj = {0, 0, -1, 1};

    static class Node {
        int i, j, d, dist;

        public Node(int i, int j, int d, int dist) {
            this.i = i;
            this.j = j;
            this.d = d;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) + 100;
            int y1 = Integer.parseInt(st.nextToken()) + 100;
            int x2 = Integer.parseInt(st.nextToken()) + 100;
            int y2 = Integer.parseInt(st.nextToken()) + 100;
            result = 0;

            visited = new boolean[201][201][2]; // 0 : 가로, 1: 세로;
            Queue<Node> queue = new ArrayDeque<>();
            queue.offer(new Node(x1, y1, 0, 0)); // 세로 이동
            visited[x1][y1][1] = true;
            queue.offer(new Node(x1, y1, 2, 0)); // 가로 이동
            visited[x1][y1][0] = true;

            while (!queue.isEmpty()) {
                Node cur = queue.poll();

                // 종료
                if (cur.i == x2 && cur.j == y2) {
                    result = cur.dist;
                    break;
                }

                // 세로 이동 -> 가로
                if (cur.d == 0 || cur.d == 1) {
                    for (int d = 2; d < 4; d++) {
                        int ni = cur.i + di[d];
                        int nj = cur.j + dj[d];

                        if (ni < 0 || ni >= 201 || nj < 0 || nj >= 201) continue;
                        if (visited[ni][nj][0]) continue;

                        queue.offer(new Node(ni, nj, d, cur.dist + 1));
                        visited[ni][nj][0] = true;
                    }
                } else {
                    //  가로이동 -> 세로
                    for (int d = 0; d < 2; d++) {
                        int ni = cur.i + di[d];
                        int nj = cur.j + dj[d];

                        if (ni < 0 || ni >= 201 || nj < 0 || nj >= 201) continue;
                        if (visited[ni][nj][1]) continue;

                        queue.offer(new Node(ni, nj, d, cur.dist + 1));
                        visited[ni][nj][1] = true;
                    }
                }
            }
            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }
}