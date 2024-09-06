import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Edge> edges;
    static Node[] nodes;
    static double cost = 0;
    static int[] parents;


    static class Node {
        int id;
        double x, y;

        public Node(int id, double x, double y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class Edge implements Comparable<Edge> {
        int u, v;
        double w;

        @Override
        public String toString() {
            return "Edge{" +
                    "u=" + u +
                    ", v=" + v +
                    ", w=" + w +
                    '}';
        }

        public Edge(int u, int v, double w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());


        edges = new ArrayList<>();
        nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double a = Double.parseDouble(st.nextToken());
            double b = Double.parseDouble(st.nextToken());
            nodes[i] = new Node(i, a, b);
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                edges.add(new Edge(i, j, calDist(nodes[i], nodes[j])));
            }
        }

        parents = new int[N];
        Arrays.fill(parents, -1);

        Collections.sort(edges);

        int cnt = 0;
        for (int i = 0; i < edges.size(); i++) {
            if (union(edges.get(i).u, edges.get(i).v)) {
                cost += edges.get(i).w;
                cnt++;
                if (cnt == N - 1) break;
            }
        }
        System.out.printf("%.2f", cost);
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
            parents[a] += parents[b];
            parents[b] = a;
        } else {
            parents[b] += parents[a];
            parents[a] = b;
        }
        return true;
    }


    static double calDist(Node a, Node b) {
        double tempX = Math.pow(Math.abs(a.x - b.x), 2);
        double tempY = Math.pow(Math.abs(a.y - b.y), 2);
        return Math.sqrt(tempX + tempY);
    }
}