function solution(stones, k) {
    let left = 1;
    let right = 200000000; // 돌의 최대 숫자

    while (left <= right) {
        let mid = Math.floor((left + right) / 2);
        let count = 0;
        let maxCount = 0;

        for (let stone of stones) {
            if (stone - mid < 0) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count = 0;
            }
        }

        if (maxCount < k) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return right;
}
