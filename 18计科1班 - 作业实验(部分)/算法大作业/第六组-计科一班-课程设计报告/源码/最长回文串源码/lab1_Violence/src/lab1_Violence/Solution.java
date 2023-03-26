package lab1_Violence;

public class Solution {//�����ⷨ

    // �����ⷨ

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        String res = s.substring(0, 1);

        // ö�����г��ȴ��ڵ��� 2 ���Ӵ�
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && valid(s, i, j)) {
                    maxLen = j - i + 1;
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    private boolean valid(String s, int left, int right) {
        // ��֤�Ӵ� s[left, right] �Ƿ�Ϊ���Ĵ�
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
