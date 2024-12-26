import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result;
    static List<Edge> edges; // 간선 리스트
    static int[] parents;

    static class Edge {
        int u, v, cost;

        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "u=" + u +
                    ", v=" + v +
                    ", cost=" + cost +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 집의 개수
        M = Integer.parseInt(st.nextToken()); // 길의 개수

        edges = new ArrayList<>();
        parents = new int[N + 1]; // 유니온 파인드 배열
        Arrays.fill(parents, -1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, cost));
        }

        result = Integer.MAX_VALUE;
        // 1. 크루스칼을 통해 MST를 구한다.
        Collections.sort(edges, ((o1, o2) -> o1.cost - o2.cost));

        int cost = 0;
        int count = 0;
        List<Edge> MST = new ArrayList<>();

        for (Edge cur : edges) {
            if (union(cur.u, cur.v)) {
                cost += cur.cost;
                count++;
                MST.add(cur);
            }

            if (count == N - 1) break;
        }

        // 2. 두 마을로 분리 하려면 하나의 선이 끊어져야함
        // 가장 작은 비용을 유지 하려면, 가장 큰 값을 제거 해야함
        Collections.sort(MST, ((o1, o2) -> -(o1.cost - o2.cost)));
        result = cost - MST.get(0).cost;
        System.out.println(result);
    }

    static int find(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;

        if (parents[aRoot] < parents[bRoot]) {
            parents[aRoot] += parents[bRoot]; // b집합을 a에 합치기
            parents[bRoot] = aRoot;
        } else {
            parents[bRoot] += parents[aRoot]; // a집합을 b에 합치기
            parents[aRoot] = bRoot;
        }

        return true;
    }
}