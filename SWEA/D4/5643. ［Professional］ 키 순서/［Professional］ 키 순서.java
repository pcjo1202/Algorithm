import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, result, count;
    static int[][] graph, rgraph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            graph = new int[N + 1][N + 1];
            rgraph = new int[N + 1][N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a][b] = 1;
                rgraph[b][a] = 1;
            }

            result = 0;
            // 자신의 키가 몇 번째인지 알 수 있는 학생은 어떻게 구분??
            // N명 중 나보다 작은 친구 수 + 큰 친구 수 == N - 1명이면 내가 몇번째인지 정확히 알 수 있을라나?

            for (int i = 1; i < N + 1; i++) { // 모든 친구를 기준으로 확인
                count = 0;
                findDFS(i, graph, new boolean[N + 1]);
                findDFS(i, rgraph, new boolean[N + 1]);
//                findInMeDFS(i, new boolean[N + 1]);
//                findOutMeDFS(i, new boolean[N + 1]);
                if (count == N - 1) result++;
//                // 1. 나를 가리키고 있는 노드의 수
//                int tempA = findInMe(i);
//
//                // 2. 내가 가리키고 있는 노드의 수
//                int tempB = findOutMe(i);
//
//                // 3. 1번 2번의 합 확인
//                if (tempA + tempB == N - 1) {
//                    result++;
//                }
            }

            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }

    static void findDFS(int node, int[][] matrix, boolean[] visited) {
        visited[node] = true;
        for (int i = 1; i < N + 1; i++) {
            if (!visited[i] && matrix[node][i] != 0) {
                findDFS(i, matrix, visited);
                count++;
            }
        }
    }

    static void findInMeDFS(int node, boolean[] visited) {
        visited[node] = true;
        for (int i = 1; i < N + 1; i++) {
            if (!visited[i] && graph[i][node] != 0) {
                findInMeDFS(i, visited);
                count++;
            }
        }
    }

    static void findOutMeDFS(int node, boolean[] visited) {
        visited[node] = true;
        for (int i = 1; i < N + 1; i++) {
            if (!visited[i] && graph[node][i] != 0) {
                findOutMeDFS(i, visited);
                count++;
            }
        }
    }

    static int findInMe(int node) {
        boolean[] visited = new boolean[N + 1];
        int cnt = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 1; i < N + 1; i++) {
                if (visited[i]) continue;
                if (graph[i][cur] != 0) {
                    queue.offer(i);
                    visited[i] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }


    static int findOutMe(int node) {
        boolean[] visited = new boolean[N + 1];
        int cnt = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 1; i < N + 1; i++) {
                if (visited[i]) continue;
                if (graph[cur][i] != 0) {
                    queue.offer(i);
                    visited[i] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}