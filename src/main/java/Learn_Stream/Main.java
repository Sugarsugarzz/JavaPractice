package Learn_Stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java8新特性：流Stream，以声明的方式处理数据
 * 将要处理的元素集合看作一种流，流在管道中传输，并且可以在管道的节点上进行处理，比如筛选，排序，聚合等。
 *
 * | stream | -> filter -> sorted -> map -> collect
 */
public class Main {

    public static void main(String[] args) {

        /*
        * 生成流
        * stream() - 串行流
        * parallelStream() - 并行流
        */

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abc", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered);

        /* forEach：迭代流中的每个数据 */
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        /* map：映射每个元素到对应的结果（可去重） */
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取平方数
        List<Integer> squaresList = numbers.stream().map(i -> i*i).distinct().collect(Collectors.toList());
        System.out.println(squaresList);

        /* filter：设置条件过滤出元素 */
        // 获取空字符串的数量
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);

        /* limit：获取指定数量的流 */
        List<String> collect = strings.stream().limit(3).collect(Collectors.toList());
        System.out.println(collect);

        /* sorted：对流进行排序 */
        random.ints().limit(10).sorted().forEach(System.out::println);

        /* parallelStream：并行处理程序的代替方法 */
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count);

        /* Collectors：归约操作，例如将流转换成集合和聚合元素，Collectors可用于返回列表或字符串 */
        filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());;
        System.out.println("筛选列表：" + filtered);
        String collect1 = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println("合并列表：" + collect1);

        /* 统计 */
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数：" + stats.getMax());
        System.out.println("列表中最小的数：" + stats.getMin());
        System.out.println("所有数之和：" + stats.getSum());
        System.out.println("平均数：" + stats.getAverage());
        System.out.println("数量：" + stats.getCount());

    }
}
