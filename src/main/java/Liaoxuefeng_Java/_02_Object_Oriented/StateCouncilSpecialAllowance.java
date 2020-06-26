package Liaoxuefeng_Java._02_Object_Oriented;

public class StateCouncilSpecialAllowance extends Income {

    public StateCouncilSpecialAllowance(double income) {
        super(income);
    }

    // 多态Demo
    // 国务院津贴，免税
    @Override
    public double getTax() {
        return 0;
    }
}
