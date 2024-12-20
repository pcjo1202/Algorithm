import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int N;
    static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        board = new char[N][N * 2 - 1]; //

        for (char[] arr : board) {
            Arrays.fill(arr, ' ');
        }
        // 재귀적으로 바텀 업 형식으로 출력하게 할까?

        /*
         *        *
         *       * *
         *      *****
         * */
        printStar(N, 0, N - 1);

        // 결과 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    // 별의 높이, 별이 그려질 위치의 좌표
    static void printStar(int n, int i, int j) {
        // 기저 조건 : 높이의 수가 3이 되는 것이 최소
        if (n == 3) {
            // 제일 쪼꼬미 별 그리기
            board[i][j] = '*';
            board[i + 1][j - 1] = '*';
            board[i + 1][j + 1] = '*';
            for (int k = j - 2; k <= j + 2; k++) {
                board[i + 2][k] = '*';
            }
            return;
        }
        // 탐색 조건 : 작은 같은 크기의 별로 나누기
        printStar(n / 2, i, j);
        printStar(n / 2, i + n / 2, j + n / 2);
        printStar(n / 2, i + n / 2, j - n / 2);
    }
}