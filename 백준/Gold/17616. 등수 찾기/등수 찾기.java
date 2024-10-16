import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X, U, V;
    static int[][] board, dp;
    static List<Pair> list;

    static class Pair {
        int win, lose;

        public Pair(int win, int lose) {
            this.win = win;
            this.lose = lose;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 학생 번호 (1 ~ N)
        M = Integer.parseInt(st.nextToken()); // 질문 횟 수
        X = Integer.parseInt(st.nextToken()); // 등수 알고 싶은 학생 번호
        U = 0; // 가장 높은 등수
        V = 0; // 가장 낮은 등수

        list = new ArrayList<>();
        board = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a][b] = 1;
            list.add(new Pair(a, b));
        }


        // 나보다 작은 사람 구하기
        for (int i = 1; i < N + 1; i++) {
            dfs(i, i);
        }

        int winSum = 0; // X 보다 낮은
        int loseSum = 0; // X 보다 높은
        int mola = 0; // 모르는 사람
        for (int i = 1; i < N + 1; i++) {
            if (board[X][i] == 1) {
                winSum++;
            }

            if (board[i][X] == 1) {
                loseSum++;
            }

            if (i != X && board[X][i] == 0) {
                mola++;
            }
        }
//
//        System.out.println("나보다 낮은: " + winSum);
//        System.out.println("나보다 높은: " + loseSum);
//        System.out.println("몰라: " + mola);

        // 가장 높은 등수 : 모르는 사람이 나보다 뒤에
        U = loseSum + 1;
        // 가장 낮은 등수 : 모르는 사람이 나보다 앞에
        V = N - winSum;
        System.out.printf("%d %d", U, V);
    }

    static void dfs(int prev, int cur) {
        // 기저 조건
        for (int i = 1; i < N + 1; i++) {
            if (board[cur][i] == 1) { // Node가 이기는 상대라면
                board[prev][i] = board[cur][i];
                dfs(cur, i);
            }
        }
    }
}