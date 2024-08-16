import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] numbers;
    static boolean[] isSelect;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 1 ~ N 까지 자연수
        M = Integer.parseInt(st.nextToken()); // M 개를 고르기

        numbers = new int[M];
        isSelect = new boolean[N + 1];
        /*
         * 중복해서 뽑아도 됨
         * 비내림차순 (같은 것을 중복해서 포함해서 내림차순)
         * */

        permutation(0, 1);

    }

    static void permutation(int depth, int start) {
        if (depth == M) {
            for (int i : numbers) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= N; i++) {
//            if (isSelect[i] && numbers[depth - 1] != i) continue;
//            if (depth > 0 && numbers[depth - 1] > i) continue;
            numbers[depth] = i;
//            isSelect[i] = true;
            permutation(depth + 1, i);
//            isSelect[i] = false;
        }

    }
}