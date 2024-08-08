import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<ArrayList<Integer>> graph;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수
        int V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점 번호

        graph = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (ArrayList<Integer> each : graph) {
            Collections.sort(each, ((o1, o2) -> o1 - o2));
        }

        dfs(V, visited);
        visited = new boolean[N + 1];
        System.out.println();
        bfs(V, visited);
    }

    public static void bfs(int v, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(v); // 큐에 넣기

        while (!queue.isEmpty()) {
            int cur = queue.poll(); // 가장 앞에 있는 큐 꺼내기

            if (!visited[cur]) {
                visited[cur] = true;
                System.out.printf("%d ", cur); // 꺼낸 항목 출력 처리하기
            }

            if (!graph.get(cur).isEmpty()) {
                for (int i = 0; i < graph.get(cur).size(); i++) {
                    int node = graph.get(cur).get(i);
                    if (!visited[node]) {
                        // 연결되어 있는 친구들에 큐에 넣기
                        queue.offer(node);
                    }
                }
            }
        }
    }

    public static void dfs(int v, boolean[] visited) {
        visited[v] = true;
        System.out.printf("%d ", v);

        if (!graph.get(v).isEmpty()) {
            for (int i = 0; i < graph.get(v).size(); i++) {
                int node = graph.get(v).get(i);
                if (!visited[node]) {
                    dfs(node, visited);
                }
            }
        }
    }
}
