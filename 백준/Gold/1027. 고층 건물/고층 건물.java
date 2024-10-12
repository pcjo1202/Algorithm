import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static int[] height;

    static class Pair {
        int i, h;

        public Pair(int i, int h) {
            this.i = i;
            this.h = h;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        height = new int[N + 1];
        result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        // 가장 많은 고층 빌딩이 보이는 빌딩을 구하기.
        // 거기서 보이는 빌딩의 수 구하기

        for (int i = 1; i < N + 1; i++) {
            Pair cur = new Pair(i, height[i]); // 확인할 기준이 되는 현재 빌딩
            result = Math.max(result, check(cur));
        }

        // 정답
        System.out.println(result);
    }

    // 고층빌딩이 보이는 수 구하기
    static int check(Pair cur) {
        int count = 0;

        double leftTemp = 1e9; // 기울기가 음수일 수, 양수 일 수 있어서. 최대값을 양수로 잡음

        // 자기보다 왼쪽을 볼 때 : i-1 > i-2 => 왼쪽으로 갈 수록 기울기 감소
        for (int i = cur.i - 1; i >= 1; i--) {
            double grad = getGradient(cur, new Pair(i, height[i]));
            if (leftTemp > grad) { // 조건에 맞다면
                count++; // 보이는 고층 건물 수 증가
                leftTemp = grad; // 다음 건물을 탐색하기 위해 값 변경
            }
        }

        double rightTemp = -1e9; // 기울기가 음수일 수 도 , 양수 일 수 있어서. 최소값을 음수로 잡음

        // 자신보다 오른쪽을 볼 때 : i + 1 < i + 2 => 갈 수록 기울기가 커야
        for (int i = cur.i + 1; i < N + 1; i++) {
            double grad = getGradient(cur, new Pair(i, height[i]));
            if (rightTemp < grad) {
                count++;
                rightTemp = grad;
            }
        }
//        System.out.println(cur.i + " : " + count);
        return count;
    }

    // 기울기 구하기
    static double getGradient(Pair cur, Pair next) {
        int dx = next.i - cur.i;
        int dy = next.h - cur.h;

        return (double) dy / dx;
    }


}