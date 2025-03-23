import java.io.*;
import java.util.*;

public class Main {
    static int W, H;
    static int[] di = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dj = {0, 1, 0, -1};
    static char[][] map;
    static Queue<Node> razer;

    static class Node {
        int i, j, d, turn;

        public Node(int i, int j, int d, int turn) {
            this.i = i;
            this.j = j;
            this.d = d;
            this.turn = turn;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        razer = new ArrayDeque<>();

        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'C') razer.offer(new Node(i, j, -1, 0));
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.turn, o2.turn));
        boolean[][][] visited = new boolean[H][W][4]; // 방문 처리

        int[][] turnCnt = new int[H][W]; // 회전한 횟 수

        for (int[] arr : turnCnt) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        Node start = razer.poll();
        Node target = razer.poll();

        for (int d = 0; d < 4; d++) {
            queue.offer(new Node(start.i, start.j, d, 0));
            visited[start.i][start.j][d] = true;
        }
        turnCnt[start.i][start.j] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.i == target.i && cur.j == target.j) {
                return cur.turn;
            }

            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                // 예외 처리
                if (ni < 0 || ni >= H || nj < 0 || nj >= W || map[ni][nj] == '*') continue;

                // 턴을 했는지 안했는지 확인
                int nextTurn = cur.turn + (cur.d == d ? 0 : 1);
                if (!visited[ni][nj][d] || turnCnt[ni][nj] > nextTurn) {
                    queue.offer(new Node(ni, nj, d, nextTurn));
                    visited[ni][nj][d] = true;
                    turnCnt[ni][nj] = nextTurn;
                }
            }
        }

        return -1;
    }
}
