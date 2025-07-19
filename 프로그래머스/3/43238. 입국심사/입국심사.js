function solution(n, times) {
    let answer = 0;
    
    let low = 1;
    let high = Math.max(...times) * n; // 가장 오래 걸린 경우 시간
    
    while(low <= high){
        const mid = Math.floor((low + high) / 2)
        
        let count = 0;
    
        // 현재 mid 값으로 체크
        for(const time of times){
            count += Math.floor(mid / time)
            
            if(count >= n) break; // 이미 넘어가면 바로 끝
        }
        
        if(count >= n){
            answer = mid
            high = mid - 1
        }else{
            low = mid + 1
        }
        
    }
    
    return answer;
}