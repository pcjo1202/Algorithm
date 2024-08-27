import java.io.*;
import java.sql.Array;
import java.util.*;

public class Solution {
    static int V, E;
    //    static int[][] board; // 인접 행렬
    static List<ArrayList<Integer>> list; // 인접 리스트
    static int[] inDegree; // 진입 차수 계산할 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= 10; test_case++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            inDegree = new int[V + 1];

            for (int i = 0; i < V + 1; i++) {
                list.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.get(a).add(b);
                // 1. 그래프 각 노드들의 진입 차수 계산
                inDegree[b]++;
            }

            // 2. 진입 차수 0 Queue 에 넣기
            Queue<Integer> queue = new ArrayDeque<>();
            Queue<Integer> result = new ArrayDeque<>();
            for (int i = 1; i <= V; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                    result.offer(i);
                }
            }

            // 3. Queue에서 정점 하나를 꺼내서 자신과 인접한 정점의 간선을 제거
            while (!queue.isEmpty()) {
                int cur = queue.poll();

                // 인접한 정점의 간선 제거 (실제 list 제거 아니고, indegree 배열에서 하나 제거
                for (Integer c : list.get(cur)) {
                    inDegree[c]--;
                }

                for (Integer c : list.get(cur)) {
                    // 4. 간선 제거 후 진입 차수가 0이 된 정점을 Queue에 넣는다.
                    if (inDegree[c] == 0) {
                        queue.offer(c);
                        result.offer(c);
                    }
                }
            }


            sb.append(String.format("#%d ", test_case));
            while (!result.isEmpty()) {
                int cur = result.poll();
                sb.append(String.format("%d ", cur));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}