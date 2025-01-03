import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long result;
    static int[][] board;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine()); // 행성 수

        board = new int[N][N]; // 플로우 관리 비용 ( i -> j)

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];
        result = 0;
        // 모든 행성을 최소 비용으로 연결 -> MST
        // 정점 중심인 프림으로 MST 구하기
        result = prim();
        System.out.println(result);
    }

    static long prim() {
        long total = 0;
        // [0]: node, [1]: cost
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{0, 0}); // 0번부터 시작

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];

            if (visited[node]) continue;

            visited[node] = true;
            total += cost;

            for (int nextNode = 0; nextNode < N; nextNode++) {
                int nextCost = board[node][nextNode];
                if (visited[nextNode]) continue;

                pq.offer(new int[]{nextNode, nextCost});
            }
        }

        return total;
    }

}