import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int MAX = 100_000;
    static List<Integer> result_path;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        result_path = bfs();

        System.out.println(result_path.size() - 1);
        for (int res : result_path) {
            System.out.print(res + " ");
        }
    }

    static List<Integer> bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[MAX + 1];
        int[] prevNumbers = new int[MAX + 1];
        Arrays.fill(prevNumbers, -1);

        queue.offer(N);
        // 시작 노드 추가
        visited[N] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            // 도착하면
            if (cur == K) {
                List<Integer> temp = new ArrayList<>();
                temp.add(cur);
                
                int idx = cur;
                while (prevNumbers[idx] != -1) {
                    temp.add(prevNumbers[idx]);
                    idx = prevNumbers[idx];
                }

                Collections.reverse(temp);
                return temp;
            }

            // 이동
            int[] moves = {cur - 1, cur + 1, cur * 2};
            for (int next : moves) {
                if (next < 0 || next > MAX) continue;
                if (visited[next]) continue;

                queue.offer(next);
                visited[next] = true;
                prevNumbers[next] = cur; // 직전 노드 저장
            }
        }

        return new ArrayList<>();
    }
}
