import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        list = new int[N];

        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);

        long count = 0;

        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            int target = -list[i];

            while (left < right) {
                int sum = list[left] + list[right];

                // 찾았을 때
                if (sum == target) {
                    // list[left]와 list[right]가 같은 경우
                    if (list[left] == list[right]) {
                        int numCount = right - left + 1;
                        count += (numCount * (numCount - 1)) / 2; // 조합 수
                        break;
                    }

                    // list[left]와 list[right]가 다른 경우
                    if (list[left] != list[right]) {
                        long leftCount = 1;
                        long rightCount = 1;

                        // 같은 값을 가진 원소의 개수 처리
                        while (left + 1 < right && list[left] == list[left + 1]) {
                            leftCount++;
                            left++;
                        }
                        while (right - 1 > left && list[right] == list[right - 1]) {
                            rightCount++;
                            right--;
                        }

                        // 모든 조합의 수
                        count += leftCount * rightCount;
                        left++;
                        right--;
                    }
                } else if (sum < target) {
                    // 더 작으면 크기 키우기
                    left++;
                } else {
                    // 더 작으면 크기 줄이기
                    right--;
                }
            }
        }

        System.out.println(count);
    }
}
