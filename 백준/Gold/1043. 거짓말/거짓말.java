import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		List<Integer>[] person = new List[N+1];
		for (int i = 0; i <= N; i++) {
			person[i] = new ArrayList<>();
		}
		
		int[][] party = new int[M+1][];
		boolean[] know = new boolean[N+1];
		boolean[] disable = new boolean[M+1];
		int answer = M;
		
		input = br.readLine().split(" ");
		int k = Integer.parseInt(input[0]);
		for (int i = 1; i <= k; i++) {
			int p = Integer.parseInt(input[i]);
			know[p] = true;
		}
		
		for (int i = 1; i <= M; i++) {
			input = br.readLine().split(" ");
			int cnt = Integer.parseInt(input[0]);
			party[i] = new int[cnt];
			for (int j = 0; j < cnt; j++) {
				int p = Integer.parseInt(input[j+1]);
//				System.out.println(party.length);
				party[i][j] = p;
				person[p].add(i);
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (know[i]) {
				for (int pot : person[i]) {
					if (disable[pot]) continue;
					disable[pot] = true;
					queue.add(pot);
					answer--;
				}
			}
		}
		
		while (!queue.isEmpty()) {
			int pot = queue.poll();
			for (int p : party[pot]) {
				if (know[p]) continue;
				
				for (int g : person[p]) {
					if (disable[g]) continue;
					queue.add(g);
					disable[g] = true;
					answer--;
				}
			}
		}
		
		System.out.println(answer);
		br.close();
	}

}
