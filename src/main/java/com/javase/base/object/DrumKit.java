package com.javase.base.object;

/**
 * @author miclefengzss
 * @create 2021-02-24 下午2:45
 */

public class DrumKit {

    boolean topHat = true;
    boolean snare = true;

    void playTopHat() {
        System.out.println("bang band ba-bang");
    }

    void playSnare() {
        System.out.println("ding ding da-ding");
    }
}

class DrumKitTestDriver {

    public static void main(String[] args) {
        DrumKit d = new DrumKit();
        d.playTopHat();
        if (d.snare) {
            d.playSnare();
        }
        d.snare = false;
    }
}
