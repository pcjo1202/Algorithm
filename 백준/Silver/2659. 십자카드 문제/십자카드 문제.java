import java.io.*;
import java.util.*;

public class Main {
    static int[] numbers;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        numbers = new int[4];

        for (int i = 0; i < 4; i++) {
            int a = Integer.parseInt(st.nextToken());
            numbers[i] = a;
        }


        // 십자 모양의 시계수 구하기
        int target = getClockNumber(numbers);


        // 답 : 찾은 시계수 - 1111 + 1 - 시계수가 될 수 없는 수
//        Set<Integer> set = new TreeSet<>(); // 시계수가 될 수 있는 수 저장
        int count = 0;
        for (int i = 1111; i <= target; i++) {
            char[] temp = String.valueOf(i).toCharArray();
            int[] res = new int[4];

            for (int j = 0; j < 4; j++) {
                res[j] = temp[j] - '0';
            }

            int num = getClockNumber(res);

            if (num == i) { // 시계 수라면
                count++;
//                set.add(i);
            }
        }

//        System.out.println(set);
//
//        int sub = target - 1111 + 1; // 12
//        result = sub - count;
        System.out.println(count);
//        System.out.println(set.size());
    }

    static int getClockNumber(int[] numbers) {
        int target = Integer.MAX_VALUE;

        // 2 1 1 2
        for (int i = 0; i < 4; i++) {
            // 한 칸씩 회전
            int temp = numbers[0];
            numbers[0] = numbers[1];
            numbers[1] = numbers[2];
            numbers[2] = numbers[3];
            numbers[3] = temp;

            int num = numbers[0] * 1000 + numbers[1] * 100 + numbers[2] * 10 + numbers[3];

            target = Math.min(target, num);
        }

        return target;
    }
}