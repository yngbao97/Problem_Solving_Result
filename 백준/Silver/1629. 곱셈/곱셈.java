import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static long C;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
        long A = Long.parseLong(input[0]);
        long B = Long.parseLong(input[1]);
        C = Long.parseLong(input[2]);

        long answer = pow(A, B);

        bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

    public static long pow(long num, long cnt) {
        if (cnt == 1) return num % C;

        long half = pow(num, cnt / 2);
        long doubleModuler = (half * half) % C;
        if (cnt % 2L == 0) return doubleModuler;
        else return (doubleModuler * num) % C;
    }
}