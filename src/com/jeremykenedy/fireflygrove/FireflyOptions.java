package com.jeremykenedy.fireflygrove;

import java.util.Random;

public final class FireflyOptions {
    public final int count;
    public final float speed;
    public final int glowColor;
    public final int habitat;

    private FireflyOptions(int count, float speed, int glowColor, int habitat) {
        this.count = count;
        this.speed = speed;
        this.glowColor = glowColor;
        this.habitat = habitat;
    }

    public static FireflyOptions resolve(String density, String motion, String color,
            String habitat, boolean randomizeAll, Random random) {
        String selectedDensity = choose(density, randomizeAll, random, "few", "handful", "many", "ton", "schools");
        String selectedMotion = choose(motion, randomizeAll, random, "slow", "natural", "quick");
        String selectedColor = choose(color, randomizeAll, random, "warm", "lime", "aqua");
        String selectedHabitat = choose(habitat, randomizeAll, random, "woodland", "meadow", "riverbank");
        return new FireflyOptions(countFor(selectedDensity), speedFor(selectedMotion),
                colorFor(selectedColor), habitatFor(selectedHabitat));
    }

    private static String choose(String selected, boolean randomizeAll, Random random, String... values) {
        if (randomizeAll || "random".equals(selected)) return values[random.nextInt(values.length)];
        for (String value : values) if (value.equals(selected)) return selected;
        return values[0];
    }

    static int countFor(String density) {
        if ("few".equals(density)) return 12;
        if ("many".equals(density)) return 38;
        if ("ton".equals(density)) return 72;
        if ("schools".equals(density)) return 110;
        return 24;
    }

    static float speedFor(String motion) {
        if ("slow".equals(motion)) return 0.55f;
        if ("quick".equals(motion)) return 1.55f;
        return 1f;
    }

    static int colorFor(String color) {
        if ("lime".equals(color)) return 0xffb7ef61;
        if ("aqua".equals(color)) return 0xff75e8d4;
        return 0xffffd46a;
    }

    static int habitatFor(String habitat) {
        if ("meadow".equals(habitat)) return 1;
        if ("riverbank".equals(habitat)) return 2;
        return 0;
    }
}
