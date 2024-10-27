import java.util.*;

// 반례 : [2,3], [2,1]

class Solution {
    int size;
    long sum1, sum2, target; // 오버 플로우 방지
    public int solution(int[] queue1, int[] queue2) {
        int count = 0; // 작업 횟수 
        
        size = queue1.length; // queue 사이즈
        
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        
        // 1. 각각의 queue 합이 될 taget 값 구하기 & 진짜 큐에 담기
        for(int i = 0; i < size ;i++){
            sum1 += queue1[i];
            sum2 += queue2[i];
            
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }
        
        // 종료조건: 두 큐의 원소의 합이 홀수일 때 불가능
        if((sum1 + sum2) % 2 == 1){
            return -1;
        }
        
        target = ( sum1 + sum2 ) / 2;
        
        // 각 큐의 합을 기준으로 합의 크기가 더 큰 쪽의 숫자를 먼저 꺼낸다??
        // sum1과 target이 같아지면 반복문 종료
        while(sum1 != target){             
            // 종료 조건 : 모든 원소가 서로 최대 횟수로 교환을 했는데, 답을 못찾았다면
            if(count == size * 2 + size){
                return -1;
            }
            
            // 1. sum1과 sum2의 차이를 확인, 더 큰쪽의 큐를 꺼내서 다른 한쪽에 넣는다.
            if(sum1 > sum2){
                int temp = q1.poll();
                q2.offer(temp);
                
                sum1 -= temp;
                sum2 += temp;
                
            }else if(sum1 < sum2){
                int temp = q2.poll();
                q1.offer(temp);
                
                sum2 -= temp;
                sum1 += temp;
            }
            
            count++; // 작업 횟수 증가
            
            // 종료 조건 : 한쪽의 큐가 비게 된다면
            if(q1.isEmpty() || q2.isEmpty()){
                return -1;
            }

            
        }
        
        return count;
    }
}