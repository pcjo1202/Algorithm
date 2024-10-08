import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result, idx;
    static char[][] map;
    static int[][] visited;
    static HashMap<Character, Pair> direction; // 방향 저장

    static {
        direction = new HashMap<>();
        direction.put('U', new Pair(-1, 0));
        direction.put('D', new Pair(1, 0));
        direction.put('L', new Pair(0, -1));
        direction.put('R', new Pair(0, 1));
    }

    static class Pair {
        int i, j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new int[N][M]; // 사이클을 구분하기 위해 int

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // SAFE ZONE
        // 1. 사이클 찾기.
        // -> 사이클 표시
        result = 0;
        idx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0) { // 탐색 하지 않은 곳이라면
                    bfs(new Pair(i, j));
                    idx++;
                }
            }
        }

        System.out.println(result); // 만들어진 사이클의 개수

    }

    static void bfs(Pair start) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(start);


        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            char curDirect = map[cur.i][cur.j];

            Pair d = direction.get(curDirect); // 방향

            Pair next = new Pair(cur.i + d.i, cur.j + d.j); // 다음 확인할 격자

            if (visited[next.i][next.j] == 0) { // 아직 탐색하기 전이라면
                queue.offer(next);
                visited[next.i][next.j] = idx;
            } else if (visited[next.i][next.j] == idx) {
                result++;
            }
        }
    }

}