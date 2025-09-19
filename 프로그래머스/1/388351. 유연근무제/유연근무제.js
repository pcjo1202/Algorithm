function solution(schedules, timelogs, startday) {
    let answer = 0;

    schedules.forEach((schedule, i) => {
        // 출근 허용 시간 계산
        let goal = schedule + 10;
        
        goal += goal % 100 >= 60 && 40
        // if (goal % 100 >= 60) {
        //     goal += 100;
        //     goal -= 60;
        // }

        let count = 0;
        for (let j = 0; j < 7; j++) {
            const weekday = (startday - 1 + j) % 7;
            if (weekday === 5 || weekday === 6) {
                continue; // 주말 제외
            }
            if (timelogs[i][j] > goal) {
                break; // 지각 시 탈락
            }
            count++;
        }

        if (count === 5) {
            answer++;
        }
    });

    return answer;
}
