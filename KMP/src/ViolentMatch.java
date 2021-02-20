public class ViolentMatch {
    public static int violentMatch(String s1, String s2) {
        for (int i = 0, j = 0; i < s1.length(); i++) {
            // 暴力匹配 思考? -> 对于已经匹配上的一部分字符回溯的时候有必要再重新判断吗?
            if (s1.charAt(i) != s2.charAt(j)) {
                i = i - j + 1;
                j = 0;
            }
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            if (j == s2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    // 测试
    public static void main(String[] args) {
        String s1 = "BBC ABCAB ABCABCDABDE";
        String s2 = "ABCDABD";
        System.out.println(violentMatch(s1, s2));
    }
}
