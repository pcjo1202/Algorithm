import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static List<int[]> node;
    static int[] parents;
    static double E;
    static long result;

    static class Edge implements Comparable<Edge> {
        int start, end;// 노드 번호 : x, [1]:y
        double L;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
            int startX = node.get(start)[0];
            int startY = node.get(start)[1];
            int endX = node.get(end)[0];
            int endY = node.get(end)[1];
            this.L = E * (Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2));
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.L, o.L);
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            node = new ArrayList<>();

            // 정점을 저장할 리스트
            for (int i = 0; i < N; i++) {
                node.add(i, new int[2]);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                node.get(i)[0] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                node.get(i)[1] = Integer.parseInt(st.nextToken());
            }

            E = Double.parseDouble(br.readLine());

            List<Edge> edges = new ArrayList<>();

            // 나올 수 있는 모든 Edge 만들기
            for (int i = 0; i < N; i++) {
                for (int j = i; j < N; j++) {
                    if (i == j) continue;
                    edges.add(new Edge(i, j));
                }
            }

            // 각 노드를 저장할 서로소 집합
            parents = new int[N + 1];
            Arrays.fill(parents, -1);


            // 가중치를 기준으로 정렬
            Collections.sort(edges);


            // 최소 신장 트리 구성
            // 그래프 생성
            long cnt = 0;
            double cost = 0; // cnt : 간선의 수, cost : 가중치
            for (Edge e : edges) {
                if (union(e.start, e.end)) { // 집합이 생성되면
                    cost += e.L;
                    cnt++;
                    if (cnt == N - 1) break;
                }
            }

            sb.append(String.format("#%d %d\n", test_case, Math.round(cost)));
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