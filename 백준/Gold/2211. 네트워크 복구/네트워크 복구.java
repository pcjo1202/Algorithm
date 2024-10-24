import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] distance, result;
    static List<ArrayList<Node>> edges;

    static class Node {
        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "v=" + v +
                    ", cost=" + cost +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
        M = Integer.parseInt(st.nextToken()); // 회선 정보 개수

        edges = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.get(u).add(new Node(v, cost));
            edges.get(v).add(new Node(u, cost));
        }

        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);


        // 최소 개수의 회선만을 복구 -> 전체 컴퓨터를 최소 간선의 개수로 이으면 N -1 개
        result = new int[N + 1]; // 결과를 담을 리스트

        // 슈퍼 컴퓨터에서 각각의 컴퓨터까지의 최단거리
        dijkstra(1);

        System.out.println(N - 1);
        for (int i = 0; i < N + 1; i++) {
            if (result[i] == 0) continue;
            System.out.printf("%d %d\n", i, result[i]);
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node next : edges.get(cur.v)) {
                if (distance[next.v] > cur.cost + next.cost) {
                    distance[next.v] = cur.cost + next.cost;
                    result[next.v] = cur.v; // 1 ~ next 보다 1~ cur, cur ~ next가 더 짧음. 그러니까 연결되었다는 소리
                    pq.offer(new Node(next.v, distance[next.v]));
                }
            }
        }
    }
}