package com.my.librery;

import java.util.Random;

public class Fighter extends Thread {

    private int endurance = 100;   //  выносливость
    private int strength;          //  сила
    private int dexterity;         //  ловкость
    private int intuition;         //  интуиция

    private Arena arena;
    private int number;

    public Fighter(int strength, int dexterity, int intuition, int number, Arena arena) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intuition = intuition;
        this.number = number;
        this.arena = arena;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    @Override
    public void run() {
        start();
    }

    public int getResultStrength() {
        int result = strength;
        double divider = 100.0;
        if (dexterity / divider > 0.5 && intuition / divider > 0.5) {
            result = result * 2;
        }
        return result;
    }

    public int bump() {
        synchronized (arena) {
            int resultStr = getResultStrength();
            Random random = new Random();
            if (random.nextBoolean()) endurance += resultStr;
            else endurance -= resultStr;
            arena.notify();
        }
        return endurance;
    }

    public int getNumber() {
        return number;
    }
}
