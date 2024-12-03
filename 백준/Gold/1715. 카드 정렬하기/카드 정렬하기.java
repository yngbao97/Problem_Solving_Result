import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Long> cards = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			cards.add(Long.parseLong(br.readLine()));
		}
		
		Long answer = 0L;
		while (cards.size() > 1) {
			Long sum = cards.poll() + cards.poll();
			cards.add(sum);
			answer += sum;
		}
		
		System.out.println(answer);
		br.close();
	}
}
