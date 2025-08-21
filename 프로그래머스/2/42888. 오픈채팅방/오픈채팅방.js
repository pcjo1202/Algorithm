function solution(record) {
    var answer = [];
    
    // userId - nickname 관리 자료구조
    // {userId : nickname}
    const status = new Map(); // userId : nickname
    
    record.forEach(each =>{
        const [command, userId, nickname] = each.split(" ")
        
        switch(command){
            case "Enter":
                status.set(userId, nickname)
                answer.push({userId, command})
                break;
            case "Leave":
                answer.push({userId, command})
                break;
            case "Change":
                status.set(userId, nickname)
                break;
        }
    })
    
    answer = answer.map(({userId, command}) => {
        const nickname = status.get(userId)
        if(command === "Enter"){
            return enterString(nickname)
        }else if(command === "Leave"){
            return leaveString(nickname)
        }
    })
    
    return answer;
}

const enterString = (name) => `${name}님이 들어왔습니다.`
const leaveString = (name) => `${name}님이 나갔습니다.`
