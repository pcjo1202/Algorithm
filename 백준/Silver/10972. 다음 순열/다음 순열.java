import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
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

        boolean flag = nextPermutation(numbers);

        if (flag) {
            for (int r : numbers) {
                System.out.printf("%d ", r);
            }
        } else {
            System.out.println(-1);
        }

    }

    static boolean nextPermutation(int[] nums) { // Ex) {1,3,5,4,2} 다음 순열 찾기
        // 0. 뒤에서부터 탐색할 인덱스
        int i = nums.length - 2;

        // 1. 뒤의 숫자가 더 큰 인덱스를 찾는다.
        // 탐색 조건 : 0보다 크거나 같고, 뒤의 숫자가 앞의 숫자보다 작을 때
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--; // i = 3
        }

        // 마지막 숫자 (이미 배열이 내림차순으로 정렬되어있음)
        if (i < 0) {
            return false;
        }

        // 2. i 보다 뒤에 있는 친구들 중에서 i의 값보다 큰 첫번째 인덱스 j 찾기
        int j = nums.length - 1; // 뒤에서부터 찾기

        while (nums[j] <= nums[i]) { // i의 값보다 j의 값이 더 클 때 반복
            j--; // -> j= 3
        }

        // 3. i의 위치 값과, j위치의 값을 바꿈
        swap(nums, i, j);

        // 4. 바꾸고난 후 i 보다 오른쪽에 있는 수들을 모두 내림차순으로 정렬
        reverse(nums, i + 1, nums.length - 1); // 바꿀 배열, 오름차순으로 정렬할 범위의 시작점, 마치는 점
        return true;
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}