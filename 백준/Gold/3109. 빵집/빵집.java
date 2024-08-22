import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int R, C, result;
    static char[][] map;
    static boolean flag;
    static boolean[][] visited;
    static int[] di = {-1, 0, 1}; // 오른쪽 위, 오른쪽, 오른쪽 아래
    static int[] dj = {1, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        result = 0;

        for (int i = 0; i < R; i++) {
            flag = false;
            map[i][0] = '-';
            dfs(i, 0);
//            for (boolean[] arr : visited) {
//                System.out.println(Arrays.toString(arr));
//            }
//            System.out.println(max);
        }
//        System.out.println("최종 상태");
//        for (char[] arr : map) {
//            System.out.println(Arrays.toString(arr));
//        }
        System.out.println(result);
    }

    static void dfs(int i, int j) {
        if (j == C - 1) {
            flag = true;
            result++;
            return;
        }


        for (int d = 0; d < 3; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (ni < 0 || ni >= R || nj < 0 || nj >= C || map[ni][nj] != '.') continue;

            if (flag) continue;
            map[ni][nj] = '-';
            dfs(ni, nj);
        }
    }
}