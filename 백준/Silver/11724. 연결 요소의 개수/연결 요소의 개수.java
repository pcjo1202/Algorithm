import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][] arr;
	private static boolean[] visited;
	private static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		visited = new boolean[N]; // 방문한지 확인

		for (int i = 0; i < M; i++) { // 인접행렬
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; // 시작 인덱스를 0부터 시작하기 위해
			int b = Integer.parseInt(st.nextToken()) - 1;

			arr[a][b] = 1;
			arr[b][a] = 1;
		}

		for (int i = 0; i < N; i++) {
			// 방문을 한적이 없다면, 방문체크하고, 해당 노드와 인접한 친구들 dfs 함수로 찾기
			if (!visited[i]) {
				visited[i] = true;
				count++;
				dfs(i);
			}
		}
		
		System.out.println(count);
	}

	public static void dfs(int i) { // 노드와 인접한 친구들 찾기
		// i 와 인접한 행렬이 있고, 방문한 적이 없다면 방문처리!
		for (int j = 0; j < arr.length; j++) {
			if(arr[i][j] == 1 && !visited[j]) {
				visited[j] = true;
				dfs(j);
			}
			
		}
	}

}
