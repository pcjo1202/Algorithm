import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] set = new int[N];

        for (int i = 0; i < N; i++) {
            set[i] = Integer.parseInt(br.readLine());
        }

        // set을 오름 차순으로 정렬
        // N 의 최대 입력이 1,000,000 ->  O(N^2) 이하로 가능
        // O(N^2) 이하 정렬 알고리즘 -> 병합 정렬, 힙정렬
        // 하지만 퀵 정렬로 함 해보까...?

        Arrays.sort(set);
        for (int i : set) {
            sb.append(i + "\n");
        }
        System.out.println(sb);
    }
}
