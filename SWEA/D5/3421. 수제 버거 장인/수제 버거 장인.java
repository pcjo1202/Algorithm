import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, count;
    static HashMap<Integer, ArrayList<Integer>> badPair;
    static boolean[] isSelected;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 재료 번호
            M = Integer.parseInt(st.nextToken()); // 궁합이 맞지 않는 쌍
            count = 0;
            isSelected = new boolean[N + 1]; // 1 ~ N 으로 확인

            badPair = new HashMap<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); // a
                int b = Integer.parseInt(st.nextToken()); // b

                if (!badPair.containsKey(a)) { // Key를 이미 가지고 있다면,
                    badPair.put(a, new ArrayList<>());
                }

                if (!badPair.containsKey(b)) { // Key를 이미 가지고 있다면,
                    badPair.put(b, new ArrayList<>());
                }

                badPair.get(a).add(b);
                badPair.get(b).add(a); // 문제 발견 ! -> 양방향을 해주지 않음
            }

            solve(1);

            sb.append("#").append(test_case).append(" ").append(count).append("\n");
        }
        System.out.println(sb);
    }

    static void solve(int cnt) {
        if (cnt > N) {
//            for (int i = 0; i < N + 1; i++) {
//                System.out.print(isSelected[i] ? "[" + (i) + "] " : "");
//            }
//            System.out.println();
            count++;
            return;
        }


        // 현재 값을 선택했을 때 O -> 이 친구가 진짜 사용 가능한 친구인지 확인
        boolean flag = true;
        // 찾고 있는 조합 중 badPair있다면 빼자 [a, b]
        if (badPair.containsKey(cnt)) { // 현재 값이 badPair에 들어있다면,
            for (int i = 1; i <= cnt; i++) { // 현재까지 만들어진 조합 중 확인
                // 현재 뽑기로 결정한 cnt가 현재 요소가 cnt와 짝을 이루어 있다면, 건너뛰기
                // 아니라면, 인정~ 뽑아주자
                if (isSelected[i] && badPair.get(cnt).contains(i)) {
                    flag = false;
                    break;
                }
            }
        }


        if (flag) {
            isSelected[cnt] = true;
            solve(cnt + 1);
        }

        // 현재 값을 선택하지 X
        isSelected[cnt] = false;
        solve(cnt + 1);
    }
}