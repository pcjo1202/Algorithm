import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static int N, sum;
    static ArrayList<Edge> edges;
    static int[] parents;

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sum = 0;

        edges = new ArrayList<>();
        parents = new int[N];
        Arrays.fill(parents, -1);

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char cur = str.charAt(j);
                int cost = 0;
                if ('a' <= cur && cur <= 'z') {
                    cost = cur - 'a' + 1;
                } else if ('A' <= cur && cur <= 'Z') {
                    cost = cur - '0' + 10;
                } else if (cur == '0') {
                    cost = 0;
                }

                if (i != j && cost != 0) {
                    edges.add(new Edge(i, j, cost));
                }
                sum += cost;
            }
        }

        // 다솜이가 기부할 수 있는 랜선의 길이의 최댓값 -> 다솜이 집을 최소로 연결
        // MST 구하기
        Collections.sort(edges, (o1, o2) -> o1.cost - o2.cost);
        int count = 0;
        int sumCost = 0;

        for (int i = 0; i < edges.size(); i++) {
            Edge cur = edges.get(i);

            if (union(cur.from, cur.to)) {
                sumCost += cur.cost;
                count++;
            }
            if (count == N - 1) break;
        }
        System.out.println(count == N - 1 ? sum - sumCost : -1);
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
            parents[aRoot] += parents[bRoot];
            parents[bRoot] = aRoot;
        } else {
            parents[bRoot] += parents[aRoot];
            parents[aRoot] = bRoot;
        }
        return true;
    }
}