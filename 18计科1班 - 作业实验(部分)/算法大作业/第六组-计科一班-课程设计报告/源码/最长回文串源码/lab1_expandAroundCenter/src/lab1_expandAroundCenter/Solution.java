package lab1_expandAroundCenter;

public class Solution {

    // ������ɢ��

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int start = 0;
        // ����λ��ö�ٵ� len - 2 ����
        for (int i = 0; i < len - 1; i++) {
            int oddLen = expandAroundCenter(s, i, i);
            // System.out.println("oddLen��" + oddLen);
            int evenLen = expandAroundCenter(s, i, i + 1);
            // System.out.println("evenLen��" + evenLen);

            int curMaxLen = Math.max(oddLen, evenLen);
            if (curMaxLen > maxLen) {
                maxLen = curMaxLen;
                start = i - (maxLen - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    /**
     * ���Ĵ��ĳ���
     * @param s
     * @param left
     * @param right
     * @return
     */
    private int expandAroundCenter(String s, int left, int right) {
        // left = right ��ʱ�򣬴�ʱ����������һ���ַ������Ĵ��ĳ���������
        // right = left + 1 ��ʱ�򣬴�ʱ�������������ַ������Ĵ��ĳ�����ż��
        int len = s.length();
        int i = left;
        int j = right;
        while (i >= 0 && j < len) {
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            } else {
                break;
            }
        }
        // ����ҪС�ģ����� while ѭ��ʱ��ǡ������ s.charAt(i) != s.charAt(j)��
        // ��ʱ���Ĵ��ĳ����� j - i
        return j - i - 1;
    }
}
