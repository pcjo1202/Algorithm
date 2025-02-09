import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0, sum = 0, length = 0;
        int min = Integer.MAX_VALUE;

        while (right < N) {
            sum += numbers[right];
            right++;

            while (sum >= S) {
                sum -= numbers[left];
                left++;
                min = Math.min(min, right - left + 1);
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }
}