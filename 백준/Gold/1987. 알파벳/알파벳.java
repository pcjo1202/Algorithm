import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result;
    static char[][] map;
    static HashSet<Character> list;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        result = 0;
        list = new HashSet<>();

        visited = new boolean[N][M];
        list.add(map[0][0]);
        dfs(0, 0, 1);

        System.out.println(result);
    }

    static void dfs(int i, int j, int count) {
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
            if (visited[ni][nj]) continue;

            // 다음 지나가려는 칸이 list에 담겨있지 않은 알파벳일 때 탐색
            char next = map[ni][nj];
            if (!list.contains(next)) {
                visited[ni][nj] = true;
                list.add(next);
                count++;
                dfs(ni, nj, count);
                count--;
                visited[ni][nj] = false;
                list.remove(next);
            }
        }

        result = Math.max(result, count);
    }
}