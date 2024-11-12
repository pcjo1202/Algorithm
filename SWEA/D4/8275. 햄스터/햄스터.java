import java.io.*;
import java.util.*;

public class Solution {
    static int N, X, M, max;
    static int[] answer;
    static List<Node> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 우리 개수
            X = Integer.parseInt(st.nextToken()); // 우리 최대 마리 수
            M = Integer.parseInt(st.nextToken()); // 기록 수

            list = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); // from
                int b = Integer.parseInt(st.nextToken()); // to
                int c = Integer.parseInt(st.nextToken()); // c
                list.add(new Node(a, b, c));
            }

            max = -1;
            answer = new int[N + 1]; // 우리
            permutation(1, 0, new int[N + 1]);
            sb.append(String.format("#%d ", tc));
            if (max == -1) {
                sb.append(max);
            } else {
                for (int i = 1; i < N + 1; i++) {
                    sb.append(answer[i]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static boolean check(int[] result) {
        for (Node node : list) {
            int tempSum = 0;
            for (int i = node.from; i <= node.to; i++) {
                tempSum += result[i];
            }
            if (tempSum != node.count) return false;
        }

        return true;
    }

    static void permutation(int depth, int sum, int[] result) {
        // 기저
        if (depth == N + 1) {
            // 조건에 만족하면
            if (check(result)) {
                if (max < sum) {
                    max = sum;
                    answer = result.clone();
                }
            }
            return;
        }

        for (int i = 0; i < X + 1; i++) {
            result[depth] = i;
            permutation(depth + 1, sum + i, result);
        }
    }

    static class Node {
        int from, to, count;

        public Node(int from, int to, int count) {
            this.from = from;
            this.to = to;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "from=" + from +
                    ", to=" + to +
                    ", count=" + count +
                    '}';
        }
    }
}