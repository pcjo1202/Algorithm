import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, S, count;
    static boolean[] isSelected;
    static int[] number;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        number = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }
        count = 0;
        comb(0, 0);

        if (S == 0) count--; // 공집합일 때를 빼야함

        System.out.println(count);
    }

    static void comb(int depth, int sum) {
        if (depth == N) {
            if (sum == S) count++;
            return;
        }

        comb(depth + 1, sum);
        comb(depth + 1, sum + number[depth]);
    }
}