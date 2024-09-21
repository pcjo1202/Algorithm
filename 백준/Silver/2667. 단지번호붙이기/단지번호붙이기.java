import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> result;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        result = new ArrayList<>();
        visited = new boolean[N][N];

        int num = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    result.add(1);

                    visited[i][j] = true;
                    map[i][j] = num;

                    dfs(i, j, num);
                    num++;
                }
            }
        }

        Collections.sort(result);

        // 답
        System.out.println(result.size());
        for (int res : result) {
            System.out.println(res);
        }
    }

    static void dfs(int i, int j, int num) {
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
            if (visited[ni][nj]) continue;

            if (map[ni][nj] == 1) {
                map[ni][nj] = num;
                visited[ni][nj] = true;
                int cur = result.get(num - 1);
                cur++;
                result.set(num - 1, cur);

                dfs(ni, nj, num);
            }
        }
    }
}