import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int N, M, result;
    static int[][] map;
    static boolean[] isSelected;
    static List<Node> virus, blank;
    static int[] di = {-1, 1, 0, 0}; // 상 하 좌 우
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

        N = Integer.parseInt(st.nextToken()); // 열 크기
        M = Integer.parseInt(st.nextToken()); // 행 크기


        blank = new ArrayList<>();
        virus = new ArrayList<>();

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // blank 저장
                if (map[i][j] == 0) {
                    blank.add(new Node(i, j));
                } else if (map[i][j] == 2) {
                    // virus
                    virus.add(new Node(i, j));
                }
            }
        }

        result = 0;
        isSelected = new boolean[blank.size()];
        combination(0, 0);

        System.out.println(result);
    }

    static int spread(int[][] arr) {
        boolean[][] visited = new boolean[N][M];
        Queue<Node> queue = new ArrayDeque<>();

        // 초기 바이러스 위치를 큐에 넣음
        for (Node res : virus) {
            queue.offer(res);
            visited[res.i][res.j] = true;
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if (visited[ni][nj]) continue;

                // 바이러스 기준으로 주변에 빈칸이 있다면, 바이러 전파
                if (arr[ni][nj] == 0) {
                    arr[ni][nj] = 2;
                    queue.offer(new Node(ni, nj));
                    visited[ni][nj] = true;
                }
            }
        }
        
        int sum = 0; // 빈칸의 개수 구하기
        for (int[] temp : arr) {
            for (int res : temp) {
                if (res == 0) {
                    sum++;
                }
            }
        }

        return sum;
    }

    static void combination(int depth, int start) {
        // 기저 조건
        if (depth == 3) {

            int[][] temp = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    temp[i][j] = map[i][j];
                }
            }

            // 벽을 세우기로 선택한 곳에 벽을 세우고, 바이러스 전파를 시킨다.
            for (int i = 0; i < blank.size(); i++) {
                if (isSelected[i]) {
                    Node cur = blank.get(i);
                    temp[cur.i][cur.j] = 1;
                }
            }

            // 바이러스 전파 시키고, 안전 영역 구하기.
            result = Math.max(result, spread(temp));
            return;
        }

        for (int i = start; i < blank.size(); i++) {
            if (isSelected[i]) continue;

            isSelected[i] = true;
            combination(depth + 1, i + 1);
            isSelected[i] = false;
        }
    }
}