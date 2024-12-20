import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, start, end;
    static List<ArrayList<Edge>> edges;
    //    static int[] distance;
    static Edge[] distance;

    static class Edge {
        int node, cost;
        Edge prev;

        public Edge() {

        }

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
            prev = new Edge();
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "node=" + node +
                    ", cost=" + cost +
                    ", prev=" + prev +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine()); // 도시의 개수 (노드)
        M = Integer.parseInt(br.readLine()); // 버스의 개수 (간선)

        edges = new ArrayList<>(); // 입력 받은..간선 리스트 저장

        for (int i = 0; i < N + 1; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()); // 출발 도시번호
            int to = Integer.parseInt(st.nextToken()); // 도착 도시번호
            int cost = Integer.parseInt(st.nextToken()); // 버스 비용

            edges.get(from).add(new Edge(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()); // 출발 도시번호
        end = Integer.parseInt(st.nextToken()); // 도착 도시번호


        distance = new Edge[N + 1];
        for (int i = 0; i < N + 1; i++) {
            distance[i] = new Edge(i, Integer.MAX_VALUE);
        }

        dijkstra();


        int nodeNum = end;
        List<Integer> path = new ArrayList<>();
        while (true) {
            path.add(nodeNum); // end -> start node 찾으며 경로 추가

            if (nodeNum == start) break;
            
            nodeNum = distance[nodeNum].prev.node; // 현재 노드의 prev 노드로 바꿈

        }

        // 최소 비용을 출력
        System.out.println(distance[end].cost);

        // 최소 비용을 갖는 경로에 포함되어있는 도시의 개수를 출력
        System.out.println(path.size());

        // 최소 비용을 갖는 경로를 방문하는 도시 순서대로 출력
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }

    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        distance[start].cost = 0;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll(); // 인접한 도시 중 가장 비용이 작은 놈 선택

            if (cur.cost > distance[cur.node].cost) continue;


            // 인접한 노드 탐색
            for (Edge next : edges.get(cur.node)) {
                if (distance[next.node].cost > cur.cost + next.cost) {
                    distance[next.node].cost = cur.cost + next.cost;
                    pq.offer(new Edge(next.node, cur.cost + next.cost));
                    // 바로 전 node 저장
                    distance[next.node].prev = cur;
                }
            }

            // 현재 노드가 도작 지점이면 다음을 찾지 않고 종료
            if (cur.node == end) {
                break;
            }
        }
    }
}