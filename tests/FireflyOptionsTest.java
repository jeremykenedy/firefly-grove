package com.jeremykenedy.fireflygrove;

import java.util.Random;

public final class FireflyOptionsTest {
    public static void main(String[] args) {
        check(FireflyOptions.countFor("few") == 12, "few density");
        check(FireflyOptions.countFor("handful") == 24, "handful density");
        check(FireflyOptions.countFor("many") == 38, "many density");
        check(FireflyOptions.countFor("ton") == 72, "ton density");
        check(FireflyOptions.countFor("schools") == 110, "schools density");
        check(FireflyOptions.countFor("invalid") == 24, "density fallback");
        check(FireflyOptions.speedFor("slow") == 0.55f, "slow speed");
        check(FireflyOptions.speedFor("natural") == 1f, "natural speed");
        check(FireflyOptions.speedFor("quick") == 1.55f, "quick speed");
        check(FireflyOptions.speedFor("invalid") == 1f, "speed fallback");
        check(FireflyOptions.colorFor("warm") == 0xffffd46a, "warm color");
        check(FireflyOptions.colorFor("lime") == 0xffb7ef61, "lime color");
        check(FireflyOptions.colorFor("aqua") == 0xff75e8d4, "aqua color");
        check(FireflyOptions.colorFor("invalid") == 0xffffd46a, "color fallback");
        check(FireflyOptions.habitatFor("woodland") == 0, "woodland habitat");
        check(FireflyOptions.habitatFor("meadow") == 1, "meadow habitat");
        check(FireflyOptions.habitatFor("riverbank") == 2, "riverbank habitat");
        check(FireflyOptions.habitatFor("invalid") == 0, "habitat fallback");
        FireflyOptions selected = FireflyOptions.resolve("few", "quick", "aqua", "meadow", false, new Random(1));
        check(selected.count == 12 && selected.speed == 1.55f && selected.glowColor == 0xff75e8d4
                && selected.habitat == 1, "explicit settings");
        check(SettingsValues.isSupported("density", "few"), "few density accepted");
        check(SettingsValues.isSupported("density", "handful"), "handful density accepted");
        check(SettingsValues.isSupported("density", "many"), "many density accepted");
        check(SettingsValues.isSupported("density", "ton"), "ton density accepted");
        check(SettingsValues.isSupported("density", "schools"), "schools density accepted");
        check(SettingsValues.isSupported("motion", "slow"), "slow speed accepted");
        check(SettingsValues.isSupported("motion", "natural"), "natural speed accepted");
        check(SettingsValues.isSupported("motion", "quick"), "quick speed accepted");
        check(SettingsValues.isSupported("glow_color", "warm"), "warm glow accepted");
        check(SettingsValues.isSupported("glow_color", "lime"), "lime glow accepted");
        check(SettingsValues.isSupported("glow_color", "aqua"), "aqua glow accepted");
        check(SettingsValues.isSupported("habitat", "woodland"), "woodland habitat accepted");
        check(SettingsValues.isSupported("habitat", "meadow"), "meadow habitat accepted");
        check(SettingsValues.isSupported("habitat", "riverbank"), "riverbank habitat accepted");
        check(SettingsValues.isSupported("randomize_all", "true"), "randomize toggle accepted");
        check(SettingsValues.isSupported("randomize_all", "false"), "disabled randomize accepted");
        check(SettingsValues.isSupported("density", "random"), "random density accepted");
        check(!SettingsValues.isSupported("density", "millions"), "invalid density rejected");
        check(!SettingsValues.isSupported("motion", "instant"), "invalid speed rejected");
        check(!SettingsValues.isSupported("glow_color", "red"), "invalid glow rejected");
        check(!SettingsValues.isSupported("habitat", "ocean"), "invalid habitat rejected");
        check(!SettingsValues.isSupported("randomize_all", "yes"), "invalid toggle rejected");
        check(!SettingsValues.isSupported("unrecognized", "value"), "unknown setting rejected");
        check(!SettingsValues.isSupported(null, "warm"), "null key rejected");
        check(!SettingsValues.isSupported("glow_color", null), "null value rejected");
        FireflyOptions random = FireflyOptions.resolve("random", "random", "random", "random", false, new Random(7));
        check(random.count >= 12 && random.count <= 110, "random count bounds");
        check(random.speed >= 0.55f && random.speed <= 1.55f, "random speed bounds");
        check(random.habitat >= 0 && random.habitat <= 2, "random habitat bounds");
        check(random.glowColor == 0xffffd46a || random.glowColor == 0xffb7ef61 || random.glowColor == 0xff75e8d4,
                "random color choice");
        FireflyOptions all = FireflyOptions.resolve("few", "slow", "warm", "woodland", true, new Random(5));
        check(all.count >= 12 && all.count <= 110 && all.speed >= 0.55f && all.speed <= 1.55f,
                "randomize all bounds");
        check(all.habitat >= 0 && all.habitat <= 2, "randomize all habitat");
        check(all.glowColor == 0xffffd46a || all.glowColor == 0xffb7ef61 || all.glowColor == 0xff75e8d4,
                "randomize all color");
        FireflyOptions fallback = FireflyOptions.resolve("invalid", "invalid", "invalid", "invalid", false,
                new Random(9));
        check(fallback.count == 12 && fallback.speed == 0.55f && fallback.glowColor == 0xffffd46a
                && fallback.habitat == 0, "invalid setting fallback");
    }

    private static void check(boolean result, String message) {
        if (!result) throw new AssertionError(message);
    }
}
