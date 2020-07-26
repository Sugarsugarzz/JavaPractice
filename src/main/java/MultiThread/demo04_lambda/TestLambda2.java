package MultiThread.demo04_lambda;

// 带参数的Lambda表达式例子
public class TestLambda2 {
    public static void main(String[] args) {

        // 匿名内部类
        ILove love = new ILove() {
            @Override
            public void love(int a) {
                System.out.println("Love " + a);
            }
        };

        // Lambda
        love = (int a) -> {
                System.out.println("Love " + a);
        };
        love.love(520);

        // 简化 1.参数类型
        love = (a) -> {
            System.out.println("Love " + a);
        };
        love.love(520);

        // 简化 2.简化括号（多参数不能简化）
        love = a -> {
            System.out.println("Love " + a);
        };
        love.love(520);

        // 简化 3.简化花括号（多行不能简化）
        love = a ->
            System.out.println("Love " + a);
        love.love(520);

        /*
        总结：
            1、lambda表达式只能有一行代码的情况下才能简化称为一行，简化花括号，如果有多行，那么就用代码块包裹。
            2、前提是接口为函数式接口（接口只有一个方法）。
            3、多个参数也可以去掉参数类型，要去掉就都去掉。多参数括号不能简化。
         */
    }
}

interface ILove {
    void love(int a);
}

//class Love implements ILove {
//    @Override
//    public void love(int a) {
//        System.out.println("Love " + a);
//    }
//}