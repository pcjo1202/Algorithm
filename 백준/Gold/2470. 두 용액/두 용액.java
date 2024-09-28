import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        // Numbers에서 두개를 뽑아 차이의 절대값이 가장 작은 수 찾기
        // 완탐 불가

        int low = 0; // 탐색 포인터
        int hight = N - 1; // 탐색 포인터
        int min = Integer.MAX_VALUE; // 현재까지 가장 답에 가까운 차이
        int resLow = 0; // 답
        int resHight = 0; // 답

        while (low < hight) {
            int temp = numbers[low] + numbers[hight];

            if (Math.abs(temp) < min) {
                min = Math.abs(temp);
                resLow = low;
                resHight = hight;
            }

            if (temp > 0) {
                hight--;
            } else if (temp < 0) {
                low++;
            } else {
                break;
            }

        }

        System.out.printf("%d %d", numbers[resLow], numbers[resHight]);
    }
}