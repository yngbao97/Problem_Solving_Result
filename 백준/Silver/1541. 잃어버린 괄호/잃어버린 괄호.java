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
		st = new StringTokenizer(br.readLine(), "-+", true);

		boolean minus = false;
		int answer = 0;
		while (st.hasMoreTokens()) {
			if (minus) {
				int num = Integer.parseInt(st.nextToken());
				if (st.hasMoreTokens()) st.nextToken();
				answer -= num;
			} else {
				int num = Integer.parseInt(st.nextToken());
				if (st.hasMoreTokens() && st.nextToken().equals("-")) minus = true;
				answer += num;
			}
		}

		bw.write(String.valueOf(answer));
		
		bw.flush();
		bw.close();
		br.close();
	}
}