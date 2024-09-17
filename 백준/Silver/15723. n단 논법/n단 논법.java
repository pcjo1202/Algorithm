import java.io.*;
import java.util.*;
/*
 * 주어진는 전제는 그래프로 표현 가능
 * 간선으로 저장해볼끼?
 * a -> b -> c -> d
 * 그래프를 그리고,
 * 주어진 결론이 맞는지 아닌지 판단
 * a -> d , a -> c, d ->a
 */

public class Main {
    static int N, M;
    static List<Edge> edges;

    static class Edge {
        char from, to;

        public Edge(char from, char to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine()); // 전제 개수
        edges = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (str == null) continue;
            char a = str.charAt(0);
            char b = str.charAt(str.length() - 1);
            edges.add(new Edge(a, b));
        }

        M = Integer.parseInt(br.readLine()); // 결론 개수

//        for (Edge arr : edges) {
//            System.out.println(arr.from + ", " + arr.to);
//        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (str == null) continue;
            char a = str.charAt(0);
            char b = str.charAt(str.length() - 1);

            if (check(a, b)) { // 그래프가 맞는지 확인하는 함수
                sb.append("T\n");
            } else {
                sb.append("F\n");
            }
        }
        System.out.println(sb);
    }

    // 결론의 내용이 맞는지 확인하는 함수
    static boolean check(char from, char to) {
        // from에서 연결된 정점 중 to가 있는지 확인, 있으면 True, 없으면 false
        int start = 0;
        Queue<Edge> queue = new ArrayDeque<>();

        // 초기 저장한 간선들 중 from으로 시작하는 친구들 저장
        for (int i = 0; i < edges.size(); i++) {
            Edge curEdge = edges.get(i);
            if (curEdge.from == from) {
                queue.offer(curEdge); // From으로 시작하는 모든 edge 큐에 넣음
            }
        }

        while (!queue.isEmpty()) {
            Edge curEdge = queue.poll();

            // curEdge의 to가 원하는 To인지 확인 맞으면
            // - 맞으면 바로 true 반환
            // - 틀리면 curEdge의 to로 시작하는 연결 간선 큐에 넣기.
            if (curEdge.to == to) {
                return true;
            }

            for (int i = 0; i < edges.size(); i++) {
                Edge next = edges.get(i);
                if (next.from == curEdge.to) {
                    queue.offer(next); // From으로 시작하는 모든 edge 큐에 넣음
                }
            }

        }
        // 여기까지 오면 없는 것이므로 false
        return false;
    }
}