import java.util.*;

class Solution {
    static int N, M;
    static boolean isWork; // 작업 수행중인지 확인
    static int[] resultTime;
    
    public int solution(int[][] jobs) {
        int answer = 0;
        N = jobs.length;

        isWork = false;
        
        // 평균을 가장 줄이는 방법
        // 작업시간이 가장 작은 작업부터 처리
        
        for(int [] arr : jobs){
            System.out.println(Arrays.toString(arr));
        }
        
        // 대기 큐(작업 소요 시간이 작은 작업부터) -> 작업 수행이 가능한 작업을 담음
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> {
            if(o1[1] == o2[1]){ // 소요 시간이 같을 때
                return o1[0] - o2[0]; // 요청 시점으로 정렬
            }
            return o1[1] - o2[1]; // 작업 소요 시간
        });
        
           
        resultTime = new int[N]; // 요청 시점 ~ 종료까지 걸린 시간
        
        int time = 0; // 현재 시간
        int count = 0; // 처리한 작업의 개수
        
        boolean[] isWait = new boolean[N]; // 대기큐가 들어갔는지 아닌지 확인하는 배열 (들어감:true, 아님: false)
        
        // 작업 시작 (처리한 작업의 개수가 jobs의 개수와 같다면 종료)
        while(count < N){
            // 1. 수행 가능한 작업을 대기 큐에 담음 (현재 시간을 기준으로 요청 시점이 작은 작업)
            for(int i = 0 ; i < N ;i++){
                if(isWait[i]) continue; // 완료한 작업 건너 뛰기
                
                if(jobs[i][0] <= time){ 
                    pq.offer(jobs[i]);
                    isWait[i] = true;
                }
            }
            
            // 2. 대기 큐에서 하나를 가져와서(수행 시간이 짧은) 작업을 수행
            if(!pq.isEmpty()){
                int[] curTask = pq.poll();
                // 작업 수행
                time += curTask[1]; // 이때 time이 현재 작업한 작업의 수행 시간
                
                // 수행한 작업의 요청 ~ 종료까지의 시간을 answer 변수에 저장.
                answer += time-curTask[0];
                count++;
            }else{
                // 대기 큐가 비어있다면 시간 증가
                time++;
            }

        }
        
        
        return answer / N;
    }
}