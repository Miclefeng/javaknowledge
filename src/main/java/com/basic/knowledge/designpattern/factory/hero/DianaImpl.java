package com.basic.knowledge.designpattern.factory.hero;

import com.basic.knowledge.designpattern.factory.ISkill;

public class DianaImpl implements ISkill {
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
