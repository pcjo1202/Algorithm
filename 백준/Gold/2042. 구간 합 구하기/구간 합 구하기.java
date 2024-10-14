import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] tree;
    static long[] numbers;

    static List<long[]> problem;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken()); // 수의 개수
        M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
        K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

        numbers = new long[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(br.readLine().trim());
        }

        problem = new ArrayList<>();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine().trim());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            problem.add(new long[]{a, b, c});
        }

        // 1. tree의 높이 구하기
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        // 2. tree의 node의 크기 구하기
        int treeSize = (int) Math.pow(2, height + 1);
//        int treeSize = 1 << (height + 1);
        tree = new long[treeSize];


        // 1. build : 세그먼트 트리 생성
        build(1, 0, (int) (N - 1));

        for (long[] pro : problem) {
            int a = (int) pro[0];
            int b = (int) pro[1];
            long c = pro[2];

            if (a == 1) { // b번째 수를 C로 바꾸기
                update(b - 1, c); // 0-base 이므로 -1
            } else if (a == 2) { // b번째 수 ~ C번째 수까지 구간 합 구하기
                System.out.println(query(b - 1, c - 1)); // 0-base 이므로 -1
            }
        }
    }

    static void build(int node, int start, int end) {
        // 리프 노드를 찾았을 때
        if (start == end) {
            tree[node] = numbers[start];
        } else {
            // 리프 노드가 아닌 노드
            // 왼쪽 자식 노드
            build(node * 2, start, (start + end) / 2);
            // 오른쪽 자식 노드
            build(node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    static long query(int left, long right) {
        return query_tree(1, 0, N - 1, left, right);
    }


    static long query_tree(int node, int start, int end, int left, long right) {
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        long lsum = query_tree(node * 2, start, (start + end) / 2, left, right);
        long rsum = query_tree(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return lsum + rsum;
    }

    static void update_tree(int node, int start, int end, int index, long diff) {
        if (index < start || index > end) return;

        tree[node] += diff;

        if (start != end) {
            update_tree(node * 2, start, (start + end) / 2, index, diff);
            update_tree(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
        }
    }

    static void update(int index, long val) {
        long diff = val - numbers[index];
        numbers[index] = val;
        update_tree(1, 0, N - 1, index, diff);
    }

}