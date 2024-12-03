import java.util.Scanner;

public class Main {
	
	static int inning;
	static int answer;
	static int[][] runner;
	static int[] order;
	static boolean[] visited;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		inning = sc.nextInt();
		runner = new int[inning][9];	// 선수번호때문에 패딩하려다가, %연산 고려해서 포기
		answer = 0;
		
		for (int i = 0; i < inning; i++) {
			for (int r = 0; r < 9; r++) {
				runner[i][r] = sc.nextInt();
			}
		}
		
		// 선수번호, 타자번호 -1씩 당겨서 생각
		order = new int[9];				// 타자 순서, 선수번호로 표시
		visited = new boolean[9];		// 선수의 순서확인 여부
		order[3] = 0;					// 3번 타자, 0번 선수로 정함
		visited[0] = true;				// 0번 선수 순서 정해짐
		perm(0);

		System.out.println(answer);
		sc.close();
	}
	
	private static void perm(int cnt) {
		if (cnt >= 9) {

			answer = Math.max(answer, play());
			return;
		}
		
		// 3번 타자는 정했으니까 패스
		if (cnt == 3) perm(cnt+1);
		
		else {
			//모든 선수를 돌면서(0번선수는 정했으니까 제외)
			for (int i = 1; i < 9; i++) {
				if (!visited[i]) {
					order[cnt] = i;
					visited[i] = true;
					perm(cnt+1);
					visited[i] = false;
				}
			}
		}
	}

	private static int play() {
		int result = 0;
		int cnt = 0;	// 회차
		int number = 0;	// 타자순서번호
		
		while (cnt < inning) {
			int[] ground = new int[5];
			
			while (ground[0] < 3) {
				// 이번 회차에 몇번째 타자의 예상 타점
				int score = runner[cnt][order[number++]];
				
				if (score == 0) ground[0]++;
				else if(score == 4) {
					for (int i = 1; i < 4; i++) {
						if (ground[i] == 1) {
							ground[i] = 0;
							ground[4]++;
						}
					}
					ground[4]++;
				} else {
					int start = 4 - score;
					for (int i = start; i < 4; i++) {
						if (ground[i] == 1) {
							ground[i] = 0;
							ground[4]++;
						}
					}
					
					// 앞 인덱스부터 검사하면 바뀐값이 뒤에 적용되서, 거꾸로 확인
					for (int i = start-1; i > 0; i--) {
						if (ground[i] == 1) {
							ground[i] = 0;
							ground[i+score] = 1;
						}
					}
					
					// 타자 진출
					ground[score] = 1;
				}
				
				// 선수 순서 로테이션
				number %= 9;
			}
			
			// 아웃 3개 되면 회차 증가
			cnt++;
			result += ground[4];
		}
		
		return result;
	}

}