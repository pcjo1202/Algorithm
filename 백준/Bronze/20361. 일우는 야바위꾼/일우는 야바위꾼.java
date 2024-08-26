import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int X = Integer.parseInt(str[1]);
        int K = Integer.parseInt(str[2]);

        for (int i = 0; i < K; i++) {
            str = br.readLine().split(" ");

            int A = Integer.parseInt(str[0]);
            int B = Integer.parseInt(str[1]);
            
            if (A == X) // A가 간식 컵이었을 경우 B가 간식 컵이 됨
                X = B;
            else if (B == X) // B가 간식 컵이었을 경우 A가 간식 컵이 됨
                X = A;
        }
        
        System.out.println(X);
    }
}