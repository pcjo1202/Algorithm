import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] map;
    static int[][] visited;
    static int[] di = {-1, 1, 0, 0}; // 상하좌우
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

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // result_1 : 적록색약 X 사람이 봤을 때 구역 수
        // result_2 : 적록색약인 O 봤을 때의 구역의 수 -> R + G

        System.out.println(solve(false) + " " + solve(true));
    }

    static int solve(boolean flag) {
        visited = new int[N][N];
        for (int[] arr : visited) {
            Arrays.fill(arr, -1);
        }

        int number = 0; // 구역을 구분할 번호
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] > -1) continue;
                bfs(number++, new Point(i, j), flag);
            }
        }

        return number;
    }

    static void bfs(int number, Point start, boolean flag) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.i][start.j] = number;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            char target = map[cur.i][cur.j];

            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                if (visited[ni][nj] > -1) continue;
                char next = map[ni][nj];

                if (flag) { // R + G
                    if (target == 'B' && next == 'B') {
                        queue.offer(new Point(ni, nj));
                        visited[ni][nj] = number;
                    } else if (target == 'R' || target == 'G') {
                        if (next == 'R' || next == 'G') {
                            queue.offer(new Point(ni, nj));
                            visited[ni][nj] = number;
                        }
                    }
                } else {
                    if (target == next) {
                        queue.offer(new Point(ni, nj));
                        visited[ni][nj] = number;
                    }
                }
            }
        }
    }
}
