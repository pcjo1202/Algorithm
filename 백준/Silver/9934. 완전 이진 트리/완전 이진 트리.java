import java.util.*;
import java.io.*;

public class Main {
    static int K; // 트리의 깊이
    static int[] arr; // 방문한 빌딩의 순서 ( 1 ~ 2^k)
    static List<ArrayList<Integer>> tree; // 레벨별로 저장할

    // 중위 순회
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        K = Integer.parseInt(br.readLine());

        // 완전 이진 트리의 노드 개수
        int size = (int) (Math.pow(2, K) - 1);
        arr = new int[size];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 트리 입력
        tree = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            tree.add(new ArrayList<>());
        }


        dfs(0, arr.length - 1, 0);

        for (ArrayList<Integer> arr : tree) {
            for (int i : arr) {
                System.out.printf("%d ", i);
            }
            System.out.println();
        }
    }

    static void dfs(int start, int end, int depth) {
        // 기저 조건
        if (depth == K) {
            return;
        }
        int mid = (start + end) / 2;

        tree.get(depth).add(arr[mid]);

        // 탐색 조건
        // 전체 적으로 왼쪽 먼저 넣고, 오른쪽 넣기.
        dfs(start, mid - 1, depth + 1);
        dfs(mid + 1, end, depth + 1);
    }
}