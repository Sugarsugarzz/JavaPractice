package Liaoxuefeng_Java._02_Object_Oriented;

public class Salary extends Income {

    public Salary(double income) {
        super(income);
    }

    // 多态Demo
    @Override
    public double getTax() {
        if (income <= 5000) {
            return 0;
        }
        return (income - 5000) * 0.2;
    }
}
