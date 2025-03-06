import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int n = points.length;      // 포인트의 개수 (2~100)
        int x = routes.length;      // 로봇의 개수 (2~100)
        int m = routes[0].length;   // 운송루트 길이(2~100), 모든 로봇 동일
        boolean[] end = new boolean[x];
        int[] nextOrder = new int[x];    // 다음 이동 위치(운송루트의 인덱스)
        Arrays.fill(nextOrder, 1);
        
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] next = new int[x][2];   // 로봇별 이동 위치 저장
        int[][] is = new int[101][101];
        for (int i = 0; i < x; i++) {
            // i번째 로봇이 처음 위치해 있는 포인트 좌표
            int[] curr = points[routes[i][0] - 1];
            queue.add(new int[] {curr[0], curr[1]});
            next[i][0] = curr[0];
            next[i][1] = curr[1];
            if (++is[curr[0]][curr[1]] == 2) answer++;
            // System.out.println((i+1) + "번 로봇 초기 위치: " + Arrays.toString(curr));
        }
        
        // 더 이동할 로봇이 없을 때까지
        while(!queue.isEmpty()) {

            // 모든 로봇의 다음 위치 확인
            for (int i = 0; i < x; i++) {
                // 이동할 루트가 남은 로봇만
                if (!end[i]) {
                    int[] curr = queue.poll();
                    // // i번 로봇의 다음 목적지 좌표
                    int[] p = points[routes[i][nextOrder[i]] - 1];
                    
                    // 이동 위치 설정
                    if (curr[0] != p[0]) {
                        if (curr[0] < p[0]) next[i][0]++;
                        else next[i][0]--;
                    } else if (curr[1] != p[1]) {
                        if (curr[1] < p[1]) next[i][1]++;
                        else next[i][1]--;
                    }
                    // System.out.println((i+1)+"번 로봇: ");
                    // System.out.println("다음 목적지: " + Arrays.toString(p));
                    // System.out.println(Arrays.toString(curr) + " -> " + Arrays.toString(next[i]));
                }
            }
            
            is = new int[101][101];;
            // 한번에 이동 및 중복 위치 확인
            for (int i = 0; i < x; i++) {
                if (!end[i]) {
                    int r = next[i][0];
                    int c = next[i][1];
                    // System.out.println((i+1)+"번 로봇 -> " + r + ", " + c);
                    
                    if (++is[r][c] == 2) answer++;
                    
                    int[] p = points[routes[i][nextOrder[i]] - 1];
                    // 다음 목적지에 도착했으면, 목적지 인덱스를 다음으로
                    if (r == p[0] && c == p[1]) {
                        // 다음 목적지가 없으면 end 처리
                        if (++nextOrder[i] >= m) {
                            end[i] = true;
                            continue;
                        }
                    }
                    
                    queue.add(new int[] {r, c});
                }
            }
            // for (int i = 0; i < x; i++) System.out.println(Arrays.toString(is[i]));
        }
        
        return answer;
    }
}