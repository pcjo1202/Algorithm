import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E, start;
    static final int INF = Integer.MAX_VALUE;
    static List<ArrayList<Node>> graph;
    static int[] distance;

    static class Node implements Comparable<Node> {
        int id, weight;

        public Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); // 정점의 개수
        E = Integer.parseInt(st.nextToken()); // 간선의 개수

        start = Integer.parseInt(br.readLine()); // 시작 정점

        graph = new ArrayList<>();

        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // from 정점
            int v = Integer.parseInt(st.nextToken()); // to 정점
            int w = Integer.parseInt(st.nextToken()); // 해당 간선의 가중치

            graph.get(u).add(new Node(v, w));
        }


        distance = new int[V + 1]; // 각 정점에 대한 최단 경로
        Arrays.fill(distance, INF);
        dijkstra(start);

        for (int i = 1; i < V + 1; i++) {
            System.out.println(distance[i] == INF ? "INF" : distance[i]);
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // Node에서 Comparable로 정렬한대로 가중치가 가장 작은 친구부터 나옴

        pq.offer(new Node(start, 0)); // 처음 시작할 지점의 노드와, 가중치 0을 넣어준다.
        distance[start] = 0; // 시작노드 최단 거리 0으로

        while (!pq.isEmpty()) {
            Node cur = pq.poll(); // 현재 pq에 들어가있는 것 중 가중치가 가장 작은 친구
            int now = cur.id;
            int curW = cur.weight;

            // 0. 현재 노드가 이미 처리된 적이 있는데, 이미 최단 거리라면, 건너뛰기
            if (distance[now] < curW) continue;

            // 1. 인접한 노드을 탐색하며
            for (int i = 0; i < graph.get(now).size(); i++) {
                Node next = graph.get(now).get(i); // 인접한 노드
                int cost = distance[now] + next.weight; // (현재 노드 ~ 인접한 노드 거리)

                // 2. (현재 노드의 최단 거리 + (현재 노드 ~ 인접한 노드)) 와 인접한 노드까지의 최단 거리 비교하여 최단 거리 갱신
                if (distance[next.id] > cost) {
                    distance[next.id] = cost;

                    // 3. 갱신한 친구 pq에 넣기
                    pq.offer(new Node(next.id, cost));
                }
            }

        }
    }
}