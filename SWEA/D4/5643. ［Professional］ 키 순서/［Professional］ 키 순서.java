import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, result;
    static int INF = 999999; // 적절한 큰 값으로 설정
    static int[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            graph = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                Arrays.fill(graph[i], INF);
                graph[i][i] = 0;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a][b] = 1; // a < b 관계를 나타냄
            }

            result = 0;

            // 플로이드-워셜 알고리즘
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (graph[i][j] > graph[i][k] + graph[k][j]) {
                            graph[i][j] = graph[i][k] + graph[k][j];
                        }
                    }
                }
            }

            // 키 순서를 알 수 있는 학생 확인
            for (int i = 1; i <= N; i++) {
                boolean flag = true;
                for (int j = 1; j <= N; j++) {
                    if (graph[i][j] == INF && graph[j][i] == INF) {
                        flag = false;
                        break;
                    }
                }
                if (flag) result++;
            }

            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }
}