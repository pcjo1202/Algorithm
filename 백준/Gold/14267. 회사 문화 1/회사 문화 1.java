import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    static List<ArrayList<Integer>> tree;
    static int[] good;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        tree = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            tree.add(new ArrayList<>());
        }

        // idx 직속 상사를 가리킴 [직속상사] -> 칭찬할 사람
        for (int i = 1; i < N + 1; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a == -1) continue;
            tree.get(a).add(i);
        }

        good = new int[N + 1]; // [칭찬을 처음 받은 사람] = 수치
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            good[a] += b; // 한 사람이 여러번 칭찬 받을 경우
        }


        dfs(1);


        // 결과 출력
        for (int i = 1; i < good.length; i++) {
            System.out.printf("%d ", good[i]);
        }
    }

    static void dfs(int n) {
        for (int child : tree.get(n)) {
            good[child] += good[n];  // 현재 노드의 칭찬 점수를 자식 노드에 전파
            dfs(child);  // 자식 노드에 대해 재귀 호출
        }
    }
}