import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];

        if (n == 1) bw.write("0");
        else {
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i-1] + 1;
                if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
                if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
//                System.out.println(Arrays.toString(dp));
            }
            bw.write(String.valueOf(dp[n]));
        }

		bw.flush();
		bw.close();
		br.close();
	}
}