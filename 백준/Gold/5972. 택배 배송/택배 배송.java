import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<ArrayList<Edge>> list;
    static int[] distance;

    static class Edge {
        int u, cost;

        public Edge(int u, int cost) {
            this.u = u;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "u=" + u +
                    ", cost=" + cost +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 헛간 개수
        M = Integer.parseInt(st.nextToken()); // 간선 수

        list = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Edge(b, c));
            list.get(b).add(new Edge(a, c));
        }

        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        dijkstra(1);

        System.out.println(distance[N]);
    }

    static void dijkstra(int start) {
        distance[start] = 0; // 최단 거리 배열

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.cost > distance[cur.u]) continue;

            for (Edge next : list.get(cur.u)) {
                if (distance[next.u] > cur.cost + next.cost) {
                    distance[next.u] = cur.cost + next.cost;
                    pq.offer(new Edge(next.u, cur.cost + next.cost));
                }
            }
        }
    }
}
