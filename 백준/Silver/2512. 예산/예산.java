import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * M의 범위가 N 보다 크고, 100억보다 작은범위...
 */

public class Main {
    static int N, M, result;
    static int[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        result = 0;

        Arrays.sort(numbers);

        int start = 0;
        int end = numbers[N - 1];

        while (start <= end) {
            int mid = start + (end - start) / 2;

            int total = 0;
            for (int num : numbers) {
                if (num > mid) {
                    total += mid;
                } else {
                    total += num;
                }
            }

            if (total <= M) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }
}