import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] incre = new int[N];
		int[] decre = new int[N];
		
		String[] tmp = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tmp[i]);
			incre[i] = 1;
			
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					incre[i] = Math.max(incre[i], incre[j]+1);
				}
			}
		}
		
		int answer = 0;
		
		for (int i = N-1; i >= 0; i--) {
			decre[i] = 1;
			
			for (int j = N-1; j > i; j--) {
				if (arr[i] > arr[j]) {
					decre[i] = Math.max(decre[i], decre[j]+1);
				}
			}
			answer = Math.max(answer, incre[i] + decre[i] - 1);
		}
		
		System.out.println(answer);
		br.close();
	}
}