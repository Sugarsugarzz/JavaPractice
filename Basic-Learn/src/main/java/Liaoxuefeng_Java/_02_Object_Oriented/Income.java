package Liaoxuefeng_Java._02_Object_Oriented;

public class Income {

    // 多态Demo
    protected double income;

    public Income(double income) {
        this.income = income;
    }

    public double getTax() {
        return income * 0.1;
    }
}
