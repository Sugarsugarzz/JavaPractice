package MultiThread.demo04_lambda;

/*
前提：有函数式接口，才能用Lambda表达式

推导Lambda表达式
    一步步简化的过程
 */
public class TestLambda {

    // 3、静态内部类
    static class Like2 implements ILike {
        @Override
        public void lambda() {
            System.out.println("I Like Lambda2");
        }
    }

    public static void main(String[] args) {
        // 通常，一个接口，一个实现类
        ILike like = new Like();
        like.lambda();

        like = new Like2();
        like.lambda();

        // 4、局部内部类
        class Like3 implements ILike {
            @Override
            public void lambda() {
                System.out.println("I Like Lambda3");
            }
        }

        like = new Like3();
        like.lambda();

        // 5、匿名内部类，没有类的名称，必须借助接口或者父类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("I Like Lambda4");
            }
        };
        like.lambda();

        // 6、用lambda简化，只需要关注方法的实现即可
        // 原来需要一个实现类，去实现接口的方法，在调用
        like = () -> {
            System.out.println("I Like Lambda5");
        };
        like.lambda();
    }
}

// 1、定义一个函数式接口
interface ILike {
    void lambda();
}

// 2、实现类
class Like implements ILike {

    @Override
    public void lambda() {
        System.out.println("I Like Lambda");
    }
}