package com.my.librery;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

public class Arena extends Observable {

    private LinkedList<Fighter> linkedListFighter;

    private Fighter fighterOne;
    private Fighter fighterTwo;

    private int bumpOne;
    private int bumpTwo;

    public LinkedList<Fighter> getLinkedListFighter() {
        return linkedListFighter;
    }

    public void setLinkedListFighter(LinkedList<Fighter> linkedListFighter) {
        this.linkedListFighter = linkedListFighter;
    }

    public synchronized void notifyDisplay() {
        setChanged();
        notifyObservers();
    }

    public void battle() {
        fighterOne = linkedListFighter.get(0);
        fighterTwo = linkedListFighter.get(1);

        bumpOne = fighterOne.bump();
        bumpTwo = fighterTwo.bump();

        deadHeat();

        System.out.println("Боец №" + fighterOne.getNumber() + "(сила - " + bumpOne +
                ") ударил бойца №" + fighterTwo.getNumber() + " (сила - " + bumpTwo + ")");

        linkedListFighter.remove(fighterOne);
        linkedListFighter.remove(fighterTwo);

        if (bumpOne > bumpTwo) {
            linkedListFighter.addLast(fighterOne);
        } else if (bumpOne < bumpTwo) {
            linkedListFighter.addLast(fighterTwo);
        }
    }

    public void deadHeat() {
        Random random = new Random();
        if (bumpOne == bumpTwo) {
            if (random.nextBoolean()) {
                bumpTwo += random.nextInt(30) + 1;
                fighterTwo.setEndurance(bumpTwo);
            } else {
                bumpOne += random.nextInt(30) + 1;
                fighterOne.setEndurance(bumpOne);
            }
        }
    }
}

