import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] numbers = new int[3];

        while (true) {
            st = new StringTokenizer(br.readLine());
            numbers[0] = Integer.parseInt(st.nextToken());
            numbers[1] = Integer.parseInt(st.nextToken());
            numbers[2] = Integer.parseInt(st.nextToken());

            if (numbers[0] == 0) {
                break;
            }

            Arrays.sort(numbers);

            if (numbers[0] * numbers[0] + numbers[1] * numbers[1] == numbers[2] * numbers[2]) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
    }
}