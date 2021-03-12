package bridge;

// 抽象的电脑类
public abstract class Computer {

    // 组合，品牌~
    protected Brand brand;

    public Computer(Brand brand) {
        this.brand = brand;
    }

    public void info() {
        // 自带品牌
        brand.info();
    }
}
