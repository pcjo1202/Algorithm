import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue((o1, o2) -> {
            return o2 - o1;
        });
        
        for(int res : works){
            pq.offer(res);
        }
        
        System.out.println(pq);
        
        return answer;
    }
}