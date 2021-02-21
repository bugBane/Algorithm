import java.util.Arrays;
import java.util.LinkedList;

public class Dijkstra {
    private static int N = 10000;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = new int[][]{{N, 5, 7, N, N, N, 2}, {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N}, {N, 9, N, N, N, 4, N}, {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6}, {2, 3, N, N, 4, 6, N}};
        int[] flag = new int[vertex.length];
        Graph graph = new Graph(vertex.length);
        int[] dijkstra = dijkstra(graph, vertex, weight, flag, 0);
        System.out.println(Arrays.toString(dijkstra));
    }

    // 迪杰斯算法
    public static int[] dijkstra(Graph graph, char[] vertex, int[][] weight, int[] flag, int v) {
        graph.vertex = vertex;
        graph.weight = weight;
        graph.flag = flag;
        flag[v] = 0;
        int temp = v;
        // 用来广度算法的链表
        LinkedList<Integer> ch = new LinkedList<>();
        ch.addLast(v);
        while (!ch.isEmpty()) {
            temp = ch.removeFirst();
            // 寻找相交顶点
            for (int j = 0; j < weight[temp].length; j++) {
                if (j != v && weight[temp][j] != N) {
                    // 第一次发现顶点就先加入
                    if (flag[j] == 0) {
                        ch.addLast(j);
                        flag[j] = weight[temp][j] + flag[temp];
                    }
                    // 判断如果有最优的路径,就赋值
                    if (flag[j] > weight[temp][j] + flag[temp]) {
                        flag[j] = weight[temp][j] + flag[temp];
                    }
                }
            }
        }
        return flag;
    }
}

class Graph {
    // 顶点
    char[] vertex;
    // 邻接矩阵
    int[][] weight;
    // 到顶点的最小路径权值
    int[] flag;

    public Graph(int vertexSize) {
        vertex = new char[vertexSize];
        weight = new int[vertexSize][vertexSize];
        flag = new int[vertexSize];
    }
}
