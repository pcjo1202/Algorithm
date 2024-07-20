import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static long A;
    private static long B;

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken()); // 첫번째 숫자의 1의 개수
        B = Long.parseLong(st.nextToken()); // 두번째 숫자의 1의 개수

//        long big = Math.max(A, B);
//        long small = Math.min(A, B);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < gcd(A, B); i++) {
            result.append("1");
        }

        System.out.println(result);
    }
    public static long gcd(long a, long b){
        if (b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }
}
