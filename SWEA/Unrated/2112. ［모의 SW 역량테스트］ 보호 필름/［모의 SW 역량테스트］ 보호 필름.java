import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int D, W, K, result;
    static int [][] map;
    static int[] inject; // 주입 상태 확인하는 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine(), " ");
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[D][W];
            inject = new int[D];

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            result = K;

            subset(0 ,0);
            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
    static void subset(int depth, int cnt){ // cnt : 주입 횟 수
        if(cnt >= result) return;
        // 기저 조건
        if(depth == D){
            // 성능 검사를 통과하는지 아닌지에 대한 함수를 작성
            if(check()){// 성능 검사를 통과 했다면
                result = Math.min(result, cnt);
            }
            return;
        }
        // 주입 X
        inject[depth] = -1;
        subset(depth + 1, cnt);
        // A:0 주입
        inject[depth] = 0;
        subset(depth + 1, cnt + 1);
        // B:1 주입
        inject[depth] = 1;
        subset(depth + 1, cnt + 1);
    }

    static boolean check(){
        if(K ==1) return true; // K가 1이면 바로 통과
        // 주입 정보 (inject 배열과 각 행을 확인)
        columns: for (int j = 0; j < W ; j++) { // ---> 행을 반복해서 체크!
            // 각각의 열을 inject과 비교하여 바꿔주고, 연속되는 k개가 있는지 확인
            // 연속되는 k개가 없다면, 바로 return false
            int tempCnt = 1; // 연속되는 숫자 확인
            for (int i = 1; i < D; i++) {
                // cur: 현재 상태 투입을 안했다면(-1) -> 그대로 / 투입 했다면 투입한 내용으로 변환
                int cur = inject[i - 1] == -1 ? map[i - 1][j] : inject[i - 1]; // 0 or 1
                // 다음 지점
                int next = inject[i] == -1 ? map[i][j] : inject[i]; // 0 or 1

                // cur 와 next가 같다면, tempCnt++
                if(cur == next){
                    tempCnt++;
                } else{
                    // 같지 않다면 tempCnt 초기화
                    tempCnt = 1;
                }
                // tempCnt가 조건 K를 넘는다면 바로 다음 행으로 넘어가자!
                if(tempCnt >= K) continue columns;
            }
            if(tempCnt < K) return false; // 만족하지 못한다면,
        }
        return true; // 아무 문제 없이 모두 돌았다면 모든 행에 대하여 성능 평가가 ok된 주입 집합
    }
}