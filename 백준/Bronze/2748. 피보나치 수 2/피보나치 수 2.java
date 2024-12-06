import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static long[] dp;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		dp = new long[n+1];

		long answer = fibo(n);
		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	private static long fibo(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		if (dp[n] != 0) return dp[n];

		return dp[n] = fibo(n-1) + fibo(n-2);
	}
}