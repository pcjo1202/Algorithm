import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static int[][] map;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static class Node {
        int i, j, dist;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
            this.dist = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result = Integer.MAX_VALUE;

        visited = new boolean[N + 1][N + 1];


        int cnt = 1;
        // 섬 구별하기 (1번, 2번, 3번)
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    findIsland(new Node(i, j), cnt);
                    cnt++;
                }
            }
        }

        // 1번, 2번, 3번 섬에서 연결 할 수 있는 다리를 찾자
        // 각 섬의 가장 자리에 있는 격자를 출발 점으로 지정
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (!visited[i][j]) continue; // 섬이 아닌 지역은 패스
                if (map[i][j] != 0) {
                    getBridge(new Node(i, j));
                }
            }
        }


//        for (int[] arr : map) {
//            System.out.println(Arrays.toString(arr));
//        }

        // 결과 출력
        System.out.println(result);
    }

    static void getBridge(Node start) {
        int[][] temp = new int[N + 1][N + 1];
        for (int[] arr : temp) {
            Arrays.fill(arr, -1);
        }

        int curIsland = map[start.i][start.j];
        int dist = 0;

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        temp[start.i][start.j] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (temp[cur.i][cur.j] > result) continue;

            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if (ni <= 0 || ni > N || nj <= 0 || nj > N) continue;
                if (temp[ni][nj] != -1) continue;

                // 다른 섬을 만날 시, 해당 다리의 길이 저장 후 종료
                if (map[ni][nj] != 0 && map[ni][nj] != curIsland) {
                    dist = temp[cur.i][cur.j];

                    result = Math.min(result, dist);
                    return;
                }

                // 다른 섬을 안만났다면, 계속 진행
                temp[ni][nj] = temp[cur.i][cur.j] + 1;
                queue.offer(new Node(ni, nj));
            }
        }
        return;
    }

    static void findIsland(Node start, int cnt) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.i][start.j] = true;
        map[start.i][start.j] = cnt;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if (ni <= 0 || ni > N || nj <= 0 || nj > N) continue;
                if (visited[ni][nj]) continue;
                if (map[ni][nj] == 1) {
                    visited[ni][nj] = true;
                    queue.offer(new Node(ni, nj));
                    map[ni][nj] = cnt;
                }

            }
        }
    }
}