import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.*;

/*
 * 물을 대는 법
 * 1. 직접 논에 우물 파는 것
 * 2. 다른 논에서 끌어오기
 *
 * */

public class Main {
    static int N, result;
    static int[] arr;
    static List<ArrayList<Edge>> edges;

    static class Edge implements Comparable<Edge> {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return compare(this.w, o.w);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "v=" + v +
                    ", w=" + w +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = parseInt(br.readLine()); // 우물의 수
        arr = new int[N + 1]; // 우물을 팔때 드는 비용

        for (int i = 1; i < N + 1; i++) {
            arr[i] = parseInt(br.readLine());
        }

        edges = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                int w = parseInt(st.nextToken());
                if (i == j) continue;
                edges.get(i).add(new Edge(j, w)); // 간선 만들기
            }
        }

//        for (ArrayList arr : edges) {
//            System.out.println(arr);
//        }

        // mst 구하기 -> 프림! (어느 우물에서부터 시작하느냐 정해야 하므로)

        // 가상 정점(0)과 모든 논을 연결하는 간선을 추가 (우물을 파는 비용)
        for (int i = 1; i < N + 1; i++) {
            edges.get(0).add(new Edge(i, arr[i]));
            edges.get(i).add(new Edge(0, arr[i]));
        }

//        result = MAX_VALUE;
//
//        int start = 0;
//        int temp = arr[1];
//
//        for (int i = 2; i < N + 1; i++) {
//            if (temp > arr[i]) {
//                temp = arr[i];
//                start = i;
//            }
//        }

        result = prim(0);

        System.out.println(result);
    }

    static int prim(int startV) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];

        pq.offer(new Edge(startV, 0)); // 시작 정점 기준 가중치

        int cost = 0;

//        System.out.println("start : " + start);
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
//            System.out.println("cur : " + cur);
            if (visited[cur.v]) continue; // cur 정점이 방문했던 곳인지 확인
            visited[cur.v] = true;

            cost += cur.w;
//            System.out.println(cur + " 이후 cost : " + cost);

            for (Edge next : edges.get(cur.v)) {
                if (visited[next.v]) continue; // 방문한 정점이면 패스
//                System.out.println("next : " + next);
                pq.offer(next);
            }
        }
        return cost;
    }
}