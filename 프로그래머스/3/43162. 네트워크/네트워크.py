def solution(n, computers):

    count = 0
    visited = set()

    def dfs(node):
        visited.add(node)
        for j in range(n):
            if j not in visited and computers[node][j] == 1:
                dfs(j)

    for i in range(n):
        if i not in visited:
            dfs(i)
            count += 1
    
    return count