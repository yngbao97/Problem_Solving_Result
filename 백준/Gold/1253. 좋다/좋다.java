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
        int[] nums = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n-1;

            while (left < right) {
                if (left == i) left++;
                if (right == i) right--;
                if (left >= right) break;

                if (nums[left] + nums[right] > nums[i]) right--;
                else if (nums[left] + nums[right] < nums[i]) left++;
                else {
                    answer++;
                    break;
                }
            }
        }

        bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}
}