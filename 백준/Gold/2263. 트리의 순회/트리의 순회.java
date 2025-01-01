import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static int[] inOrder;
    static int[] inOrderPrint;
    static int[] postOrderPrint;
    static int n;
    static boolean[] visited;
    static int idx;
    static Node[] tree;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        inOrder = new int[n+1];     // 각 숫자의 인오더 출력 순서
        inOrderPrint = new int[n+1];      // 인오더 출력 내용
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            inOrderPrint[i] = num;
            inOrder[num] = i;
        }
        postOrderPrint = new int[n+1];     // 포스트오더 출력 내용
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            postOrderPrint[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[n+1];     // 해당 숫자의 노드 방문여부
        tree = new Node[n+1];
        for (int i = 0; i <= n; i++) tree[i] = new Node(i);
        idx = n;
        Node root = tree[postOrderPrint[idx--]];
        makeTree(root);

        printInOrder(root);
		bw.flush();
		bw.close();
		br.close();
	}

    private static void printInOrder(Node root) throws IOException {
        bw.write(String.valueOf(root.num) + " ");
        if (root.left > 0) printInOrder(tree[root.left]);
        if (root.right > 0) printInOrder(tree[root.right]);
    }

    private static void makeTree(Node curr) {
        int order = inOrder[curr.num];

        visited[curr.num] = true;

        if (order+1 <= n && !visited[inOrderPrint[order+1]]) {
            curr.right = postOrderPrint[idx--];
            makeTree(tree[curr.right]);
        }
        if (order-1 > 0 && !visited[inOrderPrint[order-1]]) {
            curr.left = postOrderPrint[idx--];
            makeTree(tree[curr.left]);
        }
    }
}

class Node {
    int num;
    int left;
    int right;

    Node(int num) {
        this.num = num;
        this.left = -1;
        this.right = -1;
    }
}