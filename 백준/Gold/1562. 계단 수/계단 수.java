import java.util.Scanner;

public class Main {

	static int N;
	static final int MOD = 1_000_000_000;
	static long[][] rest = new long[10][91];
	static long[][][] dp = new long[10][1024][101];
	public static void main(String[] args) {
		
		// dp테이블 초기화
		for (int j = 0; j <= 90; j++) {
			for (int i = 0; i < 10; i++) {
				if (j == 0) rest[i][j] = 1;
				else if (i == 0) rest[i][j] = rest[1][j-1];
				else if (i == 9) rest[i][j] = rest[8][j-1];
				else rest[i][j] = (rest[i-1][j-1] + rest[i+1][j-1]) % MOD;
			}
		}
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		long answer = 0;
		
		if (N < 10) answer += 0;
		else {
			for (int i = 1; i < 10; i++) {
				answer += dfs(i, 1, 1 << i);
			}
		}
		
		
		System.out.println(answer % 1_000_000_000);
		sc.close();

	}
	
	private static long dfs(int num, int idx, int visited) {
		
		if (visited == ((1 << 10)-1)) return rest[num][N-idx];
		
		if (idx >= N) return 0;
		
		if (dp[num][visited][N-idx] != 0) return dp[num][visited][N-idx];
		
		long answer = 0;
		
		if (num > 0) answer += dfs(num-1, idx+1, visited | (1<<(num-1)));
		if (num < 9) answer += dfs(num+1, idx+1, visited | (1<<(num+1)));
		
		return dp[num][visited][N-idx] = answer % 1_000_000_000;
	}

}