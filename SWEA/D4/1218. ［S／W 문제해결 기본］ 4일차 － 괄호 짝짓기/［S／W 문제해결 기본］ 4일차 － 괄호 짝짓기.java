import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N;
    static String str = null;
    static int result;
    static final HashMap<Character, Character> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        map.put('<', '>');

        int T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            result = 1;
            str = br.readLine();

            ArrayDeque<Character> stack = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                char current = str.charAt(i);
                if (map.containsKey(current)) { // current가 여는 것이라면,
                    stack.push(str.charAt(i));
                } else { // current가 닫는 것이라면,
                    if (stack.isEmpty() || current != map.get(stack.pop())) {// stack 이 비어있거나, 최상위 친구와 같지 않다면 바로 종료
                        result = 0;
                        break;
                    }
                }
            }

            if (!stack.isEmpty()) {
                result = 0;
            }


            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}