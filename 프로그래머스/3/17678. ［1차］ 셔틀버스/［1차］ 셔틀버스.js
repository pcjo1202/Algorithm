function solution(n, t, m, timetable) {
    var answer = '';
    
    const start = 9 * 60
    
    // 버스 운영 시간
    const busTime = {}
    let last = 0;
    for(let i = 0; i < n ;i++){
        busTime[start + i * t] = []
        if(i === n - 1) last = start + i * t;
    }
    
    // 콘의 탑승 시간은 table의 마지막 사람 시간
    
    // 크루들 도착 시간
    const parsingTable = timetable.map((time)=>{
        const [m, s] = time.split(":")
        return Number(m) * 60 + Number(s)
    }).sort((o1,o2)=> o1- o2)
    
    // 출력 : 콘이 셔틀을 타고 사무실로 갈 수 있는 도착 시각 중 제일 늦은 시각
    
    
    const MaxBusNum = n * m; // 버스에 탑승 가능한 인원 수
    let num = 1; // 지금까지 버스 운행 횟 수
    let idx = 1;
    
    for(let people of parsingTable){
        for(const busKey of Object.keys(busTime).map(Number).sort((o1,o2)=>o1-o2)){
            if(busTime[busKey].length === m) continue;
            if(people <= busKey){
                busTime[busKey].push(people)
                break;
            }
        }
    }
    
    const lastBus = busTime[last]  // 마지막 버스 시간
        
    // 꽉 안차있다면
    if(lastBus.length < m){
        return converter(last)
    }
    // 마지막 버스에 사람이 꽉차있다면,
    else{
        return converter(lastBus[lastBus.length - 1] - 1)
    }
    
    function converter(time){
        let mm = Math.floor(time / 60) + ""
        let ss = time % 60 + ""
        
        return [mm.padStart(2,0), ss.padStart(2,0)].join(":")
    }
    
}

