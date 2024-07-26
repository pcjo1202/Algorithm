import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[8];
        String result = null;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 8; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < 8; i++) {
            if (Math.abs(arr[i] - arr[i - 1]) != 1) {
                result = "mixed";
                break;
            } else if (arr[i] > arr[i - 1]) {
                result = "ascending";
            } else if (arr[i] < arr[i - 1]) {
                result = "descending";
            }
        }

        System.out.println(result);
    }
}