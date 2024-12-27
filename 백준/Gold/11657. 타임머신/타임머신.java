import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static long[] distance;
    static Line[] list;

    static class Line {
        int from, to, time;

        public Line(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 도시의 개수
        M = Integer.parseInt(st.nextToken()); // 버스 노선의 개수

        list = new Line[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[i] = new Line(a, b, c);
        }

        distance = new long[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        if (bellmanFord(1)) {
            // 가장 빠른 시간 순서대로 출력
            for (int i = 2; i < N + 1; i++) {
                sb.append(distance[i] == Integer.MAX_VALUE ? -1 : distance[i])
                        .append("\n");

                // 출력량 제한: 주기적으로 출력
                if (sb.length() > 10_000) {
                    System.out.print(sb);
                    sb.setLength(0);
                }
            }
        } else {
            // 음수 사이클이 발생되었을 때
            sb.append(-1);
        }
        System.out.println(sb);
    }

    static boolean bellmanFord(int start) {
        distance[start] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int from = list[j].from;
                int to = list[j].to;
                int time = list[j].time;

                if (distance[from] == Integer.MAX_VALUE) continue;

                if (distance[to] > distance[from] + time) {
                    distance[to] = distance[from] + time;

                    if (i == N - 1) { // 음수 순환 체크
                        return false;
                    }
                }
            }
        }

        return true;
    }
}