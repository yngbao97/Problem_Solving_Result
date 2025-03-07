import java.util.*;
import java.lang.*;

class Solution {
    public String solution(long n, String[] bans) {
        
        // 각 자리까지의 최대 문자열 개수
        long[] prefixSum = new long[11];
        prefixSum[1] = 26;
        for (int i = 2; i < 11; i++) {
             prefixSum[i] = prefixSum[i-1] + (long) Math.pow(26, i);
        }
        System.out.println(Arrays.toString(prefixSum));
        
        Set<Long> rm = new HashSet<>();
        long realN = n;
        
        for (int i = 0; i < bans.length; i++) {
            char[] word = bans[i].toCharArray();
            int length = word.length;
            long idx = prefixSum[length-1];
            for (int j = 0; j < length-1; j++) {
                idx += (word[j] - 'a') * (prefixSum[length-j-1] - prefixSum[length-j-2]);
            }
            idx += (word[length-1] - 'a' + 1);
            if (realN >= idx) while (rm.contains(++realN)) rm.remove(realN);
            else rm.add(idx);
        }
        System.out.println(realN);
        
        StringBuilder sb = new StringBuilder();

        int length = 0;
        for (int i = 11; i > 0; i--) {
            if (prefixSum[i-1] < realN) {
                realN -= prefixSum[i-1];
                length = i;
                break;
            }
        }
        System.out.println(realN);
        realN--;
        for (int i = length; i > 0; i--) {
            long div = (long) Math.pow(26, i-1);
            sb.append((char)(realN / div + 96 + 1));
            realN %= div;
        }
        return sb.toString();
    }
}