import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static int[][] graph;
    static int[] nums;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 구역의 개수

        nums = new int[N + 1]; // 구역의 인구

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        graph = new int[N + 1][N + 1]; // 각 구역과 인접한 구역의 정보

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size; j++) {
                int a = Integer.parseInt(st.nextToken());
                graph[i][a] = 1;
                graph[a][i] = 1;
            }
        }

        result = Integer.MAX_VALUE; // 두 선거구의 인구수 차이

        // 가능한 모든 구역의 조합을 찾고, 구역의 인구수 차이 구하기
        visited = new boolean[N + 1];
        comb(1);

        // 답 도출
        if (result == Integer.MAX_VALUE) {
            result = -1;
        }

        System.out.println(result);
    }

    // r: 뽑을 개수, arr: 뽑은 조합을 저장할 배열
    static void comb(int depth) {
        if (depth == N) {
            List<Integer> aList = new ArrayList<>(); // 노드 번호 저장
            List<Integer> bList = new ArrayList<>();

            for (int i = 1; i < N + 1; i++) {
                if (visited[i]) aList.add(i);
                else bList.add(i);
            }

            if (aList.isEmpty() || bList.isEmpty()) return;

            if (check(aList) && check(bList)) { // 두 선거구가 모두 인접해있다면
                // 두 선거구의 인구 수 계산
                int temp = calNum(aList) - calNum(bList);
                result = Math.min(result, Math.abs(temp));
            }
            return;
        }

        visited[depth] = true;
        comb(depth + 1);

        visited[depth] = false;
        comb(depth + 1);
    }

    // 연결 여부를 체크해줄 함수
    static boolean check(List<Integer> arr) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(arr.get(0));
        visited[arr.get(0)] = true;

        int cnt = 0;
        while (!queue.isEmpty()) {
            cnt++;
            int cur = queue.poll();

            for (int next : arr) {
                if (visited[next]) continue;

                if (graph[cur][next] == 1) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }

        return cnt == arr.size();
    }

    static int calNum(List<Integer> arr) {
        int sum = 0;
        for (int num : arr) {
            sum += nums[num];
        }
        return sum;
    }

}