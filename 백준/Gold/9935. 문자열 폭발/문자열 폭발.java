import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] input = br.readLine().toCharArray();
		char[] bomb = br.readLine().toCharArray();
		int N = bomb.length;
		
		int[] dp = new int[input.length];
		Deque<Character> dq = new LinkedList<>();
		
		int bombIdx = 0;
		int idx = 0;
		
		for (int i = 0; i < input.length; i++) {
			
			dq.addLast(input[i]);
			
			if (input[i] == bomb[bombIdx]) dp[idx++] = bombIdx++;
			else {
				bombIdx = 0;
				if (input[i] == bomb[bombIdx]) dp[idx++] = bombIdx++;
				else dp[idx++] = -1;
			}
			
			if (bombIdx == N) {
				
				for (int b = 0; b < N; b++) dq.pollLast();
				idx -= N;
				if (idx > 0) bombIdx = dp[idx-1] + 1;
				else bombIdx = 0;
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		if (dq.isEmpty()) sb.append("FRULA");
		else while (!dq.isEmpty()) sb.append(dq.pollFirst());
		
		System.out.println(sb.toString());
		br.close();
	}
}