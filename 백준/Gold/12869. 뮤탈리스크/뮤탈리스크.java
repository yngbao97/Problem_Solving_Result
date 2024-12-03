import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int[][] attack = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}};
	static int[][][] dp = new int[61][61][61];
	static int min;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] svc = new int[3];
		
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			svc[i] = Integer.parseInt(input[i]);
		}
		
		min = Integer.MAX_VALUE;
		dfs(svc, 0);
		
		System.out.println(min);
		br.close();
	}
	private static void dfs(int[] hp, int cnt) {
		Arrays.sort(hp);
		
		if (min <= cnt) return;
		
		if (hp[0] == 0 && hp[1] == 0 && hp[2] == 0) {
			min = Math.min(min, cnt);
		}
		
		if (dp[hp[0]][hp[1]][hp[2]] != 0 && dp[hp[0]][hp[1]][hp[2]] <= cnt) return;
		dp[hp[0]][hp[1]][hp[2]] = cnt;
		
		for (int i = 0; i < 6; i++) {
			int[] newHp = new int[3];
			newHp[0] = Math.max(hp[0]-attack[i][0], 0);
			newHp[1] = Math.max(hp[1]-attack[i][1], 0);
			newHp[2] = Math.max(hp[2]-attack[i][2], 0);
			
			dfs(newHp, cnt+1);
		}
	}
}