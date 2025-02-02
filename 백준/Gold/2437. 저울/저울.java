import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] weights;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weights = new int[N];

        // 주어진 추들로 측정할 수 없는 양의 정수 무게 중 최솟값
        // -> 가능한 무게

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weights);
        int result = 1;
        for (int i = 0; i < N; i++) {
            if (result < weights[i]) {
                break;
            }
            result += weights[i];
        }

        System.out.println(result);
    }
}