import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static Queue<Integer> q;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = 10;
        for (int test_case = 1; test_case <= T ; test_case++) {
            int tc = Integer.parseInt(br.readLine());
            q = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; i++) {
                q.offer(Integer.parseInt(st.nextToken()));
            }
            // 가장 먼저 넣었던 친구를 꺼내서, 1 감소 하고, 맨 뒤로 넣기
            boolean flag = true;
            int i = 0;
            while(flag){
                if(i >= 5){
                    i = 0;
                }
                int cur = q.poll();
                cur = cur - ++i;
                if(cur <= 0){
                    cur = 0;
                    flag = false;
                }
                q.offer(cur);
            }
            String result = "";
            while (!q.isEmpty()) {
                result += (q.poll()+" ");
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}