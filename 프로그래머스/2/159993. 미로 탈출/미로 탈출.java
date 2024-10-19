import java.util.*;

class Solution {
    static int N, M, dist;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[][] visited;
    static Node start, lever, end;
    
    static class Node{
        int i, j;
        public Node(int i , int j){
            this.i = i;
            this.j = j;
        }
    }
    public int solution(String[] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length();
        dist= 0;
        
        for(int i = 0 ; i < N ; i++){
            for(int j = 0; j < M ;j++){
                if(maps[i].charAt(j) =='S'){
                    start = new Node(i, j);
                }else if(maps[i].charAt(j) =='L'){
                    lever = new Node(i, j);
                }
            }
        }
    
        // 시작 -> 레버 -> 끝
        // 시작 -> 레버 최단거리
        int dist1 = bfs(maps, start, 'L');
        
        // 레버를 못찾으면 바로 종료
        if(dist1 == -1){
            return -1;
        }
        
        // 레버 -> 끝 최단거리
        int dist2 = bfs(maps, lever, 'E');
        
        if(dist2 == -1){
            return -1;
        }
        
        // 여기까지 오면 레버는 찾았다는 것!
        answer = dist1 + dist2;
        
        return answer;
    }
    
    static int bfs(String[] maps, Node from, char to){
        Queue<Node> queue = new ArrayDeque<>();
        
        visited = new int[N][M];
        
        for(int[] arr : visited){
            Arrays.fill(arr,-1);
        }
        
        
        queue.offer(from);
        visited[from.i][from.j] = 0;
        
        while(!queue.isEmpty()){
            Node cur = queue.poll();

            if(maps[cur.i].charAt(cur.j) == to){
                return visited[cur.i][cur.j];
            }     

            for(int d = 0; d < 4; d++){
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if(visited[ni][nj] != -1) continue;
                char next = maps[ni].charAt(nj);
                if(next == 'X') continue;

                
                queue.offer(new Node(ni,nj));
                visited[ni][nj] = visited[cur.i][cur.j] + 1;
            }
        }
        
        // 여기까지 나오면 발견하지 못하고 움직이지 못한 것.
        return -1;
    }
}