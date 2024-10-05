import java.io.*;
import java.util.*;

public class Main {
    static int N, M, max;
    static int[] result; // 결과 저장
    static List<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 1 ~ N 번
        M = Integer.parseInt(st.nextToken()); // 신뢰하는 관계의 수

        list = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // a -> b 신뢰
            int b = Integer.parseInt(st.nextToken());

            list.get(b).add(a);
        }

        max = 0;
        result = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            bfs(i);
        }

//        System.out.println(Arrays.toString(result));

        for (int i = 0; i <= N; i++) {
            if (result[i] == max) {
                sb.append(String.format("%d ", i));
            }
        }

        System.out.println(sb);
    }

    static void bfs(int start) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        int count = 1;

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : list.get(cur)) {
                if (visited[next]) continue;

                queue.offer(next);
                visited[next] = true;
                count++;
            }
        }

        result[start] = count;
        max = Math.max(max, count);
    }

}