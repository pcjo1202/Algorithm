function solution(p) {
    var answer = '';
    
    // 올바른 괄호 문자열인지 체크
    function check(str){
        const stack = [] // ")" 넣을 stack
        
        for(const ch of str){
            if(ch === "("){
                stack.push(ch)
            }else{
                if(stack.length === 0) return false
                stack.pop();
            }
        }
        
        return stack.length === 0;
    }
    
    // 균형 -> 올바른 변환 함수
    function changeToCorrect(str){
        // 1.
        if(str.length === 0) return "";

        // 2. 어떤 기준으로 잘라? "두개 다 균형 잡히도록"
        let idx = 0;
        let count = 0;
        
        for(const [i, ch] of [...str].entries()){
            if(ch === "(") count++
            else count--
            if(count === 0){
                idx = i + 1;
                break;
            }
        }
        
        let u = str.substring(0 ,idx)
        let v = str.substring(idx)

        
        // 3.
        if(check(u)){
            return u += changeToCorrect(v)
        }
        // 4.
        else{
            let temp = "(" + changeToCorrect(v) + ")";
            
            let slice_text = ""
            
            for(const each of [...u.slice(1, u.length - 1)]){
                slice_text += ( each === "(") ? ")" : "("
            }
            
            return temp + slice_text;
        }
        
    }
    
    // 초기값 확인
    if(check(p)) return p;
    
    answer += changeToCorrect(p)
    
    return answer;
}

