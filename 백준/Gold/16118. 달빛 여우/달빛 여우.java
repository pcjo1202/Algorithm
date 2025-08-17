import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] distance1;
    static int[][] distance2;
    static List<ArrayList<Edge>> graph;

    static class Edge {
        int u, cost, step;

        public Edge(int u, int value) {
            this.u = u;
            this.cost = value;
        }

        public Edge(int u, int value, int step) {
            this.u = u;
            this.cost = value;
            this.step = step;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        distance1 = new int[N + 1]; // 여우
        distance2 = new int[N + 1][2]; // 늑대

        Arrays.fill(distance1, Integer.MAX_VALUE);
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(distance2[i], Integer.MAX_VALUE);
        }

        // 도착지 확인
        dijkstra(distance1); // 여우
        dijkstra(distance2); // 늑대

        // 최단거리배열에서 여우와 늑대가 도착한 시간 비교
        // distance1[] < distance2[] 인 개수 확인
        int cnt = 0;
        for (int i = 1; i < N + 1; i++) {
            if (distance1[i] < Math.min(distance2[i][0], distance2[i][1])) cnt++;
        }

        System.out.println(cnt);
    }

    /*
     * 여우(true) : 일정한 속도 (속도 2)
     * 늑대(false) : 여우의 두배 (속도 4) -> 여우의 절반 반복 (속도 1)
     *
     * 여우와 늑대의 가중치를 변경한다!
     * */

    // 여우
    static void dijkstra(int[] distance) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.cost, o2.cost)));

        pq.offer(new Edge(1, 0)); // 시작 노드
        distance[1] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.cost > distance[cur.u]) continue; // 최단거리가 아니라면 패스

            for (Edge next : graph.get(cur.u)) {
                int nextCost = next.cost * 2; // 가중치

                if (distance[next.u] > cur.cost + nextCost) {
                    distance[next.u] = cur.cost + nextCost;
                    pq.offer(new Edge(next.u, cur.cost + nextCost));
                }
            }
        }
    }

    // 늑대
    static void dijkstra(int[][] distance) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.cost, o2.cost)));

        pq.offer(new Edge(1, 0, 0)); // 시작 노드
        distance[1][0] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.cost > distance[cur.u][cur.step % 2]) continue; // 최단거리가 아니라면 패스

            for (Edge next : graph.get(cur.u)) {
                int nextCost;

                if (cur.step % 2 == 0) {
                    // 속도 1
                    nextCost = cur.cost + next.cost;
                } else {
                    // 속도 4
                    nextCost = cur.cost + next.cost * 4;
                }

                int nextStep = (cur.step + 1) % 2;

                if (distance[next.u][nextStep] > nextCost) {
                    distance[next.u][nextStep] = nextCost;
                    pq.offer(new Edge(next.u, nextCost, cur.step + 1));
                }
            }
        }
    }
}
