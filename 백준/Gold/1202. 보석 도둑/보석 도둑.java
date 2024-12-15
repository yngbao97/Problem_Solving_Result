import java.lang.reflect.Array;
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
		
		String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);     // 보석의 개수
        int k = Integer.parseInt(input[1]);     // 가방의 개수

        Jewelry[] jewelries = new Jewelry[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewelries[i] = new Jewelry(weight, value);
        }

        int[] bags = new int[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewelries, (o1, o2) -> {
            return o1.weight - o2.weight;
        });
        Arrays.sort(bags);

        PriorityQueue<Jewelry> candidate = new PriorityQueue<>((o1, o2) -> {
            return o2.value - o1.value;
        });
        int jIdx = 0;
        long answer = 0L;
        for (int w : bags) {
            while (jIdx < n && jewelries[jIdx].weight <= w) candidate.add(jewelries[jIdx++]);
            if (candidate.isEmpty()) continue;
            int value = candidate.poll().value;
            answer += value;
        }

        bw.write(String.valueOf(answer));
		
		bw.flush();
		bw.close();
		br.close();
	}
}

class Jewelry {
    int weight;
    int value;

    Jewelry (int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}