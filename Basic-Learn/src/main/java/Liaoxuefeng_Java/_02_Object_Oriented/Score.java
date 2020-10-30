package Liaoxuefeng_Java._02_Object_Oriented;

import java.util.Arrays;

public class Score {

    private int[] scores;

    public Score(int[] scores) {
        // 这里Score内部直接引用了外部传入的int[]数组，会造成外部代码对数组的修改，影响到Score类的字段。
        // this.scores = scores;

        this.scores = scores.clone();
    }

    public void printScores() {
        System.out.println(Arrays.toString(scores));
    }
}
