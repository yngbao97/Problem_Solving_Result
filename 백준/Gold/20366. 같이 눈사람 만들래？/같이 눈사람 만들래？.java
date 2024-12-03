import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	
	static boolean[] elsa;
	static int answer;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] balls = new int[N];
		
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			balls[i] = Integer.parseInt(input[i]);
		}
		
		List<Snowman> snowman = new ArrayList<>();
		
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				snowman.add(new Snowman(i, j, balls[i]+balls[j]));
			}
		}
		
		Collections.sort(snowman);
//		System.out.println(snowman);
		answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < snowman.size()-1; i++) {
			Snowman curr = snowman.get(i);
			int next = i+1;
			while (next < snowman.size() &&
					(curr.idx1 == snowman.get(next).idx1 ||
					curr.idx1 == snowman.get(next).idx2 ||
					curr.idx2 == snowman.get(next).idx1 ||
					curr.idx2 == snowman.get(next).idx2)) {
				next++;
			}
			
			if (next < snowman.size()) {
//				System.out.println("i = "+ i);
//				System.out.println("next = " + next);
//				System.out.printf("%d - %d = %d\n",snowman.get(next).tall, curr.tall, snowman.get(next).tall - curr.tall);
				answer = Math.min(answer, snowman.get(next).tall - curr.tall);
			}
		}
		
		System.out.println(answer);
		br.close();
	}
}

class Snowman implements Comparable<Snowman>{
	int idx1;
	int idx2;
	int tall;
	
	Snowman(int idx1, int idx2, int tall) {
		this.idx1 = idx1;
		this.idx2= idx2;
		this.tall = tall;
	}

	@Override
	public String toString() {
		return "Snowman [idx1=" + idx1 + ", idx2=" + idx2 + ", tall=" + tall + "]";
	}



	@Override
	public int compareTo(Snowman o) {
		return Integer.compare(this.tall, o.tall);
	}
}