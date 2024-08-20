import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, B, minTop, result;
    static int[] height;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            height = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }

            visited = new boolean[N];
            minTop = Integer.MAX_VALUE;
            result = 0;
            dfs(0, 0);

            result = minTop - B;

            sb.append(String.format("#%d %d\n", test_case, result));
        }
        System.out.println(sb);
    }

    static void dfs(int idx, int tempTop) {
        // 종료 조건
        if (idx == N) {
            if (tempTop < B) return;
            minTop = Math.min(minTop, tempTop);
            return;
        }

        // 탐색 조건
        dfs(idx + 1, tempTop);
        dfs(idx + 1, tempTop + height[idx]);
    }
}