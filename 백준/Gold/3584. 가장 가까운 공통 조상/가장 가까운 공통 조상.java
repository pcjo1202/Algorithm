import java.io.*;
import java.util.*;

public class Main {
    static int T, N;
    static int[] parents;
    static int[] problem;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine()); // 노드의 수 N

            parents = new int[N + 1]; // [자식] = 부모
            problem = new int[2];

            // 간선 입력 받기
            for (int i = 0; i < N; i++) {

                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 마지막 입력은 찾는 노드
                if (i == N - 1) {
                    problem[0] = a;
                    problem[1] = b;
                    continue;
                }

                parents[b] = a;
            }

            System.out.println(find(problem[0], problem[1]));
        }
    }

    // 가장 가까운 공통 조상 찾기
    static int find(int a, int b) {
        boolean[] isMyRoot = new boolean[N + 1];
        int root = 0;
        // a 체크
        while (a != 0) {
            isMyRoot[a] = true; // 부모가 있는지 확인한 Node
            a = parents[a]; // 부모
        }

        // b 체크 : 이미 위에 a에서 체크된 부모를 찾는거지요
        while (b != 0) {
            if (isMyRoot[b]) {
                root = b;
                break;
            }
            b = parents[b];
        }

        return root;
    }
}
