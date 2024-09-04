import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, K, result, startNum;
    static int[][] map;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            result = 0;
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            startNum = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    startNum = Math.max(startNum, map[i][j]);
                }
            }

            // 높이가 가장 큰 위치에서 탐색 시작
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == startNum) {
                        visited[i][j] = true;
                        dfs(new Point(i, j), 1, false);
                        visited[i][j] = false;
                    }
                }
            }

            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }

    static void dfs(Point cur, int tempSum, boolean cutFlag) {
        int ci = cur.i;
        int cj = cur.j;
        int curCost = map[ci][cj];
        // 상하 좌우 탐색
        for (int d = 0; d < 4; d++) {
            int ni = ci + di[d];
            int nj = cj + dj[d];

            if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
            if (visited[ni][nj]) continue;

            int nextCost = map[ni][nj];
            // 다음 곳이 이동 가능한 곳이라면 이동!
            if (curCost > nextCost) {
                visited[ni][nj] = true;
                dfs(new Point(ni, nj), tempSum + 1, cutFlag);
                visited[ni][nj] = false;
            }
            // 이동 하지 못하는 곳이라면(curCost 보다 nextCost가 같거나 크다면)
            // => 등산로를 깎아본다.
            // 등산로를 갂아 봤는데, 못간다면 거기서 종료
            else {
                if (!cutFlag/* 등산로를 깍아 본적 없다면*/) {
//                    if (curCost == nextCost || ) { // 같거나, next가 큰데, 최대
//
//                    }
                    for (int i = 1; i <= K; i++) {
                        if (curCost > nextCost - i) { // 깎아서 이동 가능하다면
                            map[ni][nj] -= i; // 깎은 상태로 넘기기
                            visited[ni][nj] = true;
                            dfs(new Point(ni, nj), tempSum + 1, true); // 깎은 후에는 cutFlag true로 변경

                            visited[ni][nj] = false;
                            map[ni][nj] = nextCost; // 원상복구
                        }
                    }
                }
            }
        }
        result = Math.max(result, tempSum);
    }
}