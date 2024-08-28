import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static int[][] pair;
    static int[] parents;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 마을에 사는 사람 수
            M = Integer.parseInt(st.nextToken()); // 관계 수
            result = 0;

            pair = new int[M][2];
            parents = new int[N + 1]; // 1 ~ N+1번까지

            for (int i = 1; i < N + 1; i++) {
                parents[i] = -1;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = pair[i][0] = Integer.parseInt(st.nextToken());
                int b = pair[i][1] = Integer.parseInt(st.nextToken());


                // 집합 관계 설정
                union(a, b);
            }


            // 루트 노드의 개수 파악(서로 알고 있는 관계의 수) : 루트 노드의 수
            for (int i = 1; i < N + 1; i++) {
                if (parents[i] < 0) result++; // 저장되어 있는 값이 음수라면 루트 노드
            }

            sb.append(String.format("#%d %d\n", test_case, result));
        }
        System.out.println(sb);
    }

    static int fine(int a) {
        if (parents[a] < 0) return a; // 자기 자신이라면,
        return parents[a] = fine(parents[a]);
    }

    static void union(int a, int b) {
        int aRoot = fine(a);
        int bRoot = fine(b);

        if (aRoot == bRoot) return;

        if (parents[aRoot] < parents[bRoot]) { // 집합의 크기가 더 크다면,
            parents[aRoot] += parents[bRoot];
            parents[bRoot] = aRoot;
        } else {
            parents[bRoot] += parents[aRoot];
            parents[aRoot] = bRoot;
        }
    }
}