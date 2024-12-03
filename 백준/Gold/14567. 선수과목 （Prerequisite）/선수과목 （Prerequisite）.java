import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();	// 과목 수
		int M = sc.nextInt();	// 조건 수
		
		List<Integer>[] adj = new List[N+1];
		int[] condition = new int[N+1];	// 과목별 진입차수
		int[] answer = new int[N+1];		// 수강하는데 걸리는 총 학기
		boolean[] done = new boolean[N+1];	// 이수여부
		
		// 인접리스트 초기화
		for(int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			int before = sc.nextInt();
			int after = sc.nextInt();
			
			// 선수과목 수강시 다음강의 진입차수를 줄일 수 있도록 인접리스트 추가
			adj[before].add(after);
			
			// 후순위 과목 진입차수 증가
			condition[after]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		int season = 1;
		
		// 진입차수가 0인 과목 queue에 추가
		for (int i = 1; i <= N; i++) {
			if (condition[i] == 0) {
				queue.add(i);
				answer[i] = season;
			}
		}
		
		
		// queue가 빌 때까지 반복
		while (!queue.isEmpty()) {
			
			season++;
			
			// 현재 큐사이즈만큼 카운트 및 반복
			int cnt = queue.size();
			
			
			for (int i = 0; i < cnt; i++) {
				
				// 들을 수 있는 과목(queue) 하나 뽑음
				int subject = queue.poll();
				done[subject] = true;
				
				for (int next : adj[subject]) {
					if (!done[next]) {
						condition[next]--;
					}
				}
			}
			
			for (int i = 1; i <= N; i++) {
				if (!done[i] && condition[i] == 0) {
					queue.add(i);
					answer[i] = season;
				}
			}
			
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.print(answer[i] + " ");
		}
		
		sc.close();
	}
}