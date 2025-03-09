import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] list;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        list = new int[N];
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(br.readLine());
            pq.offer(list[i]);
        }

        int result = 0;

        if (N == 1) {
            System.out.println(0);
            return;
        }

        while (!pq.isEmpty()) {
            int a = pq.poll();
            int b = 0;
            if (!pq.isEmpty()) {
                b = pq.poll();
            }

            result += a + b;
            if (pq.isEmpty()) {
                break;
            } else {
                pq.offer(a + b);
            }
        }

        System.out.println(result);

    }
}
