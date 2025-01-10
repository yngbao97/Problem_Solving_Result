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
        String[] input = br.readLine().split(" ");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(input[i]);
        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);

        int max = copy[n-1];
        boolean[] exist = new boolean[max + 1];
        int[] score = new int[max + 1];

        for (int i = n-1; i >= 0; i--) {
            exist[copy[i]] = true;

            int mult = copy[i] + copy[i];
            while (mult <= max) {
                if (exist[mult]) {
                    score[copy[i]]++;
                    score[mult]--;
                }
                mult += copy[i];
            }
        }

        for (int i = 0; i < n; i++) bw.write(String.valueOf(score[nums[i]]) + " ");
		bw.flush();
		bw.close();
		br.close();
	}
}