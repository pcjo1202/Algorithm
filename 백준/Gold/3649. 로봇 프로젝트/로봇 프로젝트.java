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
            X = Integer.parseInt(input); // 구멍의 너비 (cm) ->
            N = Integer.parseInt(br.readLine()); // 레고 조각의 수

            lego = new int[N];

            for (int i = 0; i < N; i++) {
                lego[i] = Integer.parseInt(br.readLine());
            }

            result = new int[2];
            X = X * (int) 1e7; // 나노미터로 단위 변환

            Arrays.sort(lego); // 이분 탐색을 위한 정렬

            boolean flag = false;

            for (int i = 0; i < N; i++) {
                int target = X - lego[i];

                int low = i + 1; // i 이후부터 탐색
                int high = N - 1;

                while (low <= high) {
                    int mid = (low + high) / 2;

                    if (lego[mid] < target) {
                        low = mid + 1;
                    } else if (lego[mid] > target) {
                        high = mid - 1;
                    } else {
                        result[0] = lego[i];
                        result[1] = lego[mid];
                        flag = true;
                        break;
                    }
                }
                if (flag) break; // 결과를 찾으면 탈출
            }

            if (!flag) {
                System.out.println("danger");
            } else {
                System.out.printf("yes %d %d\n", result[0], result[1]);
            }
        }
    }
}