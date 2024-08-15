import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;

public class Main {
    static ArrayDeque<Character> stack;
    static HashMap<Character, Character> map = new HashMap<>();
    ;

    static {
        map.put('(', ')');
        map.put('[', ']');
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            stack = new ArrayDeque<>();
            String str = br.readLine();
            boolean check = true;
            // 종료 조건
            if (str.length() == 1 && str.charAt(0) == '.') {
                break;
            }
            for (int i = 0; i < str.length(); i++) {
                char current = str.charAt(i);

                // 여는 괄호를 만나면 stack에 넣기
                if (map.containsKey(current)) {
                    stack.push(current);
                } else if (map.containsValue(current)) { // 닫는 괄호를 만나면, stack의 가장 위에있는 것을 꺼내고 비교
                    if (stack.isEmpty() || current != map.get(stack.pop())) {
                        check = false;
                        break;
                    }
                }
            }
            if (!stack.isEmpty()) { // 다 돌았는데 stack에 여는 괄호가 남아있다면,
                check = false;
            }
            System.out.println(check ? "yes" : "no");
        }
    }
}