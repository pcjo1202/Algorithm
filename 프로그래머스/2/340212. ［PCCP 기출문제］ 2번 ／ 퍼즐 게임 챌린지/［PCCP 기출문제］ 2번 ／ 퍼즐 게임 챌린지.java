class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int left = 1;
        int right = 300000; // 숙련도의 최대 값은 난이도와 같을...
        
        // 이분 탐색으로 가능한 level 찾기
        while(left < right){
            int mid = (left + right) / 2;
            
            // 현재 값이 가능하지 확인
            if(check(mid, diffs, times, limit)){ // 가능할 때
                right = mid;
            }else{
                // 불가능할 때
                left = mid + 1;
            }
        }
        
        answer = right;
        
        return answer;
    }
    
    boolean check(int level, int[] diffs, int[] times, long limit){
        long sum = 0;
        
        for(int i = 0 ; i < diffs.length ; i++){
            int curDiff = diffs[i]; // 현재 퍼즐의 난이도
            int curTime = times[i]; // 현재 퍼즐의 소요 시간
            
            if(curDiff <= level){
                sum += curTime;
            }else{
                int wrong = curDiff - level;
                int prevTime = times[i - 1];// prevTime
                sum += (curTime + prevTime) * wrong + curTime;
            }
            if(sum > limit) return false;
        }
        
        // 전체 소요 값이 limit를 넘지 않는 다면 true
        return sum <= limit;
    }
    
}