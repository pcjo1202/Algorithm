def solution(numbers, target):
    answer = 0

    def dfs(idx, total):
        if idx == len(numbers):
            if target == total:
                nonlocal answer
                answer += 1
            return
        # + 1이 되었을 때
        dfs(idx + 1, total + numbers[idx])
        # - 1이 되었을 때
        dfs(idx + 1, total - numbers[idx])

    dfs(0, 0)

    return answer


print(solution([1, 1, 1, 1, 1], 3))

