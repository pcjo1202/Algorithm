import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int T, N;
    static String[] numbers;
    static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            numbers = new String[N];

            for (int i = 0; i < N; i++) {
                numbers[i] = br.readLine();
            }

            // 1. 정렬
            Arrays.sort(numbers);

            // 2. cur과 next 비교
            flag = true;

            for (int i = 0; i < N - 1; i++) {
                String curStr = numbers[i];
                String nextSub = numbers[i + 1];

                if (nextSub.startsWith(curStr)) { // 같다면, false
                    flag = false;
                    break;
                }
            }

            System.out.printf("%s\n", flag ? "YES" : "NO");
        }
    }
}