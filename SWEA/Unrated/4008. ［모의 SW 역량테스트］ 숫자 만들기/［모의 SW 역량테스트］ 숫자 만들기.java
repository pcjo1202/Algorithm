import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N, result, min, max;
    static boolean[] isSelected;
    static List<Character> operator;
    static int[] numbers;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <=T ; test_case++) {
            N = Integer.parseInt(br.readLine());
            result = 0;
            min = Integer.MAX_VALUE;
            max = -Integer.MAX_VALUE;
            isSelected = new boolean[N];

            operator = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            for(char oper : new char[]{'+', '-', '*', '/'}){
                int n = Integer.parseInt(st.nextToken());
                for (int j = 0; j < n; j++) {
                    operator.add(oper);
                }
            }

            numbers = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            subSet(0, new char[N -1]);
            result = max - min;
            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    // 가능한 연산자 부분집합을 뽑는다.
    static void subSet(int depth, char[] op){
        if(depth == N -1){ // 연산자의 개수는 N - 1개
//            System.out.println(Arrays.toString(op));
            calculator(op);
            return;
        }

        char temp =' ';
        for (int i = 0; i < N - 1; i++) {
            if(isSelected[i]) continue;
            if(temp == operator.get(i)) continue;
            temp = operator.get(i);
            op[depth] = operator.get(i);
            isSelected[i] = true;
            subSet(depth + 1, op);
            isSelected[i] = false;
        }
    }

    // 연산자 조합을 계산한다.
    static void calculator(char[] op){
        int res = numbers[0];

        for (int i = 0; i < N -1 ; i++) {
            if(op[i] == '+'){
                res += numbers[i + 1];
            }else if(op[i] == '-'){
                res -= numbers[i + 1];
            }else if(op[i] == '*'){
                res *= numbers[i + 1];
            }else if(op[i] == '/'){
                res /= numbers[i + 1];
            }
        }
        max = Math.max(res, max);
        min = Math.min(res, min);
    }
}