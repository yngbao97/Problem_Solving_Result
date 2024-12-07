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

        int first = 0;
        int second = 1;

        for (int i = 2; i <= n; i++) {
            int tmp = first;
            first = second;
            second = tmp + first;
        }

        if (n == 0) bw.write(String.valueOf(first));
        else bw.write(String.valueOf(second));

		bw.flush();
		bw.close();
		br.close();
	}
}