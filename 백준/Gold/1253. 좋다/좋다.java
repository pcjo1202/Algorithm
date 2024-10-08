import java.io.*;
import java.util.*;


public class Main {
    static int N, result;
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
        result = 0;

        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;
            int cur = numbers[i];

            while (left < right) {
                if (left == i) { // 자기 자신을 건너뜀
                    left++;
                    continue;
                }
                if (right == i) { // 자기 자신을 건너뜀
                    right--;
                    continue;
                }
                int sum = numbers[left] + numbers[right];

                if (sum == cur) { // 두 수의 합이 cur과 같으면 "GOOD" 수
                    result++;
                    break;
                } else if (sum < cur) { // 합이 작으면 left 포인터 이동
                    left++;
                } else { // 합이 크면 right 포인터 이동
                    right--;
                }
            }
        }

//        for (int i = 0; i < N; i++) { // 가장 작은 수 부터 "GOOD"이 가능한지 확인
//            int cur = numbers[i];
//
//            for (int j = 0; j < N; j++) { // 가장 작은 수 ~ cur 범위 안에서 탐색
//                int first = numbers[j];
//
//                int target = cur - first; // first와 합해서 cur을 만들 수 있는 숫자 찾기
//
//                if (target != cur && target != first && cur != first) {
//                    int idx = Arrays.binarySearch(numbers, target);
//
//                    if (idx >= 0 && idx != i && idx != j) { // 찾았을 때, 결과 1 증가
//                        result++;
//                        break;
//                    }
//                }
//            }
//        }


        System.out.println(result);
    }
}