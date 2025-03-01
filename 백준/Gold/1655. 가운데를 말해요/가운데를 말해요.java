import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static PriorityQueue<Integer> left, right;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        left = new PriorityQueue<>(Comparator.reverseOrder()); // 중간보다 작은쪽
        right = new PriorityQueue<>(); // 중간보다 큰쪽

        for (int i = 1; i < N + 1; i++) {
            int num = Integer.parseInt(br.readLine());

            left.offer(num);
            right.offer(left.poll()); // left 중 가장 큰 값을 right에 담기

            if (left.size() < right.size()) { // 한쪽으로 치우지는 걸 방지
                left.offer(right.poll());
            }

//            System.out.println("left" + left);
//            System.out.println("right" + right);
            sb.append(left.peek()).append("\n"); // 왼쪽에서 가장 큰 값을 꺼냄 (중간 값)
        }

        System.out.println(sb);
    }
}