import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
    static int[] di = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dj = {0, 1, 0, -1};
    static int[][] map = null;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            int curNum = 1;
            int i = 0;
            int j = 0;
            int d = 1; // 시작 방향
            // 우(1) -> 하(2) -> 좌(3) -> 위(0)
            while (curNum <= N * N) {
                // 등록
//                System.out.println(curNum);
                map[i][j] = curNum;
                // 다음 위치로 이동
                int ni = i + di[d];
                int nj = j + dj[d];
                // 다음 공간이 막히는 경우 (이미 숫자가 들어있거나, 벽인 경우)
                // -> 오른쪽으로 회전
                if (ni < 0 || ni >= N || nj < 0 || nj >= N || map[ni][nj] != 0) {
                    d = ((d + 1) % 4);
                    ni = i + di[d];
                    nj = j + dj[d];
                }
//                else if (map[i][j] != 0) {
//                    d = ((d + 1) % 4);
//                    ni = i + di[d];
//                    nj = j + dj[d];
//                }
                i = ni;
                j = nj;
                curNum++;
            }
            System.out.printf("#%d\n", test_case);
            for (int k = 0; k < N; k++) {
                for (int l = 0; l < N; l++) {
                    System.out.printf("%d ", map[k][l]);
                }
                System.out.println();
            }
//            sb.append("#").append(test_case).append("\n").append(result).append("\n");
        }
//        System.out.println(sb);
    }
}
