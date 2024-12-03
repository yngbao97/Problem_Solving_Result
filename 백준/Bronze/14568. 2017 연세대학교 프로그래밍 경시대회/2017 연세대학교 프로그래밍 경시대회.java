import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new InputStreamReader(new FileInputStream("src/"+Main.class.getPackage().getName()+"/input.txt")));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		
		int answer = 0;
		
		for (int a = 1; N - a > 4; a++) {
			int b;
			if (N - a % 2 == 0) b = a + 3;
			else b = a + 2;
			
			while (N - a - b > 1) {
				answer++;
				b += 2;
			}
		}
		
		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();

	}
}