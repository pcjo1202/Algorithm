import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] inDegree;
    static List<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 과목 수
        M = Integer.parseInt(st.nextToken()); // 선수 조건

        inDegree = new int[N + 1];
        list = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            inDegree[b]++;
        }

        int[] result = new int[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                result[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : list.get(cur)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                    result[next] = result[cur] + 1;
                }
            }

        }

        // 정답 도출
        for (int i = 1; i < N + 1; i++) {
            System.out.printf("%d ", result[i]);
        }
    }
}