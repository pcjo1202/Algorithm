import java.io.*;
import java.util.Stack;

public class Main {
    private static int N;
    private static int [] set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        set = new int[N];
        for (int i = 0; i < N; i++) {
            set[i] = Integer.parseInt(br.readLine()); // [4, 3, 6, 8, 7, 5, 2, 1]
        }
        /*
         * 1 ~ N 의 수를 주어진 수열로 만들기 위해 stack을 사용
         * 최대 입력이 100,000 이므로 시간 복잡도는 O(n^2) 이하로 사용?
         *
         * 조건
         * - 스택에 넣는 순서는 반드시 오름차순
         * - 스택 자료구조 이해
         *
         * **불가능의 조건**
         * - 주어진 set의 순서를 만들 수 없을 때
         * -> [ 1, 2 ,3 ,4, 5] 처럼 넣고 바로 비어있게 될 때
         *
         * 아이디어
         * - [1 ~ N]의 수를 stack에 넣는다.
         * - 하나 씩 넣고 비교한다.
         *      - 1부터 순서대로 넣는다
         *          - if 만약, target의 숫자보다 넣는 숫자가 작다면 계속 넣는다. -> 같을때 까지 넣고, 같아지면 반복 종료
         *          --> 여기서 반복문이 종료 되었다는 것은 최상위 내용이 현재 target과 숫자가 같다는 뜻
         *
         *     위의 조건을 피해서 바로 여기로 왔다면, target의 숫자가 num보다 크다는 이야기!
         *      or 비어있다는 이야기
         *      비어있다면, 불가능!! 비어잇지 않을 때 가능!!
         *     if (최상위 숫자 == target)이라면,
         *      - pop , idx++
         *
         *      set과 top의 수가 같지 않다면, ->
         *      - 불가능
         * */
        Stack<Integer> stack = new Stack<>();
        StringBuilder buffer = new StringBuilder(); // 쌓아두다가 마지막까지 성공이면, 반환
        int num = 1; // stack에 들어갈 숫자

        int idx = 0; // set을 탐색 할 index
        while(idx < N){ //
            int target = set[idx];
            if(target >= num){
                while(target >= num){
                    stack.push(num++);
                    buffer.append("+\n");
                }
            }

            // stack에 가장 마지막에 넣고, 빼고나서 그 다음 숫자(현재 최상위)가 다음 타겟과 같다면
            // 근데 비어있다면??
            // 맨 처음에 위 조건을 통과하지 못할 것이라고 생각을 못했다..
            if(!stack.empty() && target == stack.peek()){
                stack.pop();
                buffer.append("-\n");
                idx++;
            }else{ // 비어있다면, 불가능하다는 것이니
                bw.write("NO\n");
                bw.flush();
                return;
            }
        }
        bw.write(buffer.toString());
        bw.flush();
    }
}
