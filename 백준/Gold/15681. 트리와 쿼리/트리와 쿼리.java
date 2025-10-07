import java.io.*;
import java.util.*;

public class Main {
    static int N, R, Q;
    static List<List<Integer>> graph;
    static int[] dp;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점 수
        R = Integer.parseInt(st.nextToken()); // 루트 번호
        Q = Integer.parseInt(st.nextToken()); // 쿼리 수

        graph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 저장
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dp = new int[N + 1];
        visited = new boolean[N + 1];

        // dfs :: R을 root 노드로 하는 트리 찾으며 서브 트리의 정점의 개수 dp 배열에 저장
        dfs(R);

        // 루트 정점
        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            System.out.println(dp[u]);
        }
    }

    static int dfs(int u) {
        visited[u] = true;
        dp[u] = 1;

        for (int next : graph.get(u)) {
            if (visited[next]) continue;
            dp[u] += dfs(next);
        }

        return dp[u];
    }

}
