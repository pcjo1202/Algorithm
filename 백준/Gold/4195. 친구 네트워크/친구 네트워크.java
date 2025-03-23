import java.io.*;
import java.util.*;

public class Main {
    static int T, F;
    //    static int[] parents;
    static Map<Integer, Integer> parents;
    static Map<String, Integer> list; // 각각의 이름에 대한 Index 값을 저장


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            F = Integer.parseInt(br.readLine()); // 친구 관계 수
            parents = new HashMap<>();
            list = new HashMap<>();

            int idx = 0;
            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String u = st.nextToken();
                String v = st.nextToken();

                if (!list.containsKey(u)) {
                    // index 부여
                    list.put(u, idx);
                    parents.put(idx, -1);
                    idx++;
                }
                if (!list.containsKey(v)) {
                    // index 부여
                    list.put(v, idx);
                    parents.put(idx, -1);
                    idx++;
                }

                // 두 사람 Union
                union(list.get(u), list.get(v));

                // 둘 중 한 사람 fInd로 집합의 크기를 찾음
                System.out.println(-parents.get(find(list.get(u))));
            }
        }
    }

    static int find(int a) {
        if (parents.get(a) < 0) return a;
        parents.put(a, find(parents.get(a)));
        return parents.get(a);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;

        if (parents.get(aRoot) < parents.get(bRoot)) {
            parents.put(aRoot, parents.get(aRoot) + parents.get(bRoot));
            parents.put(bRoot, aRoot);
        } else {
            parents.put(bRoot, parents.get(bRoot) + parents.get(aRoot));
            parents.put(aRoot, bRoot);
        }

        return true;
    }
}
