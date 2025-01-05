import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int N, result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minus = new PriorityQueue<>(((o1, o2) -> o1 - o2));
        PriorityQueue<Integer> plus = new PriorityQueue<>(((o1, o2) -> o2 - o1));

        // 조합으로 한다면... 2^50 * ... 너무 많네

        // 묶는다 -> 곱한다. -> 더한다. 큰 수를 구한다.
        // 두 수를 곱해서 가장 큰 수를 만들어야 한다.
        // 정렬 후 가장 큰 수를 두개 씩 묶고 더한다.
        // 곱해서 0이되거나, 음수가 되면 곱하지 않는다.
        // 음수 음수이면 곱한다.

        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());
            if (temp > 0) {
                plus.offer(temp);
            } else {
                minus.offer(temp);
            }
        }

        result = 0;

        // 1, 0, -1 예외 처리 어떻게?

        // 음수랑 양수를 따로 처리
        // 양수 일 때
        while (!plus.isEmpty()) {
            int a = plus.poll();
            if (!plus.isEmpty()) {
                int b = plus.poll();

                if (a != 1 && b != 1) {
                    result += a * b;
                } else {
                    result += a + b;
                }
            } else {
                result += a;
            }
        }

        while (!minus.isEmpty()) {
            int a = minus.poll();
            if (!minus.isEmpty()) {
                int b = minus.poll();
                result += (a * b);
            } else {
                result += a;
            }
        }


        System.out.println(result);
    }
}