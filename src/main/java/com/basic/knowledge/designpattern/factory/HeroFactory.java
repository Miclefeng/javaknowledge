package com.basic.knowledge.designpattern.factory;

import com.basic.knowledge.designpattern.factory.hero.Camille;
import com.basic.knowledge.designpattern.factory.hero.Diana;
import com.basic.knowledge.designpattern.factory.hero.Irelia;

public class HeroFactory {

    public static SkillImpl getHero(String name) {
        SkillImpl skillImpl = null;
        switch (name) {
            case "Diana":
                skillImpl = new Diana();
                break;
            case "Irelia":
                skillImpl = new Irelia();
                break;
            case "Camille":
                skillImpl = new Camille();
                break;
        }
        return skillImpl;
    }

}
