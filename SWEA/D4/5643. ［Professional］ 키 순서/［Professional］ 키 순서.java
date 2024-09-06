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

            for (int i = 1; i < N + 1; i++) {
                graph[i][0] = -1; // 탐색되지 않은 학생을 나타냄 (후에 탐색이 완료되면 자신보다 큰 학생 수 저장"
            }

            result = 0;
            // 자신의 키가 몇 번째인지 알 수 있는 학생은 어떻게 구분??
            // N명 중 나보다 작은 친구 수 + 큰 친구 수 == N - 1명이면 내가 몇번째인지 정확히 알 수 있을라나?

            for (int i = 1; i < N + 1; i++) { // 모든 친구를 기준으로 확인

                // 1. dfs
//                count = 0;
//                findDFS(i, graph, new boolean[N + 1]);
//                findDFS(i, rgraph, new boolean[N + 1]);
////                findInMeDFS(i, new boolean[N + 1]);
////                findOutMeDFS(i, new boolean[N + 1]);
//                if (count == N - 1) result++;
//
                // 2. bfs
//                if (findInMe(i) + findOutMe(i) == N - 1) {
//                    result++;
//                }

                // 3. 최적화 (메모이제이션하기)
                if (graph[i][0] != -1) continue;
                findMemo(i);
            }

            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    graph[0][j] += graph[i][j];
                }
            }

            for (int i = 1; i < N + 1; i++) {
                if (graph[i][0] + graph[0][i] == N - 1) {
                    result++;
                }
            }

            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }

    static void findMemo(int node) {  // 자신보다 큰 학생 따라 탐색
        for (int i = 1; i < N + 1; i++) {
            if (graph[node][i] == 0) continue;
            if (graph[i][0] == -1) { // 탐색되지 않은 학생이므로 탐색
                findMemo(i);
            }

            // 나보다 키가 학생이 탐색을 완료한 상태
            // i 보다 키가 큰 학생이 있다면, 그 학생들의정보를 node에 반양해줌 (간접관게를 직접관계로 경로 압축)

            if (graph[i][0] > 0) {
                for (int j = 1; j < N + 1; j++) {
                    if (graph[i][j] != 0) graph[node][j] = 1;
                }
            }
        }

        graph[node][0] = 0; // 추기값이 -1 으로 초기ㅗ하

        for (int j = 1; j < N + 1; j++) {
            graph[node][0] += graph[node][j];
        }
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