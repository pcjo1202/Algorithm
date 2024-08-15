import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[] isSelected;
    static int[] numbers;
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
            if (depth > 0 && i <= numbers[depth - 1]) continue; // 넣은 숫자가 이전에 뽑은 숫자보다 작다면, 건너뛰기
            isSelected[i] = true;
            numbers[depth] = i;

            permutation(depth + 1);
            isSelected[i] = false;
        }
    }
}