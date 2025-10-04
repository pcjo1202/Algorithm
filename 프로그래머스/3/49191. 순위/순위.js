function solution(n, results) {
    const MAX = 123456789;
    let answer = 0;
    
    const graph = Array.from({length: n + 1}, () => Array(n + 1).fill(0))
    
    // 각각 선수들의 승패 결과를 1로 표현
    results.forEach(([A,B])=>{
        graph[A][B] = 1;
        graph[B][A] = -1;
    })

    
    // 주어진 내용을 토대로, 간접 승리, 패배 관계 구하기
    for(let k = 1; k < n + 1; k++){
        for(let i = 1; i < n + 1; i++){
            for(let j = 1; j < n + 1; j++){
                // 승리
                if(graph[i][k] === 1 && graph[k][j] === 1){
                    graph[i][j] = 1
                    graph[j][i] = -1
                }
                // 패배
                if(graph[i][k] === -1 && graph[k][j] === -1){
                    graph[i][j] = -1
                    graph[j][i] = 1
                }
            }
        }
    }
    
    // 순위 확실히 알 수 있는 => 승 + 패 === n - 1, 혹은 알 수 없는 결과가 있다면 (0) 알 수 없음?
    for(let i = 1; i < n + 1; i++){
        let flag = true;
        
        for(let j = 1; j < n + 1; j++){
            if(i === j) continue;
            
            if(graph[i][j] === 0){
                flag = false;
                break;
            }
        }
        
        // 확실히 알 수 있다면
        if(flag) answer++;
    }
    
    return answer;
}