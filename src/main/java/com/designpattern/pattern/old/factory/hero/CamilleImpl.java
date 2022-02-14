package com.designpattern.pattern.old.factory.hero;

import com.designpattern.pattern.old.factory.ISkill;

public class CamilleImpl implements ISkill {
    @Override
    public void q() {
        print("Q");
    }

    @Override
    public void w() {
        print("W");
    }

    @Override
    public void e() {
        print("E");
    }

    @Override
    public void r() {
        print("R");
    }

    private void print(String skill) {
        System.out.println(getClass().getSimpleName() + skill);
    }
}
