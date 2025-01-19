import java.io.*;
import java.util.*;

public class Main {
    static int N, target;
    static int[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        target = Integer.MAX_VALUE;

        Arrays.sort(numbers); // 오름 차순으로 정렬


        int left = 0;
        int right = N - 1;
        int[] result = new int[2];


        while (left < right) {
            int a = numbers[left];
            int b = numbers[right];

            if (target > Math.abs(a + b)) { // 합이 0에 가까운...
                target = Math.abs(a + b);
                result[0] = a;
                result[1] = b;
            }

            if (a + b < 0) {
                left += 1;
            } else if (a + b > 0) {
                right -= 1;
            } else {

                break;
            }
        }

        System.out.println(result[0] + " " + result[1]);
    }
}