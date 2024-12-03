import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		if (N < 4) sb.append(-1);
		else if (N % 2 == 0){
			sb.append(N/2 + "\n");
			for (int i = 1; i < N/2; i++) {
				sb.append(i + "\n");
			}
			for (int i = N/2; i < N-1; i++) {
				sb.append(i+2 + "\n");
			}
			sb.append(N/2 + 1 + "\n");

		} else {
			sb.append(N/2 + 1 + "\n");
			for (int i = 1; i <= N/2; i++) {
				sb.append(i + "\n");
			}
			sb.append(N + "\n");
			for (int i = N/2 + 2; i < N; i++) {
				sb.append(i + "\n");
			}
		}

		System.out.println(sb.toString());
		sc.close();
	}
}