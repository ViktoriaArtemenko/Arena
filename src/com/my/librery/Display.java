package com.my.librery;

import java.util.Observable;
import java.util.Observer;

public class Display implements Observer {

    private Arena arena;

    public Display(Arena arena) {
        this.arena = arena;
        arena.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        Fighter winner = arena.getLinkedListFighter().getLast();
        int sizeOfList = arena.getLinkedListFighter().size();

        System.out.println("Победил боец №" + winner.getNumber());

        System.out.println("*******************");

        if (sizeOfList == 1) System.out.println("Победитель: боец №" + winner.getNumber());
    }
}