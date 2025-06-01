function solution(players, m, k) {
    let count = 0;
    let active = []; // 사용 중인 서버 [{time: number}]
    
    // 시간당 플레이어 수
    players.forEach((p)=>{
        // 1. 기존에 돌아가고 있던 서버 종료 여부 확인
        if(active.length > 0){
            active.forEach((server) => server.time++) // 서버 운영 시간 증가
            active = active.filter(({time})=> time <= k) // 서버 시간 지나면 삭제
        }
        

        // 2. p 의 수가 m 이상일 때 -> 서버 증설
        if(p >= m){
            const server = Math.floor(p / m) // 사용해야하는 서버 수

            // 이미 돌아가고 있는 서버가 있다면 차감해서 서버 증설
            const size = server - active.length
            if(size > 0){
                Array.from({length: size}).map(()=>{
                    active.push({
                        time: 1
                    })
                    count++;
                })
            }
            
        }
    })
    
    return count;
}