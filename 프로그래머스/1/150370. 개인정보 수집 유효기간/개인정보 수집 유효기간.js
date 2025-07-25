function solution(today, terms, privacies) {
    const answer = [];
    
    const [tYear, tMonth, tDay] = today.split(".").map(Number)
    const termData = {}
    terms.forEach(each => {
        const [type, size] = each.split(" ")
        termData[type] = {type, size: Number(size)}
    })
    
    privacies.forEach((each, idx)=>{
        const [date, type] = each.split(" ")
        const [y,m,nDay] = date.split(".").map(Number)
        
        const totalMonth = y * 12 + m + termData[type].size
        
        const nMonth = (totalMonth - 1) % 12 + 1
        const nYear = Math.floor((totalMonth - 1) / 12)
        
        // 파기 해야할 정보 : 오늘 & nMonth, nYear, d 비교
        // 파기 기한 : 기간 - 1일 까지
        // 오늘이 기한보다 크다면 파기
        if(check({nYear, nMonth, nDay})){
            answer.push(idx + 1)
        }
        
    })
    
    // 파기 여부 확인
    function check({nYear, nMonth, nDay}){
        if(tYear > nYear){
            return true
        }else if(tYear === nYear ){
            if(tMonth > nMonth){
                return true
            }else if(tMonth === nMonth){
                if(tDay >= nDay){
                    return true
                }
            }
        }
        return false
    }
    
    return answer.sort((o1,o2) => o1 - o2);
}