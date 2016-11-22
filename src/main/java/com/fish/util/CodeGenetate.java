package com.fish.util;

import java.util.Random;

/**
 * Created by thy on 16-11-18.
 */

public class CodeGenetate {

//    private static CodeGenetate instance = null;
    private CodeGenetate() {
    }

//    private static synchronized void syncInit() {
//        if (instance == null) {
//            instance = new CodeGenetate();
//        }
//    }
    private static class InstanceHolder{
        private static final CodeGenetate instance = new CodeGenetate();
    }

    public static CodeGenetate getInstance() {
//        if (instance == null) {
//            syncInit();
//        }
        return InstanceHolder.instance;
    }

    public String create() {

        Random random = new Random();

        int code = random.nextInt(100000) % 100000 + (random.nextInt(9) + 1) * 100000;

        return ""+code;

    }
}
