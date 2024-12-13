import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int G;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());

        int left = 1; // 기억하고 있는 몸무게
        int right = 2; // 현재 몸무게
        List<Integer> result = new ArrayList<>();

        while (right > left) {
            int temp = (right * right) - (left * left);

            if (temp == G) {
                // G를 찾았을 때
                result.add(right);
                left++; // 다음 값을 찾기 위해 left를 증가
            } else if (temp < G) {
                // G보다 값이 작을 때 -> 크기를 키워야 하므로, right 증가
                right++;
            } else {
                // G보다 클 때, -> 크기를 줄여야 하므로 left 증가
                left++;
            }
        }

        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int res : result) {
                System.out.println(res);
            }
        }

    }
}