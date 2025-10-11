function solution(n, computers) {
    let answer = 0;
    const visited = new Set();
    
    function bfs(start){
        const queue = [];
        queue.push(start);
        visited.add(start);
        
        while(queue.length > 0){
            const cur = queue.shift();
            
            for(let next = 0 ; next < n ; next++){
                if(visited.has(next) || computers[cur][next] === 0)continue;
                
                queue.push(next);
                visited.add(next);
            }
        }
        
        return 1;
    }
    
    for(let i = 0 ; i < n ; i++){
        if(visited.has(i)) continue;
        answer += bfs(i)
    }
    
    return answer;
}