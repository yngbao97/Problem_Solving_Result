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

		String first = "koosaga";
		String second = "cubelover";
		br.readLine();
		
		st = new StringTokenizer(br.readLine(), " ");
		int result = 0;
		
		while (st.hasMoreTokens()) {
			result ^= Integer.parseInt(st.nextToken());
		}
		
		if (result == 0) bw.write(second);
		else bw.write(first);

		bw.flush();
		bw.close();
		br.close();
	}
}