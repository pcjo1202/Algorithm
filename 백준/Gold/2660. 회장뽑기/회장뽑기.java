import java.util.*;
import java.io.*;

public class Main {
    static int N, leaderScore;
    static List<ArrayList<Integer>> members;
    static ArrayList<Integer> candi;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());

        members = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            members.add(i, new ArrayList<>());
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 || b == -1) break;

            members.get(a).add(b);
            members.get(b).add(a);
        }

        leaderScore = Integer.MAX_VALUE; // 회장후보 점수
        candi = new ArrayList<>(); // 회장 후보들

        for (int i = 1; i <= N; i++) { // 1 ~ N 노드 까지
            // 각각의 노드마다 bfs 탐색을 통해 가장 먼 노드까지의 거리를 를 구한다. => 이게 점수
            int score = bfs(i);

            // 점수가 가장 작은 친구를 찾는다.
            if (score < leaderScore) {
                leaderScore = score;
                candi.clear();
                candi.add(i);
            } else if (score == leaderScore) { // 현재 회장 후보 점수와 동점자라면, 추가!
                candi.add(i);
            }
        }

        System.out.printf("%d %d\n", leaderScore, candi.size());
        for (int r : candi) {
            System.out.printf("%d ", r);
        }
    }

    static int bfs(int node) {
        int[] dist = new int[N + 1];// 현재 노드를 기준으로 최단 거리를 구할 배열
        boolean[] visited = new boolean[N + 1];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            // 현재 node에 연결되어있는 노드를 탐색
            for (int next : members.get(cur)) {
                if (!visited[next]) { // 탐색하지 않았다면,
                    visited[next] = true;
                    dist[next] = dist[cur] + 1;
                    queue.offer(next);
                }
            }
        }
        int max = 0;
        // 저장되어 있는 현재 node ~ 다른 노드까지의 거리 중 최대값 찾기
        for (int r : dist) {
            max = Math.max(max, r);
        }
        return max;
    }
}