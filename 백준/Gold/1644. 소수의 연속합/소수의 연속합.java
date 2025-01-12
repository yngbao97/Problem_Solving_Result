import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static boolean[] isNotPrime = new boolean[4_000_001];
	static List<Integer> primeNum;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		primeNum = new ArrayList<>();
		findPrimeNumber();
		int n = Integer.parseInt(br.readLine());

		int left = 0;
		int right = 1;
		int sum = primeNum.get(left) + primeNum.get(right);
		int answer = 0;
		int size = primeNum.size();

		while (right < size - 1 && left < right) {
			if (sum > n) sum -= primeNum.get(left++);
			else if (sum < n && right < size - 1) sum += primeNum.get(++right);
			else {
				answer++;
				sum += primeNum.get(++right);
			}
		}

		if (!isNotPrime[n]) answer++;

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	private static void findPrimeNumber() {
		isNotPrime[1] = true;

		for (int i = 2; i <= 4_000_000; i++) {
			if (!isNotPrime[i]) {
				primeNum.add(i);
				int num = i + i;
				while (num <= 4_000_000) {
					isNotPrime[num] = true;
					num += i;
				}
			}
		}
	}
}