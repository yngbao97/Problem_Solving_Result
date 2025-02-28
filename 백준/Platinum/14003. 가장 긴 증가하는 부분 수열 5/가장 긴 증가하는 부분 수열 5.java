import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static int[] dp;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
        dp = new int[n];            // 빠른 dp 갱신용
        int[] nums = new int[n];    // 실제 순서대로 입력된 수
        int[] length = new int[n];  // 실제 순서대로 입력된 각 수까지의 증가수열 길이
        int maxIdx = 0;             // 가장 긴 증가하는 부분 수열 마지막 숫자의 실제 순서
        int dpIdx = 1;              // 지금까지의 가장 긴 증가하는 부분 수열 길이

        st = new StringTokenizer(br.readLine(), " ");
        nums[0] = Integer.parseInt(st.nextToken());
        dp[0] = nums[0];
        length[0] = 1;

        for (int i = 1; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            if (nums[i] > dp[dpIdx-1]) {
                dp[dpIdx++] = nums[i];
                length[i] = dpIdx;
                maxIdx = i;
            } else {
                length[i] = findIdx(nums[i], dpIdx-1) + 1;
                dp[length[i]-1] = nums[i];
            }
        }

//        System.out.println(Arrays.toString(length));

        bw.write(String.valueOf(dpIdx) + "\n");
        int[] answer = new int[dpIdx--];
        answer[dpIdx] = nums[maxIdx];
//        System.out.println(nums[maxIdx] + "선택");
        int cnt = dpIdx--;
        int idx = maxIdx-1;

        while (cnt > 0 && idx >= 0) {
            while (nums[idx] >= nums[maxIdx] || length[idx] != length[maxIdx] - 1) {
//                System.out.println(nums[idx]);
                idx--;
            }
//            System.out.println(nums[idx] + "선택" + length[idx]);
            answer[dpIdx--] = nums[idx];
            maxIdx = idx;
            idx--;
            cnt--;
        }

//        System.out.println(Arrays.toString(answer));
        for (int num : answer) bw.write(String.valueOf(num) + " ");
		bw.flush();
		bw.close();
		br.close();
	}

    public static int findIdx(int num, int h) {
        int low = 0;
        int high = h;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (dp[mid] > num) high = mid;
            else if (dp[mid] < num) low = mid + 1;
            else if (dp[mid] == num) return mid;
        }
        return high;
    }
}