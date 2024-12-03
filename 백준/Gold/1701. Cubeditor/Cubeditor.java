import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		char[] input = sc.nextLine().toCharArray();
		int answer = 0;
		for (int k = 0 ; k < input.length - 1; k++) {
			int[] dp = new int[input.length];
			int j = k;
			for (int i = k+1; i < input.length; i++) {
				while (j > k && input[i] != input[j]) j = dp[j-1] + k;
				if (input[i] == input[j]) dp[i] = ++j - k;
				answer = Math.max(answer, dp[i]);
			}
		}
		
		System.out.println(answer);
		sc.close();
	}
}