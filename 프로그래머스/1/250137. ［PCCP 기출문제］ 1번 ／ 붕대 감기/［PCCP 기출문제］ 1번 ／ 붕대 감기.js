function solution(bandage, health, attacks) {
    const [t, x, y] = bandage
    // attacks = [[time, damage],]
    let HP = health ;
    
    let prevTime = 0; // 직전 공격 시간
    
    for(const [time, damage] of attacks){
        // 이전 공격 시간 ~ 다음 공격 시간 - 1 (공격 당시는 회복에서 제외)
        const diff = time - prevTime - 1;
        
        if(diff > 0){
            // 체력 회복하기
            HP += x * diff;
        
            // 연속 공격이 시전 시간 만큼 되면 초기화 & 추가 회복
            const plus = Math.floor(diff / t);
            HP += y * plus;

            // 최대 체력이면 더이상 증가 X
            HP = Math.min(HP, health);
        }
        
        // 공격 받기 
        HP -= damage
        
        // 종료 체크
        if(HP <= 0) return -1;
        
        // 직전 시간 갱신
        prevTime = time;
    }
    
    return HP > 0 ? HP : -1; // 모든 공격이 끝난 직후 남은 체력
}