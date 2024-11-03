import java.util.*;

class Solution {
    int N, M;
    int[][] visited;
    int[] di = {-1, 1, 0, 0};
    int[] dj = {0, 0, -1, 1};
    HashMap<Integer, Integer> list;
    
    public int solution(int[][] land) {
        int answer = 0;
        N = land.length;
        M = land[0].length;
        visited = new int[N][M];
        
        for(int[] arr: visited){
            Arrays.fill(arr, -1);
        }
        
        list = new HashMap<>();
        
        
         // 석유 덩어리크기 구분
        int num = 0;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0; j < M ; j++){
                if(land[i][j] == 1 && visited[i][j] == -1){
                    int temp = bfs(i, j, land, ++num);
                    list.put(num, temp);
                }
            }
        }
        
        for(int i = 0 ; i < M ; i++){
            int sum = 0;
            
            // 중복 방지 체크
            boolean[] check = new boolean[list.size() + 1];
            
            for(int j = 0; j < N; j++){
                if(visited[j][i] != -1){
                    if(check[visited[j][i]]) continue;
                    check[visited[j][i]] = true;
                    sum += list.get(visited[j][i]);
                }
            }
            
            answer = Math.max(answer, sum);
        }
        
        // 열을 한번씩 돌아서 최대 획득 덩어리 구하기
        
        return answer;
    }
    
    int bfs(int i, int j, int[][] land, int num){
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = num;
        count++;
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            
            for(int d = 0; d < 4; d++){
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];
                
                if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if(visited[ni][nj] != -1) continue;
                
                if(land[ni][nj] == 1){
                    queue.offer(new int[]{ni, nj});
                    visited[ni][nj] = num;
                    count++;
                }
                
            }
        }
        
       return count;
    }
    
}