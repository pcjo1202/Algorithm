import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 987654321;
    static int V, E;
    static int[][] distance;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        distance = new int[V + 1][V + 1]; // node - node 거리를 저장

        for (int i = 1; i < V + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                if (i != j) {
                    distance[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            distance[a][b] = c;
        }

        // 플로이드 워셜
        for (int k = 1; k < V + 1; k++) {
            for (int i = 1; i < V + 1; i++) {
                for (int j = 1; j < V + 1; j++) {
                    if (i == j) continue;
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }

        result = INF;
        for (int i = 1; i < V + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                if (i != j) {
                    if (distance[i][j] != INF && distance[j][i] != INF) {
                        result = Math.min(result, distance[i][j] + distance[j][i]);
                    }
                }
            }
        }

        // 결과 출력
        if (result == INF) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
}