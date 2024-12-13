import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static Map<Long, Long> dp;
	static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long n = Long.parseLong(br.readLine());

		dp = new HashMap<>();
		dp.put(0L, 0L);
		dp.put(1L, 1L);
		dp.put(2L, 1L);
		dp.put(3L, 2L);
		long answer = fibo(n);

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	public static long fibo(long n) {

		if (dp.containsKey(n)) return dp.get(n);

		long result = 0;
		if (n % 2 == 0) {
			result = fibo(n / 2) * (fibo(n / 2 + 1) + fibo(n / 2 - 1));
		} else {
			result = fibo((n + 1) / 2) * fibo((n + 1) / 2)  % MOD + fibo((n - 1) / 2) * fibo((n - 1) / 2)  % MOD;
		}

		result %= MOD;
		dp.put(n, result);
		return result;
	}
}