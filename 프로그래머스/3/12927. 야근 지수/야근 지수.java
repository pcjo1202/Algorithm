import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int res : works){
            pq.offer(res);
        }
        
        while (n > 0) {
            int cur = pq.poll();
            
            if(cur == 0) break;
            
            cur -= 1;
            pq.offer(cur);
            n--;
        }
        
        while(!pq.isEmpty()){
            int temp = pq.poll();
            answer += temp * temp;
        }
        
        return answer;
    }
}