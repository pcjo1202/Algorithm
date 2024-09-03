import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, L, result;
    static Pair start;
    static int[][] map;
    static int[][] visited;
    static HashMap<Integer, int[]> pipes;
    static int[] di = {-1, 1, 0, 0}; // 상하좌우
    static int[] dj = {0, 0, -1, 1};

    static {
        pipes = new HashMap<>();
        pipes.put(1, new int[]{0, 1, 2, 3});
        pipes.put(2, new int[]{0, 1});
        pipes.put(3, new int[]{2, 3});
        pipes.put(4, new int[]{0, 3});
        pipes.put(5, new int[]{1, 3});
        pipes.put(6, new int[]{1, 2});
        pipes.put(7, new int[]{0, 2});
    }

    static class Pair {
        int X, Y;

        public Pair(int x, int y) {
            X = x;
            Y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            start = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new int[N][M];

            for (int[] arr : visited) {
                Arrays.fill(arr, -1);
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            result = 0;

            bfs();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j] != -1) {
                        result++;
                    }
                }
            }

            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }

    static void bfs() {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.X][start.Y] = 1;

        int cnt = 1;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            if (visited[cur.X][cur.Y] == L) break;

            // 현재 위치의 파이프 모양 확인
            int curPipe = map[cur.X][cur.Y];

            // 연결 가능한 방향의 파이프 확인
            for (int d = 0; d < pipes.get(curPipe).length; d++) {
                int cd = pipes.get(curPipe)[d];
                int ni = cur.X + di[cd];
                int nj = cur.Y + dj[cd];

                if (ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == 0) continue;

                if (visited[ni][nj] != -1) continue; // 파이프인데, 방문한 곳이라면,

                int nextPipe = map[ni][nj]; // 다음 곳의 파이프 번호

                // 가지치기 : 애초에 연결이 안되는 파이프 컷

                // 현재 파이프와 다음 파이프가 연결 가능한지 확인
                for (int i = 0; i < pipes.get(nextPipe).length; i++) {
                    int nd = pipes.get(nextPipe)[i];
                    if (isConnect(cd, nd)) {// 연결 가능하면 다음 큐에 넣기
                        queue.offer(new Pair(ni, nj));
                        visited[ni][nj] = visited[cur.X][cur.Y] + 1;
                    }
                }
            }
        }
    }

    static boolean isConnect(int a, int to) {
        // 상 0 하 1 좌 2 우 3
        // 상-하 => 1
        // 좌-우 => 5
        int temp = a + to;
        return temp == 1 || temp == 5;
    }
}