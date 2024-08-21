import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, result;
    static String direct;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            direct = st.nextToken();

            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

//            for (int[] arr : board) {
//                System.out.println(Arrays.toString(arr));
//            }
            // 기본적으로 오른쪽으로 미는 로직을 작성
            // 오른쪽으로 밀 도록 배열을 회전

            if (direct.equals("down")) { // 오른쪽 3번 회전
                rotateArr(3);
            } else if (direct.equals("left")) { // 오른쪽 2번 회전
                rotateArr(2);
            } else if (direct.equals("up")) { // 오른쪽 1번 회전
                rotateArr(1);
            }

            // 게임을 진행하는 로직
            game();

            // 원래대로 돌려 놓기
            if (direct.equals("down")) { // 오른쪽 3번 회전 -> 오른쪽으로 1번 더 돌려 원복
                rotateArr(1);
            } else if (direct.equals("left")) { // 오른쪽 2번 회전 -> 오른쪽으로 3번 더 돌려 원복
                rotateArr(2);
            } else if (direct.equals("up")) { // 오른쪽 1번 회전 -> 오른쪽으로 3번 더 돌려 원복
                rotateArr(3);
            }

            sb.append(String.format("#%d\n", test_case));
            for (int[] arr : board) {
                for (int cur : arr) {
                    sb.append(String.format("%d ", cur));
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    static void game() {
        for (int i = 0; i < N; i++) {
            int[] newRow = new int[N];
            int index = N - 1;

            for (int j = N - 1; j >= 0; j--) {
                if (board[i][j] != 0) {
                    newRow[index--] = board[i][j];
                }
            }

            for (int j = N - 1; j > 0; j--) {
                if (newRow[j] == newRow[j - 1] && newRow[j] != 0) {
                    newRow[j] *= 2;
                    newRow[j - 1] = 0;
                }
            }

            index = N - 1;
            for (int j = N - 1; j >= 0; j--) {
                if (newRow[j] != 0) {
                    board[i][index--] = newRow[j];
                }
            }
            while (index >= 0) {
                board[i][index--] = 0;
            }
        }
    }


    static void rotateArr(int rotateCount) {
        for (int k = 0; k < rotateCount; k++) {
            int[][] temp = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    temp[j][N - 1 - i] = board[i][j];
                }
            }
            board = temp;
        }
    }
}