
const di = [0,0,-1,1];
const dj = [-1,1,0,0];

function solution(maps) {
    const answer = [];
    
    const N = maps.length
    const M = maps[0].length
    
    const visited = new Set();
    
    for(let i = 0; i < N; i++){
        for(let j = 0; j < M; j++){
            if(visited.has(`${i}-${j}`)) continue
            if(maps[i][j] === 'X') continue;
            answer.push(bfs({i, j}))
        }
    }
    
    function bfs(start){
        const {i, j} = start
        
        let sum = 0;
        
        const queue = []; // queue
        queue.push({i,j});
        visited.add(`${i}-${j}`);
        
        while(queue.length !== 0){
            const {i,j} = queue.shift();
            
            const cur = Number(maps[i][j]);
            
            if(!isNaN(cur)) sum += cur;
            
            for(let d = 0; d < 4; d++){
                const ni = i + di[d];
                const nj = j + dj[d];
                
                if(visited.has(`${ni}-${nj}`)) continue;
                if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if(maps[ni][nj] === 'X') continue;
                
                queue.push({i:ni, j:nj})
                visited.add(`${ni}-${nj}`)
            }
        }
        
        return sum;
    }
    
    return answer.length === 0 ? [-1] : answer.sort((o1,o2)=> o1 - o2);
}