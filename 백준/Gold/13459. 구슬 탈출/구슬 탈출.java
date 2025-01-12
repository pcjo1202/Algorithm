import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static int[] di = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dj = {0, 0, -1, 1};
    static boolean[][][][] visited;
    static Node red, blue, hole;

    static class Node {
        int redI, redJ, blueI, blueJ, count;

        public Node(int redI, int redJ, int blueI, int blueJ, int count) {
            this.redI = redI;
            this.redJ = redJ;
            this.blueI = blueI;
            this.blueJ = blueJ;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'R') {
                    red = new Node(i, j, 0, 0, 0);
                } else if (board[i][j] == 'B') {
                    blue = new Node(0, 0, i, j, 0);
                } else if (board[i][j] == 'O') {
                    hole = new Node(i, j, 0, 0, 0);
                }
            }
        }

        System.out.println(bfs() ? 1 : 0);
    }

    static boolean bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(red.redI, red.redJ, blue.blueI, blue.blueJ, 0));
        visited[red.redI][red.redJ][blue.blueI][blue.blueJ] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.count > 10) break; // 최대 10번까지 이동 가능
            if (check(cur)) return true; // 성공 조건 만족

            for (int d = 0; d < 4; d++) {
                int[] nextRed = roll(cur.redI, cur.redJ, d);
                int[] nextBlue = roll(cur.blueI, cur.blueJ, d);

                // 파란 구슬이 구멍에 빠지면 무시
                if (board[nextBlue[0]][nextBlue[1]] == 'O') continue;

                // 두 구슬이 같은 칸에 있을 경우, 더 많이 이동한 구슬을 뒤로 이동
                if (nextRed[0] == nextBlue[0] && nextRed[1] == nextBlue[1]) {
                    if (Math.abs(cur.redI - nextRed[0]) + Math.abs(cur.redJ - nextRed[1]) >
                            Math.abs(cur.blueI - nextBlue[0]) + Math.abs(cur.blueJ - nextBlue[1])) {
                        nextRed[0] -= di[d];
                        nextRed[1] -= dj[d];
                    } else {
                        nextBlue[0] -= di[d];
                        nextBlue[1] -= dj[d];
                    }
                }

                if (visited[nextRed[0]][nextRed[1]][nextBlue[0]][nextBlue[1]]) continue;

                queue.offer(new Node(nextRed[0], nextRed[1], nextBlue[0], nextBlue[1], cur.count + 1));
                visited[nextRed[0]][nextRed[1]][nextBlue[0]][nextBlue[1]] = true;
            }
        }

        return false; // 실패
    }

    static int[] roll(int i, int j, int d) {
        while (true) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (board[ni][nj] == '#' || board[i][j] == 'O') break; // 벽이나 구멍을 만나면 정지
            i = ni;
            j = nj;
        }
        return new int[]{i, j};
    }

    static boolean check(Node cur) {
        // 빨간 구슬만 구멍에 들어갔을 때
        if (board[cur.redI][cur.redJ] == 'O' && board[cur.blueI][cur.blueJ] != 'O') {
            return true;
        }
        return false;
    }
}