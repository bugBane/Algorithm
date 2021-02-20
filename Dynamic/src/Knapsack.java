public class Knapsack {
    // 背包问题：三种物品各有不同价格和重量，在背包容有限重量的情况下求得可盛放的最大价值
    // 背包容量，所有物品的重量，所有物品的价格
    public static int dynamic(int capacity, int[] weight, int[] price) {
        // 将复杂问题拆解成小问题，归纳小问题一步步推导至大问题，拆分的问题每往前一步，可能需要使用之前问题动态结果
        // (和分治算法不一样，分治算法也是分，但是分开是独立的:汉诺塔2和64是一样的处理都是死板的，不会因为64而造成处理2步骤发生变化)
        //  物品重量\背包容量   0     1     2     3     4
        //  0                0     0     0     0     0
        //  吉他(1)           0     1500  1500  1500  1500
        //  电子琴(4)         0     1500  1500  1500  3000
        //  唢呐(3)           0     1500  1500  2000  2000+1500
        // 记录结果集：背包容量和物品重量的二维数组，结果为所盛放物品价格
        int[][] value = new int[weight.length + 1][capacity + 1];// +1的原因是空出第一行和第一列用来记录00情况
        // 用来打印当前结果怎么放的(路径)
        int[][] path = new int[weight.length + 1][capacity + 1];
        // 默认为0其实可以不用下面代码
        // 第一行
        for (int i = 0; i < value[0].length; i++) {
            value[0][i] = 0;
        }
        // 第一列
        for (int i = 0; i < value.length; i++) {
            value[i][0] = 0;
        }
        // 经过上价格结果集分析，最大价值=背包对应容量时的结果
        // 结果集是按照先放入本次物品，如果背包无法容纳就取上次结果为本次结果，否则就判断(放入本物品价值+剩余容量物品价值)和上一次物品谁大，
        // 谁大就采取这种放置方式，并记录到该背包容量时的结果
        // 1.将每次结果放入结果集
        for (int i = 1; i < value.length; i++) {
            for (int j = 1; j < value[i].length; j++) {
                // 如果 背包容量 < 物品重量
                if (j < weight[i - 1]) {
                    // 取上次物品结果为本容量结果
                    value[i][j] = value[i - 1][j];
                } else {
                    // 判断(放入本物品价值+剩余容量物品价值)和上一次物品谁大
                    int p = price[i - 1] + value[i - 1][j - weight[i - 1]];//放入本物品价值+剩余容量物品价值(注意剩余容量不能再包含当前物品，因为已经放入了)
                    if (p > value[i - 1][j]) {
                        value[i][j] = p;
                        path[i][j] = 1;
                    } else {
                        // 取i-1结果为本容量结果
                        value[i][j] = value[i - 1][j];
                    }
                }
            }
        }
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "放入背包");
                // 注意排除掉多余的数据，需要的为：第一次放入的数据
                j -= weight[i - 1];
            }
            i--;
        }
        return value[weight.length][capacity];
    }

    public static void main(String[] args) {
        // 背包容量
        int capacity = 4;
        // 物品重量和价格
        int[] weight = {1, 4, 3};
        int[] price = {1500, 3000, 2000};
        // 动态规划得到结果
        int max = dynamic(capacity, weight, price);
        System.out.println(max);
    }
}
