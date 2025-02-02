import java.util.*;
import java.io.*;

public class Main {
    static int N, MaxNum;
    static int[] numbers, result;
    static boolean[] isCard;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        MaxNum = 0; // 가장 큰 숫자 (요만큼만 소수를 찾으면 된다?)
        numbers = new int[N]; // 플레이어의 수

        int SIZE = 1_000_001;
        isCard = new boolean[SIZE]; // 뽑힌 카드인지 확인

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            isCard[numbers[i]] = true;

            MaxNum = Math.max(MaxNum, numbers[i]);
        }

        result = new int[SIZE];

        // 소수 구하기
        for (int i = 0; i < N; i++) {
            int player1 = numbers[i];
            for (int j = player1 * 2; j < MaxNum + 1; j += player1) {
                if (isCard[j]) { // 뽑힌 카드인데, 나누어 떨어지는 수라면
                    result[player1]++;
                    result[j]--;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.print(result[numbers[i]] + " ");
        }
    }
}