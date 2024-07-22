import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] set = new int[N + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            set[i + 1] = set[i] + Integer.parseInt(st.nextToken()); // [0, 5, 9, 12, 14, 15]
        }
        /*
        * result = 구간 i번째 수 ~ j번째 수의 합 (i <= j)
        * 구간의 합을 구하는 방법
        * (1) i번 째부터 j번째까지 다 더하기
        * (2) j번 째까지 누적해서 더한 값 - (i - 1) 번 째까지 누적해서 더한 값
        * */

        for (int i = 0; i < M; i++) { // M번 반복

            st = new StringTokenizer(br.readLine());

            int coni = Integer.parseInt(st.nextToken());
            int conj = Integer.parseInt(st.nextToken());

            int result = set[conj] - set[coni - 1];
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}
