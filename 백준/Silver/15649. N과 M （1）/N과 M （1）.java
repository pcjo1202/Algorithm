import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] numbers;
    static boolean[] isSelected;
    static StringBuilder sb = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isSelected = new boolean[N + 1];
        numbers = new int[M];
        permutation(0);
        System.out.println(sb);
    }

    static void permutation(int depth) {
        if (depth == M) {
            for (int i : numbers) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (isSelected[i]) continue;

            numbers[depth] = i;
            isSelected[i] = true;
            permutation(depth + 1);
            isSelected[i] = false;
        }

    }
}