import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H, result = Integer.MAX_VALUE, size;
    static int[][] board;
    static List<Node> list;

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

        N = Integer.parseInt(st.nextToken()); // 세로선 개수
        M = Integer.parseInt(st.nextToken()); // 가로선 개수
        H = Integer.parseInt(st.nextToken()); // 가로선을 놓을 수 있는 위치 개수

        board = new int[H + 1][N + 2];
        list = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // a 번째 점선 위치
            int b = Integer.parseInt(st.nextToken()); // b - b+1 연결

            board[a][b] = 1;
        }

        // 사다리를 추가할 수 있는 곳의 목록
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (board[i][j] == 0) {
                    list.add(new Node(i, j));
                }
            }
        }
        size = list.size(); // 놓을 수 있는 공간의 수

        // i개 가로선을 추가하며 조건 충족 여부 확인
        for (int i = 0; i <= 3; i++) {
            dfs(0, i, 0);

            if (result != Integer.MAX_VALUE) {
                System.out.println(result);
                return;
            }
        }

        System.out.println(-1);
    }

    static void dfs(int depth, int r, int start) {
        if (depth == r) {
            if (check()) result = r;
            return;
        }

        for (int i = start; i < size; i++) {
            Node cur = list.get(i);
            if (board[cur.i][cur.j - 1] == 0 && board[cur.i][cur.j + 1] == 0) {
                board[cur.i][cur.j] = 1;
                dfs(depth + 1, r, i + 1);
                board[cur.i][cur.j] = 0;
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= N; i++) {
            int cur = i;

            for (int j = 1; j <= H; j++) {
                if (board[j][cur] == 1) cur++;
                else if (board[j][cur - 1] == 1) cur--;
            }

            if (cur != i) return false;
        }
        return true;
    }
}