package com.designpattern.factory;

import com.designpattern.factory.hero.CamilleImpl;
import com.designpattern.factory.hero.DianaImpl;
import com.designpattern.factory.hero.IreliaImpl;

public class HeroFactory {

    public static ISkill getHero(String name) {
        ISkill skillImpl = null;
        switch (name) {
            case "Diana":
                skillImpl = new DianaImpl();
                break;
            case "Irelia":
                skillImpl = new IreliaImpl();
                break;
            case "Camille":
                skillImpl = new CamilleImpl();
                break;
        }
        return skillImpl;
    }

}
