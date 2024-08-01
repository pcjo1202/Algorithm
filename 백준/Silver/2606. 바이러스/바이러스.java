import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static boolean[] visited;
    private static List<ArrayList<Integer>> graph = new ArrayList<>();
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); // 컴퓨터 수
        int M = Integer.parseInt(br.readLine()); // 컴퓨터 쌍의 수

        visited = new boolean[N + 1]; // 1번 노드부터 N번 노드까지 있으므로 + 1

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) { // graph 입력 받기
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        dfs(1);

        System.out.println(count - 1);
    }

    public static void dfs(int i) {
        if (!visited[i]) {
            count++;
            visited[i] = true;
            for (int node : graph.get(i)) {
                dfs(node);
            }
        }

    }
}
