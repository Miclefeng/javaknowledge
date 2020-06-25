package com.basic.knowledge.designpattern.factory;

import com.basic.knowledge.designpattern.factory.hero.CamilleImpl;
import com.basic.knowledge.designpattern.factory.hero.DianaImpl;
import com.basic.knowledge.designpattern.factory.hero.IreliaImpl;

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
