import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 최소힙
        
        for(int i = 0; i < enemy.length; i++){
            pq.offer(enemy[i]);
            
            if(pq.size() <= k){
                answer++;
                continue;
            }
            n -= pq.poll();
            if(n < 0) break;
            answer++;
        }
        
        return answer;
    }
}