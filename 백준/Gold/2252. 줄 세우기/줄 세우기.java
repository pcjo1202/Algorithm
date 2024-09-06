import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<ArrayList<Integer>> list;
    static int[] inDegree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 주어지는 관계 수

        list = new ArrayList<>();
        inDegree = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            list.add(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            inDegree[b]++;
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

            // 인접한 정점의 간선 제거
            for (int next : list.get(cur)) {
                inDegree[next]--;
            }

            // 인접한 정점의 간선을 제거후, 인접한 정점 중 진입 정점이 0인 정점이 있다면 queue에 넣기
            for (int next : list.get(cur)) {
                if (inDegree[next] == 0) {
                    queue.offer(next);
                    result.add(next);
                }
            }
        }

        for (int a : result) {
            System.out.printf("%d ", a);
        }
    }
}