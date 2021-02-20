public class HanoiTower {
    // 分治算法-汉诺塔
    public static void dac(int n, char a, char b, char c) {
        // 第一种情况：n=1，从a移动到c
        if (n == 1) {
            System.out.println("第" + n + "数：" + a + "->" + c);
            // 第二种情况：n>=2，拆分n为n-1部分和n部分
        } else {
            // n-1部分从a借助c移动到b
            dac(n - 1, a, c, b);
            // n从a直接移动到c
            System.out.println("第" + n + "数：" + a + "->" + c);
            // n-1部分从b借助a移动到c
            dac(n - 1, b, a, c);
        }
    }

    // 测试
    public static void main(String[] args) {
        dac(4, 'A', 'B', 'C');
    }
}
