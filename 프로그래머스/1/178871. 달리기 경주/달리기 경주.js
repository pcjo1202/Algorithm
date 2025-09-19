function solution(players, callings) {
    
    const rank = new Map();
    const revRank = new Map();
    
    players.forEach((each, idx)=>{
        rank.set(each, idx);
        revRank.set(idx, each);
    })
    
    callings.forEach((each)=>{
        const curIdx = rank.get(each); // 이름 불린 친구의 원래 등수
        const nextIdx = curIdx - 1 // 재낀 친구 등수
        
        const next_player = revRank.get(nextIdx); // 재낀 친구 이름
        
        rank.set(each, nextIdx)
        rank.set(next_player, curIdx)
        revRank.set(nextIdx, each)
        revRank.set(curIdx, next_player)
    })
    
    
    const sortRank = [...rank].sort((o1, o2) => o1[1] - o2[1])
    
    return [...new Map(sortRank).keys()];
}