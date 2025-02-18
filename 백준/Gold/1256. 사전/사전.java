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

		st = new StringTokenizer(br.readLine(), " ");
		int aCnt = Integer.parseInt(st.nextToken());
		int zCnt = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// dp 테이블 초기화
		int[][] dp = new int[aCnt+1][zCnt+1];

		// 다른 문자가 하나도 없는 상태는 경우의 수가 모두 1
		for (int i = 1; i <= aCnt; i++) dp[i][0] = 1;
		for (int i = 1; i <= zCnt; i++) dp[0][i] = 1;

		// a가 하나 없는 경우의 수와, z가 하나 없는 경우의 수를 더함
		for (int i = 1; i <= aCnt; i++) {
			for (int j = 1; j <= zCnt; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
				if (dp[i][j] > 1_000_000_000) dp[i][j] = 1_000_000_000;
			}
		}

		// k가 만들 수 있는 단어의 개수보다 클 경우 -1 출력
		if (k > dp[aCnt][zCnt]) bw.write("-1");
		else {
			int idx = 0;
			int length = aCnt + zCnt;
			while (idx < length) {
				if (aCnt <= 0) {
					while (zCnt > 0) {
						bw.write("z");
						zCnt--;
						idx++;
					}
					break;
				} else if (zCnt <= 0) {
					while (aCnt > 0) {
						bw.write("a");
						aCnt--;
						idx++;
					}
					break;
				}

				if (dp[aCnt-1][zCnt] >= k) {
					bw.write("a");
					aCnt--;
				} else {
					bw.write("z");
					k -= dp[aCnt-1][zCnt];
					zCnt--;
				}

				idx++;
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}
}