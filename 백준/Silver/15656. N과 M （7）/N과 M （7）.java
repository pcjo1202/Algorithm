import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] numbers;
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
            result[depth] = numbers[i];
            permutation(depth + 1, result);
        }
    }
}