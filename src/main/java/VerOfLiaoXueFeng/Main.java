package VerOfLiaoXueFeng;

import java.security.SecureRandom;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Weekday day = Weekday.MON;

        System.out.println(day.dayValue);

//        Random r = new Random();
        SecureRandom r = new SecureRandom();


        System.out.println(r.nextInt(10));
    }
}

enum Weekday {

    MON(1), TUS(2);

    public final int dayValue;

    private Weekday(int dayValue) {
        this.dayValue = dayValue;
    }
}
