import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int l;
	static int c;
	static char[] moji;
	static boolean[] moum;
	static char[] answer;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		l = Integer.parseInt(input[0]);
		c = Integer.parseInt(input[1]);

		st = new StringTokenizer(br.readLine(), " ");
		moji = new char[c];
		for (int i = 0; i < c; i++) moji[i] = st.nextToken().charAt(0);
		Arrays.sort(moji);

		moum = new boolean[c];
		for (int i = 0; i < c; i++) if (moji[i] == 'a' || moji[i] == 'e'
				|| moji[i] == 'i' || moji[i] == 'o' || moji[i] == 'u') moum[i] = true;

		answer = new char[l];
		dfs(0, 0, 0, 0);

		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int idx, int cnt, int m, int j) throws IOException {
		if (cnt >= l) {
			if (m < 1 || j < 2) return;
			bw.write(String.valueOf(answer) + "\n");
			return;
		}

		if (idx >= c) return;

		answer[cnt] = moji[idx];
		if (moum[idx]) dfs(idx + 1, cnt + 1, m + 1, j);
		else dfs(idx + 1, cnt + 1, m, j + 1);

		dfs(idx + 1, cnt, m, j);
	}
}