import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] list;
    static int[] plan, parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine()); // 도시의 수
        M = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시들의 수

        list = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                list[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        plan = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        // 유니온 파인드로 여행 계획에 들어가는 도시가 연결되어있는지 체크
        parents = new int[N + 1];
        Arrays.fill(parents, -1);

        // 유니온 파인드 연산
        for (int i = 1; i < N + 1; i++) {
            for (int j = i; j < N + 1; j++) {
                if (list[i][j] == 0) continue; // 연결 안되어 있는거 패스
                union(i, j);
            }
        }

        // 연결 여부 확인
        int root = find(plan[0]);

        for (int p : plan) {
            if (root != find(p)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    static int find(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot != bRoot) {
            parents[bRoot] = aRoot;
        }
    }
}
