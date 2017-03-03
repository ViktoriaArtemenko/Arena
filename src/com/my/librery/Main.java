package com.my.librery;

import java.util.LinkedList;
import java.util.Random;

public class Main {

    private static final int QUANTITY_OF_FIGHTERS = 100;
    private static Random random = new Random();
    private static Fighter fighter;
    private static Arena arena;
    private static LinkedList<Fighter> linkedListFighter = new LinkedList();

    public static void main(String[] args) {
        System.out.println("Start");

        arena = new Arena();
        new Display(arena);

        for (int i = 1; i <= QUANTITY_OF_FIGHTERS; i++) {
            initFighter(i);
            linkedListFighter.add(fighter);
        }
        run();
    }

    public static void run() {
        synchronized (arena) {
            while (linkedListFighter.size() > 1) {
                try {
                    arena.wait(500);
                    arena.wait(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                arena.setLinkedListFighter(linkedListFighter);
                arena.battle();
                arena.notifyDisplay();
            }
        }
    }

    public static void initFighter(int number) {
        double sumStrengthIntuitionDexterity = 50;

        int strength = random.nextInt(100);
        int intuition = random.nextInt(100);
        int dexterity = random.nextInt(100);

        double index = sumStrengthIntuitionDexterity / (strength + intuition + dexterity);

        strength = (int) Math.round(strength * index);
        intuition = (int) Math.round(intuition * index);
        dexterity = (int) Math.round(dexterity * index);

        fighter = new Fighter(strength, intuition, dexterity, number, arena);
    }
}