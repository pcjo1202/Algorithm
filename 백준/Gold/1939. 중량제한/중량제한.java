import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] factory;
    static long min, max, result;
    static List<ArrayList<long[]>> bridge;// 연결 섬 A, B 중량제한 C 저장
    static boolean[] visited; // 방문한 곳인지 확인

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bridge = new ArrayList<>(N + 1);

        for (int i = 0; i < N + 1; i++) {
            bridge.add(i, new ArrayList<>());
        }

        min = 0;
        max = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            long C = Long.parseLong(st.nextToken());

            bridge.get(A).add(new long[]{B, C});
            bridge.get(B).add(new long[]{A, C});
            max = Math.max(max, C);
        }

        st = new StringTokenizer(br.readLine());

        int size = st.countTokens();
        factory = new int[size];

        for (int i = 0; i < size; i++) {
            factory[i] = Integer.parseInt(st.nextToken()); // 공장이 있는 섬의 번호
        }

        visited = new boolean[N + 1];

        // ===================여기서부터 로직===================

        // 공장 -> 공장으로 한번의 이동! 옮길 수 있는 물품의 중량의 최대값을 어떻게 구할까용?
        // 영향을 주는 것은! 섬과 섬 사이 다리의 중량 제한!, 다리로 연결되어있는 섬끼리만 이동 가능
        // 최대 중량을 가지고 이동하지만, 감당하지 못하는 다리가 있을 수 있고
        // 적당한 중량을 가지고 이동하면, 여유로운 다리들이 생길 수 있다.
        // 0 ~ 최대 중량 중 사이에서 최대로 가능한 중량을 찾자!!

//        min = 0;
//        max = Math.max(max, C);
        result = 0;
        while (min <= max) {
            long mid = (min + max) / 2;

            // 중량이 이동 가능한 다리인지 체크체크
            int start = factory[0];
            int arrived = factory[1];

            boolean flag = true; // 가능한지 확인하는 flag (가능하면 true, 불가능하면 false)

            // 다음 중량을 확인할 때 초기화
            Arrays.fill(visited, false);
            flag = bfs(start, arrived, mid);

            // 현재 중량 (mid)이 넉넉하다면, 더 늘린다.
            if (flag) {
                min = mid + 1;
                result = mid;
            }
            // 현재 중량 (mid)가 너무 무겁다면, 무게를 줄인다.
            else {
                max = mid - 1;
            }
        }
        System.out.println(result);
    }

    static boolean bfs(int start, int arrived, long weight) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            // 현재 확인할 섬 체크
            int cur = queue.poll();

            if (cur == arrived) return true;

            // 다음에 탐색할 섬 큐에 넣기
            // 현재 섬과 연결되어 있는 섬과의 다리 무게 확인
            for (int i = 0; i < bridge.get(cur).size(); i++) {
                long[] next = bridge.get(cur).get(i);
                long curWeight = next[1];
                int curIsland = (int) next[0];

                if (!visited[curIsland] && curWeight >= weight) { // 현재 다리보다 중량이 안무거우면 가능
                    queue.offer(curIsland);
                    visited[curIsland] = true;
                }
            }
        }
        return false;
    }
}