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

		String[] input = br.readLine().split(" ");
		
		int A = Integer.parseInt(input[0]);
		int B = Integer.parseInt(input[1]);
		
		int answer = 1;
		
		while (A != B) {
			
			if (A > B) {
				answer = -1;
				break;
			}
			
			if (B % 2 == 0) B /= 2;
			else if (B % 10 == 1) B /= 10;
			else {
				answer = -1;
				break;
			};
			
			answer++;
		}
		
		bw.append(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();

	}
}