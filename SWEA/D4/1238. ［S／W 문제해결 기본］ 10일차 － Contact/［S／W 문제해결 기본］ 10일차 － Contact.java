import java.io.*;
import java.util.*;

public class Solution {
    static int N, start, result;
    //    static int[][] map;
    static HashMap<Integer, ArrayList<Integer>> pair;
    static int[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= 10; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());

//            map = new int[101][101];
            pair = new HashMap<>();
            visited = new int[101];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
//                map[from][to] = 1;
                if (!pair.containsKey(from)) {
                    pair.put(from, new ArrayList<>());
                }
                pair.get(from).add(to);
            }

            Queue<Integer> queue = new ArrayDeque<>();

            queue.offer(start); // 시작 번호 넣기
            visited[start] = 1;

            while (!queue.isEmpty()) {
                int cur = queue.poll(); // 전화걸 친구

                if (!pair.containsKey(cur)) continue; // 다음 연결할 친구가 없다면

                for (int f : pair.get(cur)) { // cur이 전화할 수 있는 친구들 체크
                    if (visited[f] != 0) continue; // 이미 전화를 걸었다면

                    visited[f] = visited[cur] + 1;
                    queue.offer(f);
                }
            }

            int max = 0;
            result = 0;
            for (int i = 1; i < visited.length; i++) {
                if (visited[i] > max) {
                    max = visited[i]; // 깊이가 가장 큰 값
                    result = i;
                } else if (visited[i] == max) {
                    result = Math.max(i, result);
                }
            }

            sb.append(String.format("#%d %d\n", test_case, result));
        }
        System.out.println(sb);
    }
}