/*
- 진열대의 특정 범위의 물건들을 모두 싹쓸이 구매
- 진열된 "모든 종류의 보석"을 적어도 1개 이상 포함하는 가장 짧은 구간
*/
function solution(gems) {
    const list = new Set(gems);
    const total = list.size;
    const curCnt = new Map(); // 보석 : 개수
    
    let left = 0, right = 0;
    let answer = [0, gems.length - 1]
    
    while(right < gems.length){
        const gem = gems[right]
        curCnt.set(gem, (curCnt.get(gem) || 0) + 1)
        right++;
        
        while(curCnt.size === total){
            // 최소 구간 갱신
            if(right - left < answer[1] - answer[0] + 1){
                answer = [left, right - 1];
            }
            
            // 왼쪽 보석 하나 제거
            const leftGem = gems[left]
            curCnt.set(leftGem, curCnt.get(leftGem) - 1);
            if(curCnt.get(leftGem) === 0){
                curCnt.delete(leftGem)
            }
            left++;
        }
    }
    
    
    return [answer[0] + 1, answer[1] + 1];
}