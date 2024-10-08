import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {
    static int[] arr;
    static boolean[] visited;
    static int N;
    static List<Long> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr=new int[11];
        visited = new boolean[11];

        for (int i = 1; i <= 10; i++) {
            dfs(0, 0, i);
        }


        Collections.sort(result);

        if(result.size()<=N){
            System.out.println(-1);
        }else {
            System.out.println(result.get(N));
        }

    }
    //9 ,8 7 6, 5 4 3, 2 1 0

    static void dfs(int depth, int start, int max){
        if(depth==max){
            StringBuilder sb = new StringBuilder();
            //감소하는수로 바꿈
            for (int i = max-1; i >=0 ; i--) {
                sb.append(arr[i]);
            }
            result.add(Long.parseLong(sb.toString()));
            return;
        }

				// 증가하는 수
        for (int i = start; i <= 9 ; i++) {
            if (!visited[i]) {
                visited[i]= true;
                arr[depth] = i;
                dfs(depth+1, i, max);
                visited[i] = false;
            }
        }
    }
}