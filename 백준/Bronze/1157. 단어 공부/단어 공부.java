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

		int upper = 'a' - 'A';
		int[] alpha = new int[26];

		char[] input = br.readLine().toCharArray();
		int max = 0;

		for (char c : input) {
			int idx = 0;
			if (c >= 'a') idx = c - 'A' - upper;
			else idx = c - 'A';

			max = Math.max(max, ++alpha[idx]);
		}

		List<Character> answer = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			if (max == alpha[i]) {
				char c = (char) (i + 'A');
				answer.add(c);
			}
		}

		if (answer.size() > 1) bw.write("?");
		else bw.write(String.valueOf(answer.get(0)));
		
		bw.flush();
		bw.close();
		br.close();
	}
}