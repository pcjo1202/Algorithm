import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_cace = 0; test_cace < T; test_cace++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][2];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            // 숫자가 높을 수록 등수가 높은 것!!
            // 정렬을 서류 기준으로 하고, 면접을 비교하면서 , 면접이 다른 사람들의 등수보다 숫자가 높지 않다면 Count ++

            Arrays.sort(arr, (o1, o2) -> Integer.compare(o1[0], o2[0]));
            // Collections sort의 시간 복잡도 NlogN

            int count = 1; // 첫번째 (1등은 무조건 합격)
            int max = arr[0][1]; // 통과를 위한 최소한의 등수 저장

            for (int i = 1; i < N; i++) {
                if (arr[i][1] < max) {
                    count++;
                    max = arr[i][1];
                }
            }

            System.out.println(count);
        }

    }
}
