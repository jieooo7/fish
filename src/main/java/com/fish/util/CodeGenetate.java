package com.fish.util;

import java.util.Random;

/**
 * Created by thy on 16-11-18.
 */

public class CodeGenetate {

    public static String create() {

        Random random = new Random();

        int code = random.nextInt(100000) % 100000 + (random.nextInt(9) + 1) * 100000;

        return ""+code;

    }
}
