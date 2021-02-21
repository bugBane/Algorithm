import java.util.Arrays;

public class Prim {
    private static int N = 10000;

    // 普里姆算法
    public static int[] prim(Graph graph, char[] vertex, int[][] weight, int[] flag, int v) {
        graph.vertex = vertex;
        graph.weight = weight;
        graph.flag = flag;
        flag[v] = 0;
        // 每次顶点的最小路径
        int minPath;
        // 下一个顶点,默认v
        int temp = v;
        // 最短路径的边一共为 顶点-1
        for (int side = 1; side < vertex.length; side++) {
            minPath = N;
            // 每个走过的顶点的最短路径(包含第一个节点)
            for (int i = 0; i < weight.length; i++) {
                if (i == v || flag[i] != 0) {
                    // 找寻每个顶点相连的下一个顶点
                    for (int j = 0; j < weight[i].length; j++) {
                        // 找到没有走过顶点的最小路径
                        if (j != v && weight[i][j] < minPath && flag[j] == 0) {
                            minPath = weight[i][j];
                            temp = j;
                        }
                    }
                }
            }
            // 赋值
            if (minPath < N) {
                flag[temp] = minPath;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = new int[][]{{N, 5, 7, N, N, N, 2}, {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N}, {N, 9, N, N, N, 4, N}, {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6}, {2, 3, N, N, 4, 6, N}};
        int[] flag = new int[vertex.length];
        Graph graph = new Graph(vertex.length);
        int[] prim = prim(graph, vertex, weight, flag, 2);
        System.out.println(Arrays.toString(prim));
    }
}

class Graph {
    // 顶点
    char[] vertex;
    // 邻接矩阵
    int[][] weight;
    // 顶点经过的最小路径权值
    int[] flag;

    public Graph(int vertexSize) {
        vertex = new char[vertexSize];
        weight = new int[vertexSize][vertexSize];
        flag = new int[vertexSize];
    }
}
