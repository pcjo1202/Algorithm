import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int result = 0;

        while (!pq.isEmpty()) {
            int a = pq.poll(); // 처음 하나 꺼내기
            if (pq.isEmpty()) { // 비어있다면 종료 조건
                break;
            }
            int b = pq.poll();

            result += a + b;
            pq.offer(a + b);
        }

        System.out.println(result);

    }
}