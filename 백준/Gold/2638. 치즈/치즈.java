import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static class Node {
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 바깥 공기 번호 매기기
//        setting();
//
//        for (int[] arr : map) {
//            System.out.println(Arrays.toString(arr));
//        }

        int time = 0;
        while (true) {
            // 바깥 공기 업데이트
            setting();
            List<Node> remove = new ArrayList<>();

            // 각 치즈가 녹은지 아닌지 체크
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 치즈일 때 바깥 공기와 만나는 지 체크
                    if (map[i][j] == 1) {
                        if (check(i, j)) {
                            flag = true;
                            remove.add(new Node(i, j));
                        }
                    }
                }
            }

            if (!flag) break;

            // 삭제할 치즈 map에 반영
            for (Node node : remove) {
                map[node.i][node.j] = -1;
            }

            time++;
        }
        System.out.println(time);
    }

    static void setting() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        queue.offer(new Node(0, 0)); // 가장 자리는 치가 없으므로
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            map[cur.i][cur.j] = -1;

            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if (visited[ni][nj]) continue;
                if (map[ni][nj] == 1) continue;

                queue.offer(new Node(ni, nj));
                visited[ni][nj] = true;
            }
        }
    }

    static boolean check(int i, int j) {
        int cnt = 0;
        // 4변 중 실내온도의 공기와 접촉한 개수 확인
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (map[ni][nj] == -1) {
                cnt++;
            }
        }
        return cnt >= 2;
    }
}