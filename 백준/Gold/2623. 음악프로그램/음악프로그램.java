import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] inDegree;
    static List<ArrayList<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 가수의 수
        M = Integer.parseInt(st.nextToken()); // 보조 PD 수

        inDegree = new int[N + 1];
        graph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");

            int size = temp.length;

            for (int j = 2; j < size; j++) {
                // a -> b
                int a = Integer.parseInt(temp[j - 1]);
                int b = Integer.parseInt(temp[j]);

                graph.get(a).add(b);
                inDegree[b]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();


        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                result.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                inDegree[next]--;

                if (inDegree[next] == 0) {
                    queue.offer(next);
                    result.add(next);
                }
            }
        }

        if (result.size() == N) {
            for (int res : result) {
                System.out.println(res);
            }
        } else {
            System.out.println(0);
        }
    }
}
