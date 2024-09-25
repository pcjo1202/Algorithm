import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, X, max;
    static int[] dates, pSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N일
        X = Integer.parseInt(st.nextToken()); // 범위

        dates = new int[N]; // 0-base
        max = 0; // 가장 큰 방문자 수
        pSum = new int[N + 1]; // 누적합 저장

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dates[i] = Integer.parseInt(st.nextToken());
        }

        // 누적 합
        for (int i = 1; i < N + 1; i++) {
            pSum[i] = pSum[i - 1] + dates[i - 1];
        }
        // [0, 1, 5, 7, 12, 13]

        // X 크기의 구간 합 중 가장 큰 수 구하기

        int count = 0;

        for (int start = 0; start < N - X + 1; start++) {
            int end = start + X;

            int temp = pSum[end] - pSum[start];
            if (temp > max) {
                max = temp;
                count = 1;
            } else if (temp == max) {
                count++;
            }
        }
        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(count);
        }
    }
}