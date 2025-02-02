function solution(plans) {
    let answer = [];
    let waiting = []; // 대기 중인 과제 저장 (스택)
    
    const toMinutes = (time) => {
        const [h, m] = time.split(":").map(Number);
        return h * 60 + m;
    };

    // 1. 시간 순서대로 정렬
    plans.sort((a, b) => {
        return toMinutes(a[1]) - toMinutes(b[1]);
    });

    
    let curTime = 0; // 현재 시간 (분 단위)

    for (let i = 0; i < plans.length; i++) {
        
        let [subject, start, playTime] = plans[i];
        
        playTime = +playTime; // 숫자로 변환

        const [h, m] = start.split(":").map(Number);
        let startTime = h * 60 + m; // 시작 시간 (분 단위)

        // 대기 중인 작업 중 남은 작업이 있는지 확인 후 처리
        while (waiting.length > 0 && curTime < startTime) {
            let [prevSubject, prevTime] = waiting.pop(); // 작업, 
            
            if (curTime + prevTime <= startTime) {
                // 이전 작업을 끝낼 수 있을 때
                answer.push(prevSubject);
                curTime += prevTime;
                
            } else {
                // 다 끝내지 못할 때
                
                const time = prevTime - (startTime - curTime); // 남은 시간
                waiting.push([prevSubject, time]);
                
                curTime = startTime;
            }
        }

        // 새로운 과제
        curTime = startTime + playTime;

        // 다음 과제가 존재하고, 현재 과제를 마칠 시간이 > 다음 과제 시작 시간보다 늦다면 남은 시간 저장
        if (i + 1 < plans.length) {
            let nextStartTime = toMinutes(plans[i + 1][1])
                
            if (curTime > nextStartTime) {
                waiting.push([subject, curTime - nextStartTime]); // [주제, 남은 시간]
                curTime = nextStartTime;
            } else {
                answer.push(subject); // 과제가 정상적으로 끝났다면 결과에 추가
            }
        } else {
            answer.push(subject);
        }
    }

    // 남아 있는 과제 처리 
    while (waiting.length > 0) {
        answer.push(waiting.pop()[0]);
    }

    return answer;
}
