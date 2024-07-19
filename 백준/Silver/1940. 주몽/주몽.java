import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int[] set;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		// 입력, 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		set = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < set.length; i++) {
			set[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(set);
		
		int i = 0;
		int j = N -1;
		
		while(i<j) {
			int temp = set[i] + set[j];
			if(temp == M) {
				count++;
				i++;
				j--;
			}else if(temp > M ) {
				j--;
			}else {
				i++;
			}
			
		}

		bw.write(count + "");

		bw.flush();
		bw.close();
	}

}