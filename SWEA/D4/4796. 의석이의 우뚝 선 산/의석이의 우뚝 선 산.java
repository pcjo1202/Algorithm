import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    static int N, count;
    static int[] height;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            height = new int[N];

            for (int i = 0; i < N; i++) {
                height[i] = sc.nextInt();
            }

            int up = 0;
            int down = 0;
            count = 0;
            for (int i = 1; i < N; i++) {
                if (height[i - 1] < height[i]) {
                    if (down > 0) {
                        count += down * up;
                        up = 0;
                        down = 0;
                    }
                    up++;
                } else {
                    down++;
                }
            }
            count += up * down;

            sb.append("#").append(test_case).append(" ").append(count).append("\n");
        }
        System.out.println(sb);
    }
}