package Leetcode.ByTags.Math;

import java.util.*;

/**
 * 399. 除法求值
 * Medium
 */

public class _0399_calcEquation {
    public static void main(String[] args) {
       List<List<String>> equations = new ArrayList<List<String>>();
       equations.add(new ArrayList<>(Arrays.asList("a", "b")));
       double[] values = {0.5};
       List<List<String>> queries = new ArrayList<>();
       queries.add(new ArrayList<>(Arrays.asList("a", "b")));
       queries.add(new ArrayList<>(Arrays.asList("b", "a")));
       queries.add(new ArrayList<>(Arrays.asList("a", "c")));
       queries.add(new ArrayList<>(Arrays.asList("x", "y")));

       System.out.println(Arrays.toString(calcEquation(equations, values, queries)));
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        // 一、BFS
        // 二、DFS
        // 三、Floyd
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        for (List<String> equation : equations) {
            for (String e : equation) {
                if (!map.containsKey(e)) {
                    map.put(e, count++);
                }
            }
        }

        double[][] graph = new double[count][count];

        int index = 0;
        for (List<String> equation : equations) {
            int x1 = map.get(equation.get(0));
            int x2 = map.get(equation.get(1));
            graph[x1][x1] = 1.0;
            graph[x2][x2] = 1.0;
            graph[x1][x2] = values[index];
            graph[x2][x1] = 1.0 / values[index++];
        }

        int n = count;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k && graph[j][k] > 0)
                        continue;
                    if (graph[j][i] > 0 && graph[i][k] > 0) {
                        graph[j][k] = graph[j][i] * graph[i][k];
                    }
                }
            }
        }

        System.out.println(Arrays.deepToString(graph));
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if (map.containsKey(a) && map.containsKey(b)) {
                ans[i] = graph[map.get(a)][map.get(b)] == 0 ? -1.0 : graph[map.get(a)][map.get(b)];
            } else {
                ans[i] = -1.0;
            }
        }

        return ans;
        // 四、并查集
    }
}
