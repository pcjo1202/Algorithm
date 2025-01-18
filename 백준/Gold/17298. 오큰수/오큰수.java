import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] numbers, result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        result = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> stack = new ArrayDeque<>();
        Arrays.fill(result, -1);

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && numbers[stack.peekLast()] < numbers[i]) {
                int id = stack.pollLast(); // 결과에 넣을 과거 idx
                result[id] = numbers[i];
            }
            stack.offerLast(i); // 스택에 넣기
        }

        for (int res : result) {
            sb.append(res).append(" ");
        }

        // 정답 출력
        System.out.print(sb);
    }
}