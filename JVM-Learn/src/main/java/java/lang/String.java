package java.lang;

public class String {
    /**
     * 双亲委派机制，为了保证安全
     * 1. APP --> EXT --> BOOT（最终执行）
     * 会一层层向上找String，找到则执行；如果找不到则再返回找，BOOT->EXT->APP，找到则执行。
     */
    public String toString() {
        return "Hello";
    }

    public static void main(String[] args) {
        String s = new String();
        System.out.println(s.toString());  // 错误: 在类 java.lang.String 中找不到 main 方法
    }
}
