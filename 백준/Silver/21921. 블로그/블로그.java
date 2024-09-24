import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] visitors = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            visitors[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int i = 0; i < X; i++) {
            sum += visitors[i];
        }


        int max = sum;
        int answer = 1;
        for (int i = 0; i < N - X; i++) { // 슬라이딩 윈도우

            //한칸 씩 이동
            sum += visitors[i+X];
            sum -= visitors[i];

            if (sum == max) { // 최다 방문자수가 유지될 경우 answer++
                answer++;
            }

            if (sum > max) { // 최다 방문자수가 갱신되었을 경우
                answer = 1;
                max = sum;
            }

        }
        if (max == 0) { // 최대 방문자 수가 0이라면 SAD 출력
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(answer);
        }
    }
}