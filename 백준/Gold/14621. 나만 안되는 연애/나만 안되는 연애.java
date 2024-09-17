import java.io.*;
import java.util.*;

public class Main {
    static int N, M, cost;
    static int[] parents;
    static char[] colleges;
    static List<Edge> edges;

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "u=" + u +
                    ", v=" + v +
                    ", w=" + w +
                    '}';
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 학교 수
        M = Integer.parseInt(st.nextToken()); // 학교를 연결하는 거리 수

        colleges = new char[N + 1]; // 남초, 여초 학교 정보 저장

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            colleges[i] = st.nextToken().charAt(0);
        }

        edges = new ArrayList<>(); // 학교간의 가중치 확인

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, c));
        }

        // MST 만들기
        parents = new int[N + 1];

        Arrays.fill(parents, -1);

        Collections.sort(edges); // 가중치가 적은 순서대로 정렬

        cost = 0;
        int cnt = 0;

        for (int i = 0; i < M; i++) {
            Edge cur = edges.get(i);
            int u = cur.u;
            int v = cur.v;

            // 남초, 여초일때만 MST 구성
            if (colleges[u] != colleges[v]) {
                // 그래프 구성
                if (union(cur.u, cur.v)) {
                    cost += cur.w;
                    cnt++;
                    if (cnt == N - 1) {
                        break;
                    }
                }
            }
        }

        // 답
        if (cnt != N - 1) {
            cost = -1;
        }
        System.out.println(cost);
    }

    static int find(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;

        if (parents[a] < parents[b]) {
            parents[b] += parents[a];
            parents[a] = b;
        } else {
            parents[a] += parents[b];
            parents[b] = a;
        }

        return true;
    }
}