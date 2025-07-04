import java.io.*;
import java.util.*;

public class Main{
    static int K, N, F;
    static boolean isFind;
    static List<ArrayList<Integer>> friends;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken()); // 소풍 갈 학생 수
        N = Integer.parseInt(st.nextToken()); // 전체 학생 수
        F = Integer.parseInt(st.nextToken()); // 친구 관계 정보 수

        friends = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            friends.add(new ArrayList<>());
        }

        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friends.get(a).add(b);
            friends.get(b).add(a);
        }

        isFind = false;

        for (int i = 1; i < N + 1; i++) {
            if (friends.get(i).size() < K - 1) continue;
            int[] group = new int[K];
            group[0] = i;
            dfs(1, i, group);
        }

        System.out.println(isFind ? sb.toString() : -1);
    }

    static void dfs(int depth, int cur, int[] group) {
        // 기저조건 : 이미 찾았으면 바로 return (어차피 사전순으로 조합하기 때문)
        if (isFind) return;

        // 기저 조건 : K명의 친구를 찾았을 때
        if (depth == K) {
            // 모두가 친구 사이인지 체크
            isFind = true;
            for (int res : group) {
                sb.append(res).append("\n");
            }
            return;
        }

        for (int next = cur + 1; next < N + 1; next++) {
            // 현재까지 뽑은 조합이 가능한지 확인
            if (!check(group, depth, next)) continue; // 불가능하면 넘어감

            group[depth] = next;
            dfs(depth + 1, next, group);
        }
    }

    static boolean check(int[] group, int size, int next) {
        for (int i = 0; i < size; i++) {
            if (!friends.get(group[i]).contains(next)) return false;
            if (!friends.get(next).contains(group[i])) return false;
        }
        return true;
    }
}
