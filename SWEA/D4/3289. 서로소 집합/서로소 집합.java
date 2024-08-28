import java.io.*;
import java.util.*;

public class Solution {
    static int N, M;
    static int[][] arr;

    static class UnionFind {
        int[] parents; // 루트 노드

        public UnionFind(int size) {
            parents = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = -1; // make set i : 자신의 부모를 자신으로하여 대표자가 되도록 설정
            }
        }

        int findSet(int a) { // 부모 노드 찾기
            if (parents[a] < 0) return a; // 음수이면

            return parents[a] = findSet(parents[a]);
        }

        void union(int a, int b) {
            int aRoot = findSet(a);
            int bRoot = findSet(b);

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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            sb.append(String.format("#%d ", test_case));

            arr = new int[M][3];
            UnionFind unionFind = new UnionFind(N + 1);

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken()); // 연산 내용
                arr[i][1] = Integer.parseInt(st.nextToken()); // a 포함하는 집합
                arr[i][2] = Integer.parseInt(st.nextToken()); // b 포함하는 집합
            }

            for (int i = 0; i < M; i++) {
                int op = arr[i][0];
                int a = arr[i][1];
                int b = arr[i][2];

                if (op == 1) { // 두 원소가 같은 집합에 포함되어 있는지 확인
                    sb.append(unionFind.findSet(a) == unionFind.findSet(b) ? 1 : 0);
                } else if (op == 0) { // 합집합
                    unionFind.union(a, b);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}