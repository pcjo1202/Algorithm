import java.io.*;
import java.util.*;

public class Main {
    static long T;
    static int N, M;
    static int[] list_A, list_B;
    static List<Long> sumA, sumB;

    public static void main(String[] args) throws Exception {
        input();

        sumA = new ArrayList<>(); // 연속된 부분합 리스트
        sumB = new ArrayList<>();
        // 1. 부분합 구하기
        createPreSum(sumA, N, list_A);
        createPreSum(sumB, M, list_B);

        // 2. 이분 탐색 -> sumA의 요소 + sumB의 요소 = target
        Collections.sort(sumB);

        long count = 0;
        for (long num : sumA) {
            long target = T - num;

            // upper - lower => target의 개수
            int upper_cnt = upperBound(sumB, target);
            int lower_cnt = lowerBound(sumB, target);

            count += upper_cnt - lower_cnt;
        }

        System.out.println(count);
    }

    static int lowerBound(List<Long> list, long target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (target > list.get(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    static int upperBound(List<Long> list, long target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (target >= list.get(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    static void createPreSum(List<Long> preSum, int size, int[] arr) {
        for (int i = 1; i < size + 1; i++) {
            long sum = 0;
            for (int j = i; j < size + 1; j++) {
                sum += arr[j];
                preSum.add(sum);
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        list_A = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            list_A[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        list_B = new int[M + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < M + 1; i++) {
            list_B[i] = Integer.parseInt(st.nextToken());
        }
    }

}
