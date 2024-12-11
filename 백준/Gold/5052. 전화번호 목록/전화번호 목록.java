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
		
		int t = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < t; tc++) {

			int n = Integer.parseInt(br.readLine());
			char[][] input = new char[n][];
			for (int i = 0; i < n; i++) {
				input[i] = br.readLine().toCharArray();
			}

			boolean correct = true;
			Node root = new Node('r');

			here: for (char[] str : input) {

				Node curr = root;
				for (int j = 0; j < str.length; j++) {
					char c = str[j];
					Node next = curr.next.getOrDefault(c, null);
					if (next == null) {
						next = new Node(c);
						curr.next.put(c, next);
					} else {
						if (j == str.length-1) {
							correct = false;
							break here;
						}
						if (next.end) {
							correct = false;
							break here;
						}
					}
					curr = next;
					if (j == str.length-1) curr.end = true;
				}
			}

			if (correct) bw.write("YES\n");
			else bw.write("NO\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}

class Node {
	char c;
	boolean end;
	Map<Character, Node> next;

	Node (char c) {
		this.c = c;
		this.end = false;
		next = new HashMap<>();
	}
}