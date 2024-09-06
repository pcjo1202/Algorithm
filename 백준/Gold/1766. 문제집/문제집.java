import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<ArrayList<Integer>> list; // 각 노드의 연결 상태 나타내는 리스트
    static int[] inDegree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        inDegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            inDegree[b]++;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
//        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result.add(cur);

            // cur이 가리키는 인접한 정점의 indegree를 하나씩 삭제해준다.
            for (int next : list.get(cur)) {
                inDegree[next]--;

                // 인접한 정점의 친구들 중에 indegree가 0이된 친구들을 다시 큐에 넣어준다.
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }

        }

        for (int a : result) {
            System.out.printf("%d ", a);
        }

    }
}