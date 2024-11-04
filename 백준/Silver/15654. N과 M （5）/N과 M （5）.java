import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] numbers;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[N + 1];

        Arrays.sort(numbers);
        permutation(0, new int[M]);

        System.out.println(sb);
    }

    static void permutation(int depth, int[] result) {
        // 기저조건
        if (depth == M) {
            for (int res : result) {
                sb.append(res).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 탐색 조건
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            result[depth] = numbers[i];
            permutation(depth + 1, result);
            visited[i] = false;
        }
    }
}