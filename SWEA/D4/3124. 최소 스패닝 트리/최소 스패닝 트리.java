import java.io.*;
import java.util.*;

public class Solution {
    static int V, E;
    static List<Edge>[] adjList;

    static class Edge implements Comparable<Edge> {
        int end, weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                // 양방향 그래프
                adjList[start].add(new Edge(end, weight));
                adjList[end].add(new Edge(start, weight));
            }

            // 프림 알고리즘 수행
            boolean[] visited = new boolean[V + 1];
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.offer(new Edge(1, 0)); // 시작점 설정 (임의의 노드, 여기서는 1번 노드부터 시작)

            long cost = 0;
            int cnt = 0;

            while (!pq.isEmpty()) {
                Edge current = pq.poll();

                if (visited[current.end]) continue;

                visited[current.end] = true;
                cost += current.weight;
                cnt++;

                if (cnt == V) break; // 모든 정점을 다 방문했으면 종료

                for (Edge next : adjList[current.end]) {
                    if (!visited[next.end]) {
                        pq.offer(next);
                    }
                }
            }

            sb.append(String.format("#%d %d\n", test_case, cost));
        }

        System.out.println(sb);
    }
}