import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
    static final int SIZE = 100;
    static int result;
    static int[][] board = new int[SIZE][SIZE];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int aa = Integer.parseInt(br.readLine()); // 첫번째 테케 방지

            for (int i = 0; i < SIZE; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < SIZE; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            solve();

            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void solve() {
        // 출발할 수 있는 지점 찾기
        for (int startX = 0; startX < SIZE; startX++) {
            if (board[0][startX] == 1) {
                int i = 0;
                int j = startX;

                while (i < SIZE) {
                    // 도착 지점(2)에 도달하면 result 갱신하고 break
                    if (board[i][j] == 2) {
                        result = startX;
                        break;
                    }

                    // 좌우 이동을 우선 체크
                    if (j > 0 && board[i][j - 1] == 1) { // 왼쪽으로 이동 가능할 경우
                        while (j > 0 && board[i][j - 1] == 1) { // 이동 할 수 있을 때까지 계속 이동
                            j--;
                        }
                    } else if (j < SIZE - 1 && board[i][j + 1] == 1) { // 오른쪽으로 이동 가능할 경우
                        while (j < SIZE - 1 && board[i][j + 1] == 1) { // 이동 할 수 있을 때까지 계속 이동
                            j++;
                        }
                    }
                    // 좌우 이동이 끝나면 아래로 이동
                    i++;
                }
            }
        }
    }
}