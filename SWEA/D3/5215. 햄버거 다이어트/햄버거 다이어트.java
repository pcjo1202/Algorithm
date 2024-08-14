import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int[][] itemList;
    static int N, L, max = 0;

    static void combination(int depth,  int Lsum, int scoreSum){
        if(Lsum > L) return;
        // arr : r개를 뽑은 요소를 저장
        if(depth == N){
            max = Math.max(max, scoreSum);
            return;
        }
        // 200 + 300 + 400 = 900
        // 현재 까지 뽑은 재료의 칼로리 합이 L을 넘기면 return

        combination(depth + 1, Lsum, scoreSum);
        combination(depth + 1, Lsum + itemList[depth][1], scoreSum + itemList[depth][0]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            max = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            itemList = new int[N][2];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                itemList[i][0] = Integer.parseInt(st.nextToken());
                itemList[i][1] = Integer.parseInt(st.nextToken());
            }

                combination(0, 0, 0);

            sb.append("#").append(test_case).append(" ").append(max).append('\n');
        }
        System.out.println(sb.toString());
    }
}