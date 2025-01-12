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
        int i, j, count;

        public Node(int i, int j, int count) {
            this.i = i;
            this.j = j;
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
                    red = new Node(i, j, 0);
                } else if (board[i][j] == 'B') {
                    blue = new Node(i, j, 0);
                } else if (board[i][j] == 'O') {
                    hole = new Node(i, j, 0);
                }
            }
        }

        System.out.println(bfs() ? 1 : 0);
    }

    static boolean bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(red.i, red.j, 0));
        queue.offer(new Node(blue.i, blue.j, 0));
        visited[red.i][red.j][blue.i][blue.j] = true;

        while (!queue.isEmpty()) {
            Node curRed = queue.poll();
            Node curBlue = queue.poll();

            if (curRed.count > 10 || curBlue.count > 10) break; // 최대 10번까지 이동 가능
            if (check(curRed, curBlue)) return true; // 성공 조건 만족

            for (int d = 0; d < 4; d++) {
                int[] nextRed = roll(curRed.i, curRed.j, d);
                int[] nextBlue = roll(curBlue.i, curBlue.j, d);

                // 파란 구슬이 구멍에 빠지면 무시
                if (board[nextBlue[0]][nextBlue[1]] == 'O') continue;

                // 두 구슬이 같은 칸에 있을 경우, 더 많이 이동한 구슬을 뒤로 이동
                if (nextRed[0] == nextBlue[0] && nextRed[1] == nextBlue[1]) {
                    if (Math.abs(curRed.i - nextRed[0]) + Math.abs(curRed.j - nextRed[1]) >
                            Math.abs(curBlue.i - nextBlue[0]) + Math.abs(curBlue.j - nextBlue[1])) {
                        nextRed[0] -= di[d];
                        nextRed[1] -= dj[d];
                    } else {
                        nextBlue[0] -= di[d];
                        nextBlue[1] -= dj[d];
                    }
                }

                if (visited[nextRed[0]][nextRed[1]][nextBlue[0]][nextBlue[1]]) continue;

                queue.offer(new Node(nextRed[0], nextRed[1], curRed.count + 1));
                queue.offer(new Node(nextBlue[0], nextBlue[1], curBlue.count + 1));
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

    static boolean check(Node curRed, Node curBlue) {
        // 빨간 구슬만 구멍에 들어갔을 때
        return board[curRed.i][curRed.j] == 'O' && board[curBlue.i][curBlue.j] != 'O';
    }
}