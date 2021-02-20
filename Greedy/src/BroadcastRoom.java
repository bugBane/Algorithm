import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class BroadcastRoom {
    // 贪心算法在于一个字"贪",都懂的:能白嫖为什么要花钱,一个意思,算法的核心在于处理事情每一步都使用最优解,最后结果近似接近最优解
    // 当然,贪心算法也有优劣,虽然贪心算法接近最优解但不代表一定是最优解(比如广播站除了找出最好的区域可能还有其它人文因素影响)
    public static HashSet<String> greedy(Map<String, HashSet<String>> areaMap, HashSet<String> allArea) {
        HashSet<String> areas = new HashSet();
        // 最优解判断
        int maxKey;
        // 中间变量
        HashSet<String> temp = new HashSet<>();
        // 中间变量
        String tempStr = null;
        while (!allArea.isEmpty()) {
            // 重置每一次的最优解
            maxKey = 0;
            for (String s : areaMap.keySet()) {
                // 重置中间变量
                temp.clear();
                // 每一个区域
                HashSet<String> area = areaMap.get(s);
                temp.addAll(area);
                // 将allArea与temp的交集元素给temp
                temp.retainAll(allArea);
                // 贪心之处: 取交集元素最多的
                if (temp.size() > 0 && temp.size() > maxKey) {
                    // 赋值最优解广播站
                    tempStr = s;
                    // 赋值最优解广播站的个数
                    maxKey = temp.size();
                }
            }
            // 有最优解
            if (maxKey > 0) {
                // 添加最优区域
                areas.add(tempStr);
                // 删除已经加入的区域
                allArea.removeAll(areaMap.get(tempStr));
                // 删除已经加入的广播站
                areaMap.remove(tempStr);
            }
        }
        return areas;
    }

    // 测试
    public static void main(String[] args) {
        Map<String, HashSet<String>> areaMap = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("广州");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("杭州");
        hashSet2.add("西安");
        hashSet2.add("上海");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("杭州");
        hashSet3.add("深圳");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("北京");
        hashSet4.add("西安");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("青岛");
        hashSet5.add("深圳");

        areaMap.put("A1", hashSet1);
        areaMap.put("A2", hashSet2);
        areaMap.put("A3", hashSet3);
        areaMap.put("A4", hashSet4);
        areaMap.put("A5", hashSet5);

        HashSet<String> allArea = new HashSet<>();
        for (String s : areaMap.keySet()) {
            allArea.addAll(areaMap.get(s));
        }
//        System.out.println(allArea);
        HashSet<String> greedy = greedy(areaMap, allArea);
        System.out.println(greedy);
    }
}
