import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static long N;
	static long M;
	static long K;
	static Node[] leaves;
	static Node root;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		N = Long.parseLong(input[0]);
		M = Long.parseLong(input[1]);
		K = Long.parseLong(input[2]);
		leaves = new Node[(int) (N+1)];
		
		for (int i = 1; i <= N; i++) {
			long num = Long.parseLong(br.readLine());
			leaves[i] = new Node(i, i, num);
		}
		
		long idx = 1;
		out: while(true) {
			Node a = leaves[(int) idx];
			while(a.parent != null) a = a.parent;
			
//			System.out.prlongln("a:"+a.toString());
			if (a.end == N) {
				if (a.start == 1) {
					root = a;
					break out;
				}
				idx = 1;
				continue;
			}
			
			idx = a.end + 1;
			Node b = leaves[(int) idx];
			while(b.parent != null) b = b.parent;
			
//			System.out.prlongln("b:"+b.toString());
			a.parent = b.parent = new Node(a.start, b.end, a.sum + b.sum);
			a.parent.child = new Node[2];
			a.parent.child[0] = a;
			a.parent.child[1] = b;
//			System.out.prlongln("parent:"+a.parent);
			idx = b.end + 1 >= N ? 1 : b.end + 1;
		}
		
		for (long i = 0; i < M + K; i++) {
			String[] tmp = br.readLine().split(" ");
			long order = Long.parseLong(tmp[0]);
			long x = Long.parseLong(tmp[1]);
			long y = Long.parseLong(tmp[2]);
			
			if (order == 1) {
				
				Node curr = leaves[(int) x];
				long minus = curr.sum - y;
				
				curr.sum -= minus;
				while (curr.parent != null) {
					curr.parent.sum -= minus;
					curr = curr.parent;
				}
				
			} else {
				long segSum = getSum(root, x, y);
				sb.append(segSum+"\n");
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	private static long getSum(Node curr, long left, long right) {
		if (curr.start >= left && curr.end <= right) return curr.sum;
		if (curr.start > right || curr.end < left) return 0;
		
		long sum = 0;
		
		for (Node child : curr.child) {
			if (child == null) continue;
			sum += getSum(child, left, right);
		}
		
		return sum;
	}

}

class Node {
	
	long start;
	long end;
	long sum;
	Node parent;
	Node[] child;
	
	Node(long start, long end, long sum) {
		this.start = start;
		this.end = end;
		this.sum = sum;
	}
	
	@Override
	public String toString() {
		return start + "~" + end +":"+ sum;
	}
}