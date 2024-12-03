import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	
	static boolean[] canTeach;
	static Character[] middles;
	static int leng;
	static int selectCnt;
	static String[] words;
	static int answer;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		char[] fix = {'a', 'n', 't', 'i', 'c'};
		words = new String[N];
		
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			words[i] = tmp.substring(4, tmp.length()-4);
		}
		
		answer = 0;
		
		if (K >= 5) {
			canTeach = new boolean[26];
			Set<Character> cSet = new HashSet<>();
			
			for (String s : words) {
				for (char c : s.toCharArray()) {
					cSet.add(c);
				}
			}
			
			for (char c : fix) {
				canTeach[c - 97] = true;
				cSet.remove(c);
			}
			
			middles = cSet.toArray(new Character[0]);
			leng = middles.length;
			selectCnt = K - 5;
			
			if (selectCnt > leng) {
				answer = words.length;
			} else comb(0,0);
			
		}
		
		System.out.println(answer);
		
		br.close();
	}
	
	private static void comb(int idx, int cnt) {
		if (cnt >= selectCnt) {
			answer = Math.max(answer, check());
			return;
		}
		
		if (idx >= leng) return;
		
		canTeach[middles[idx] - 97] = true;
		comb(idx+1, cnt+1);
		canTeach[middles[idx] - 97] = false;
		comb(idx+1, cnt);
		
	}

	private static int check() {
		int result = 0;
		
		here:for (String word : words) {
			for (char c : word.toCharArray()) {
				if (!canTeach[c-97]) continue here;
			}
			result++;
		}
		
		return result;
	}
}