import java.io.*;
import java.util.*;

public class Solution {
    static int V, E;
    static int[] parents;

    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
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

            Edge[] edges = new Edge[E];

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                edges[i] = new Edge(start, end, weight);
            }

            // 서로소 집합 생성
            parents = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                parents[i] = -1;
            }

            // 가중치를 기준으로 정렬
            Arrays.sort(edges);

            // 그래프 생성
            long cnt = 0, cost = 0; // cnt : 간선의 수, cost : 가중치
            for (Edge e : edges) {
                if (union(e.start, e.end)) { // 집합이 생성되면
                    cost += e.weight;
                    cnt++;
                    if (cnt == V - 1) break;
                }
            }

            sb.append(String.format("#%d %d\n", test_case, cost));
        }
        System.out.println(sb);
    }

    static int find(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;

        if (parents[aRoot] < parents[bRoot]) { // 집합의 크기가 더 크다면,
            parents[aRoot] += parents[bRoot];
            parents[bRoot] = aRoot;
        } else {
            parents[bRoot] += parents[aRoot];
            parents[aRoot] = bRoot;
        }
        return true;
    }
}