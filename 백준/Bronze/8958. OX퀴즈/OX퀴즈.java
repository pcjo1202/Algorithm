import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String[] strs = new String[T];

        for (int i = 0; i < T; i++) {
            strs[i] = br.readLine();
        }

        for (int i = 0; i < T; i++) {
            int size = strs[i].length();
            String str = strs[i];
            int temp = 0;
            int sum = 0;
            for (int j = 0; j < size; j++) {
                if (j > 0 && str.charAt(j) == 'O' && str.charAt(j - 1) == 'O') {
                    temp++;
                    sum += temp;
                } else if (str.charAt(j) == 'O') {
                    sum++;
                    temp = 1;
                }
            }
            System.out.println(sum);
        }
    }
}