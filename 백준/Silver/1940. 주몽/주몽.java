import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);
        // 1 2 3 4 5 7

        int left = 0;
        int right = N - 1;
        int cnt = 0;

        while (left < right) {
            int num = list[left] + list[right];

            if (num < M) {
                left++;
            } else if (num == M) {
                cnt++;
                right--;
            } else {
                right--;
            }
        }

        System.out.println(cnt);
    }
}
