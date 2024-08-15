import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] numbers;
    static boolean[] isSelected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[M];
        isSelected = new boolean[N + 1];

        permutation(0);
        System.out.println(sb);
    }

    // 중복 가능
    static void permutation(int depth) {
        if (depth == M) {
            for (int i : numbers) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            numbers[depth] = i;
            permutation(depth + 1);
        }
    }
}