function solution(cacheSize, cities) {
    let answer = 0;
    let cache = [];

    for (let city of cities) {
        city = city.toLowerCase();

        let idx = cache.indexOf(city);

        // 발견
        if (idx !== -1) {
            // cache Hit
            cache.splice(idx, 1); // 기존 위치 제거
            cache.push(city);     // 가장 최근에 사용된 것으로 다시 추가
            answer += 1;
            
        } else {
            // cache Miss
            if (cacheSize > 0 && cache.length >= cacheSize) {
                cache.shift(); // 가장 오래된 항목 제거
            }
            if (cacheSize > 0) {
                cache.push(city);
            }
            answer += 5;
        }
    }

    return answer;
}
