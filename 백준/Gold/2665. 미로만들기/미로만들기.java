import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int N, result;
    static int[][] map;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        result = Integer.MAX_VALUE;

        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.count - o2.count);
//        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // 종료 조건
            if (cur.i == N - 1 && cur.j == N - 1) {
                result = cur.count;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                if (visited[ni][nj]) continue;

                if (map[ni][nj] == 1) {
                    queue.offer(new Node(ni, nj, cur.count));
                    visited[ni][nj] = true;
                }
                if (map[ni][nj] == 0) {
                    queue.offer(new Node(ni, nj, cur.count + 1));
                    visited[ni][nj] = true;
                }
            }
        }

        System.out.println(result);
    }

    static class Node {
        int i, j, count; // count 는 검은 방을 지나간 수

        public Node(int i, int j, int count) {
            this.i = i;
            this.j = j;
            this.count = count;
        }
    }

}