import java.util.Scanner;

public class Main {

    static long[] dp = new long[10000001];
    public static void main(String[] args) {
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 9;
        for (int i = 5; i < 1000001; i++) {
            dp[i] = (i-1) * (dp[i-1] + dp[i-2]) % 1000000000;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println(dp[sc.nextInt()]);
    }
}