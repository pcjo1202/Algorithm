import java.io.*;
import java.util.*;

public class Solution {
    static int N, K;
    static long result;
    static StringBuilder line;
    static List<String> memo; // 생성한 수 중복을 제거해서 저장할 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // N 개의 숫자
            K = Integer.parseInt(st.nextToken()); // 정답을 위한 K번째 수

            memo = new ArrayList<>();

            line = new StringBuilder(br.readLine());

            solve();


            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }

    static void solve() {
        List<Integer> passList = new ArrayList<>();

        for (int n = 0; n <= N / 4; n++) {
            for (int i = 0; i < N; i += N / 4) {
                String sub = line.substring(i, i + N / 4);
                int pass = Integer.parseInt(sub, 16);
                if (!passList.contains(pass)) {
                    passList.add(pass);
                }
            }

            char last = line.charAt(N - 1);
            line.deleteCharAt(N - 1);
            line.insert(0, last);
        }
        Collections.sort(passList, Comparator.reverseOrder());

        result = passList.get(K - 1);
    }

}