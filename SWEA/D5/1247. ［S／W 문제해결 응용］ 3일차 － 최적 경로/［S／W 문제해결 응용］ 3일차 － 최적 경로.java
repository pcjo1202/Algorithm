import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, result;
    static Pair[] list;
    static int[] consumer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            list = new Pair[13]; // 0은 회사, 1은 집

            // 고객의 위치 초기화
            for (int i = 0; i < N + 2; i++) {
                list[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            consumer = new int[N];
            for (int i = 0; i < N; i++) {
                consumer[i] = i + 2; // {2, 3, 4, 5, 6}
            }

            result = Integer.MAX_VALUE;

            // 모든 순열 뽑기
            do {
                int temp = 0;
                // 회사 -> 첫번째 고객 + 두번째 고객 ~ .. + 마지막 고객 + 집 까지의 거리의 합
                Pair office = list[0];
                Pair home = list[1];

                // 회사 ~ 첫번째 고객 list[0] ,2, 3, 4,
                temp += calDirection(office, list[consumer[0]]);

                for (int i = 1; i < consumer.length; i++) {
                    // 첫번째 고객 ~ N -1번째 고객까지의 거리
                    // 0 ~ 1, 1 ~ 2 , 2 ~ 3, 3 ~ 4
                    temp += calDirection(list[consumer[i - 1]], list[consumer[i]]);
                    if (temp >= result) break;
                }
                if (temp >= result) continue;
                // 마지막 고객list[1] ~ 집
                temp += calDirection(list[consumer[consumer.length - 1]], home);
                if (temp >= result) continue;
                
                result = Math.min(result, temp);

            } while (nextPermutation(consumer));

            sb.append(String.format("#%d %d\n", test_case, result));
        }
        System.out.println(sb);
    }

    static int calDirection(Pair from, Pair to) {
        return Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
    }

    // N개로 나열된 순열의 다음 순열이 가능한지, 구하는 함수
    public static boolean nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
            reverse(nums, i + 1, nums.length - 1);
            return true;  // 다음 순열이 성공적으로 생성됨
        } else {
            return false;  // 더 이상 다음 순열이 없음 (마지막 순열)
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}