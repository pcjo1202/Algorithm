import java.io.*;
import java.util.*;

public class Main {
    static int X, N;
    static int[] lego;
    static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = br.readLine()) != null) { // EOF 처리
            try {
                X = Integer.parseInt(input); // 구멍의 너비 (cm) 입력 받기
                N = Integer.parseInt(br.readLine()); // 레고 조각의 수

                lego = new int[N];

                for (int i = 0; i < N; i++) {
                    lego[i] = Integer.parseInt(br.readLine());
                }
                result = new int[2];

                X = X * (int) 1e7; // 나노미터로 단위 변환

                // lego 중 2개를 선택해, 합이 X가 있는 것을 찾는다.
                Arrays.sort(lego); // 이분 탐색을 위한 정렬

                boolean flag = false;
                for (int i = 0; i < N; i++) {
                    int target = X - lego[i]; // lego[i]와 합하여 X를 만드는 친구 찾기

                    int idx = Arrays.binarySearch(lego, i + 1, N, target); // i 이후부터 이분 탐색

                    if (idx > 0) { // 찾음
                        result[0] = lego[i];
                        result[1] = lego[idx];
                        flag = true;
                        break; // |l1 - l2| 의 크기가 가장 큰 것은 l1의 크기가 가장 작을 때
                    }
                }

                // 결과 출력
                if (!flag) {
                    System.out.println("danger");
                } else {
                    System.out.printf("yes %d %d\n", result[0], result[1]);
                }
            } catch (NumberFormatException e) {
                // 입력이 잘못되었을 때의 예외 처리
                System.out.println("잘못된 입력 형식입니다.");
            }
        }
    }
}