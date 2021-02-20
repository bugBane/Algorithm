import java.util.Arrays;

public class KMP {
    // 获取一个字符串的部分匹配值表
    public static int[] kmpNext(String dest) {
        // 创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度为1，部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // kmp算法的核心
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            // 匹配字符相等，部分匹配值+1
            // 如AAA 部分匹配值为2 前缀:A,AA 后缀:A,AA
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    // kmp搜索
    public static int kmpSearch(String s1, String s2, int[] next) {
        for (int i = 0, j = 0; i < s1.length(); i++) {
            // kmp核心算法
            // 匹配字符不相等
            while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
                j = next[j - 1];
            }
            // 判断字符是否相等
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            // 匹配完成返回索引
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
        int[] next = kmpNext(s2);
//        System.out.println(Arrays.toString(next));
        System.out.println(kmpSearch(s1, s2, next));
    }
}
