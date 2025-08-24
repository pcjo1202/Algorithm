import java.io.*;
import java.util.*;

public class Main {
    static int N, MAX;
    static int[] list, preSum;
    static int[][] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 객차의 수\
        list = new int[N + 1];
        // 누적합 구하기
        preSum = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            list[i] = Integer.parseInt(st.nextToken());
            preSum[i] = preSum[i - 1] + list[i];
        }

        MAX = Integer.parseInt(br.readLine()); // 최대로 끌 수 있는 객차의 수

        // 누적합을 가지고 MAX의 사이즈 만큼 얼마나 손님을 저장할 수 있는지 저장
        // i 소형기관차 -> j 번째 객차까지 사용해서 태울 수 있는 손님 최대 수
        cache = new int[3 + 1][N + 1];

        for (int i = 1; i <= 3; i++) {
            for (int j = i * MAX; j <= N; j++) {
                cache[i][j] = Math.max(cache[i][j - 1],
                        cache[i - 1][j - MAX] + (preSum[j] - preSum[j - MAX]));
            }
        }

        System.out.println(cache[3][N]);
    }

}
