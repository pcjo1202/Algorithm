import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<ArrayList<Integer>> graph;
    private static int[] dist; // 최단 거리를 기록할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()); // 도시 개수 (노드)
        int M = Integer.parseInt(st.nextToken()); // 도로 개수 (간선)
        int K = Integer.parseInt(st.nextToken()); // 정답 조건이 되는 K
        int X = Integer.parseInt(st.nextToken()); // 출발 도시 번호

        dist = new int[N + 1]; //
        Arrays.fill(dist, Integer.MAX_VALUE); // 무한대 값으로 초기화

        graph = new ArrayList<>(N + 1);

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 출발
            int b = Integer.parseInt(st.nextToken()); // 도착

            graph.get(a).add(b);
        }

        dijkstra(X);


        // X에서 출발해서, 거리가 K인 도시들 추출
        for (int i = 1; i < N + 1; i++) {
            if (dist[i] == K) {
                sb.append(i + "\n");
            }
        }
        if (sb.length() == 0) { // 찾은 K 도시들이 없다면,
            System.out.println(-1);
        } else {
            System.out.println(sb);
        }
    }

    public static void dijkstra(int x) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0])); // 다음에 방문할 노드를 선택하는 데 사용
        pq.offer(new int[]{0, x}); // 초기값 넣기
        dist[x] = 0; // 처음 방문할 때는 0

        while (!pq.isEmpty()) { // 큐가 빌때까지 반복
            int[] cur = pq.poll();
            int curDist = cur[0]; // 현재 거리
            int node = cur[1]; // 현재 node

            if (dist[node] < curDist) { // 최소거리가 저장되어있는 값보다 크면 바로 패스
                continue;
            }

            // 큐에서 꺼낸 친구와 연결된 모든 노드들을 확인
            for (int next : graph.get(node)) {
                //거 리가 더 짧으면 거리 리스트를 업데이트, 큐에 추가
                if (curDist + 1 < dist[next]) {
                    dist[next] = curDist + 1;
                    pq.offer(new int[]{curDist + 1, next});
                }
            }
        }
    }
}
