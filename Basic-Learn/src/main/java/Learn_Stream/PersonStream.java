package Learn_Stream;

import Liaoxuefeng_Java._02_Object_Oriented.Person2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonStream {
    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("小王", 18, "中国", 'F'));
        personList.add(new Person("小李", 12, "美国", 'M'));
        personList.add(new Person("小白", 33, "美国", 'M'));
        personList.add(new Person("小吴", 20, "英国", 'F'));
        personList.add(new Person("小张", 18, "中国", 'M'));
        personList.add(new Person("小黑", 8, "中国", 'F'));
        personList.add(new Person("小绿", 33, "中国", 'F'));
        personList.add(new Person("小绿", 33, "中国", 'F'));

        // 找出年龄大于18的人并输出
        personList.stream().filter(p -> p.getAge() > 18).forEach(System.out::println);

        // 计算所有中国人的数量
        long chineseNum = personList.stream().filter(p -> p.getCountry().equals("中国")).count();
        System.out.println(chineseNum);

        /*
        中间操作  -  筛选与切片
        - filter：接收lambda，从流中排除某些操作
        - limit：截断流，使其元素不超过给定对象
        - skip(n)：跳过元素，返回一个扔掉前n个元素的流，不足n个返回空流，与limit(n)互补
        - distinct：筛选，底层通过hashCode()和equals()去除重复元素
         */

        // limit - 取出两个女性
        personList.stream().filter(p -> p.getGender() == 'F').limit(2).forEach(System.out::println);

        // skip - 从第2个女性开始，取出所有女性
        personList.stream().filter(p -> p.getGender() == 'F').skip(2).forEach(System.out::println);

        // distinct - 女性去重
        personList.stream().filter(p -> p.getGender() == 'F').distinct().forEach(System.out::println);

        /*
        中间操作 - 映射
        - map：接收lambda，将元素转换成其他形式或提取信息
        - flatMap：接收一个函数作为参数，将流中的每个值换成另一个流，然后把所有流连接成一个流
         */

        // map - 取出所有城市
        personList.stream().map(Person::getCountry).distinct().forEach(System.out::println);

        /*
        中间操作 - 排序
        - sorted() 自然排序
        - sorted(Comparator com) 定制排序
         */

        personList.stream().sorted(
            (p1, p2) -> {
            if (p1.getAge().equals(p2.getAge())) {
                return p1.getName().compareTo(p2.getName());
            } else {
                return p1.getAge().compareTo(p2.getAge());
            }
        }).forEach(System.out::println);


        /*
        终止操作 - 查询与匹配
        allMatch：检查是否匹配所有元素
        anyMatch：检查是否至少匹配一个元素
        noneMatch：检查是否没有匹配所有元素
        findFirst：返回第一个元素
        findAny：返回任意元素
        count：返回流中元素的总个数
        max：返回最大值
        min：返回最小值
         */

        // allMatch - 是否都是中国人
        boolean chinese = personList.stream().allMatch(p -> p.getCountry().equals("中国"));
        System.out.println(chinese);

        // max min - 找出年龄最小的人信息
        Person person = personList.stream().min((p1, p2) -> p1.getAge().compareTo(p2.getAge())).get();
        System.out.println(person);

        /*
        归约：将元素反腐结合起来
        reduce
         */

        // reduce - 求所有人的年龄之和
        Optional<Integer> reduce = personList.stream().map(Person::getAge).reduce(Integer::sum);
        System.out.println(reduce);

        /*
        收集
        collect：将流转换为其他形式
         */
        List<String> countries = personList.stream().map(p -> p.getCountry()).distinct().collect(Collectors.toList());
        System.out.println(countries);
    }
}
