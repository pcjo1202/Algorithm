import java.io.*;
import java.util.*;

public class Main {
    static int A, B, N, M;

    static class Node {
        int num, count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken()); // 스카이 콩콩
        B = Integer.parseInt(st.nextToken()); // 스카이 콩콩
        N = Integer.parseInt(st.nextToken()); // 동규
        M = Integer.parseInt(st.nextToken()); // 주미


        System.out.println(bfs());
    }

    /*
    1. +1
    2. -1
    3. +A
    4. +B
    5. -A
    6. -B
    7. cur * A
    8. cur * B
    * */

    static int bfs() {
        if (N == M) return 0;
        Queue<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[100_001]; // 방문 처리 + count

        int[] process = new int[]{1, -1, A, -A, B, -B, A, B};

        // 초기화
        for (int d = 0; d < 8; d++) {
            int curProcess = process[d];
            int next;
            if (d >= 6) {
                next = N * curProcess;
            } else {
                next = N + curProcess;
            }

            // 최대 크기 벗어나면 X
            if (next > 100_000 || next < 0) continue;

            queue.offer(new Node(next, 1));
            visited[next] = true;
        }

        while (!queue.isEmpty()) {
            Node curStat = queue.poll();


            // 탐색 조건
            for (int d = 0; d < 8; d++) {
                int curProcess = process[d];
                int next;
                if (d >= 6) {
                    next = curStat.num * curProcess;
                } else {
                    next = curStat.num + curProcess;
                }

                // 예외 조건
                if (next > 100_000 || next < 0) continue;
                if (visited[next]) continue;

                // 기저 조건 : 도착했을 때
                if (next == M) {
                    return curStat.count + 1;
                }

                queue.offer(new Node(next, curStat.count + 1));
                visited[next] = true;
            }
        }

        return -1;
    }
}
