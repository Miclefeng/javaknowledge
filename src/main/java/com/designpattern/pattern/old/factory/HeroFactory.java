package com.designpattern.pattern.old.factory;

import com.designpattern.pattern.old.factory.hero.CamilleImpl;
import com.designpattern.pattern.old.factory.hero.DianaImpl;
import com.designpattern.pattern.old.factory.hero.IreliaImpl;

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
            default:
                break;
        }
        return skillImpl;
    }

}
