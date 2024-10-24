import java.util.*;

class Solution {
    List<ArrayList<Integer>> list;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int size = sources.length;
        
        int[] answer = new int[size];
        
        list = new ArrayList<>();
        
        for(int i = 0; i < n + 1 ; i++){
            list.add(i, new ArrayList<>());
        }
        
        for(int[] arr : roads){
            int a = arr[0];
            int b = arr[1];
            list.get(a).add(b);
            list.get(b).add(a);
        }
        
      
        int[] temp = bfs(n, destination);
        
        for(int i = 0 ; i < size ; i++){
            answer[i] = temp[sources[i]];
        }
        
        return answer;
    }
    
     
    int[] bfs(int n, int start){
        int[] visited = new int[n + 1];
        
        Arrays.fill(visited, -1);
        
        
        Queue<Integer> queue = new ArrayDeque<>();
        
        queue.offer(start);
        visited[start] = 0;
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
            
            for(int next : list.get(cur)){
                if(visited[next] == -1){
                    queue.offer(next);
                    visited[next] = visited[cur] + 1;
                }
            }
        }
        
        return visited;
    }
    
}