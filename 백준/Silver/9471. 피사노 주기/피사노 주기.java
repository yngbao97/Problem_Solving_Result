import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[] fibo;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int p = Integer.parseInt(br.readLine());
		int[] answer = new int[p];
		int[] mod = new int[p];

		for (int i = 0; i < p; i++) {
			String[] input = br.readLine().split(" ");
			mod[i] = Integer.parseInt(input[1]);

			answer[i] = pisano(mod[i]);
		}

		for (int i = 0; i < p; i++) {
			bw.write(String.valueOf(i+1) + " ");
			bw.write(String.valueOf(answer[i]) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	private static int pisano(int m) {

		int cnt = 1;
		int first = 1;
		int second = 2 % m;

		while (!(first == 1 && second == 1)) {
			int tmp = first;
			first = second;
			second = (tmp + first) % m;
			cnt++;
		}

		return cnt;
	}
}