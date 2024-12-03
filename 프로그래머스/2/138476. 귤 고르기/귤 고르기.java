import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Integer> cntForSize = new HashMap<>();
        for (int size : tangerine) {
            cntForSize.put(size, cntForSize.getOrDefault(size, 0) + 1);
        }
        
        List<Integer> counts = new ArrayList<>(cntForSize.values());
        counts.sort(Collections.reverseOrder());
        
        for (int cnt : counts) {
            k -= cnt;
            answer++;
            if (k <= 0) break;
        }
        
        return answer;
    }
}