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

		char[] cal = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (char c : cal) {
            if (c == '+' || c == '-') {
                while (!stack.isEmpty() && stack.peek() != '(') sb.append(stack.pop());
                stack.push(c);
            }
            else if (c == '*' || c == '/') {
                while (!stack.isEmpty() && stack.peek() != '(' && stack.peek() != '+' && stack.peek() != '-')
                    sb.append(stack.pop());
                stack.push(c);
            }
            else if (c == '(') stack.push(c);
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') sb.append(stack.pop());
                stack.pop();
            } else sb.append(c);
        }

        while (!stack.isEmpty()) sb.append(stack.pop());
        bw.write(sb.toString());

		bw.flush();
		bw.close();
		br.close();
	}
}