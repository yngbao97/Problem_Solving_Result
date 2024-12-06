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

        long p = 1_500_000;
        long n = Long.parseLong(br.readLine());

        long answer = fibo(n % p);

        bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

    private static long fibo(long n) {

        long first = 1;
        long second = 1;

        for (int i = 3; i <= n; i++) {
            long tmp = first;
            first = second;
            second = (tmp + first) % 1000000;
        }

        return second;
    }
}