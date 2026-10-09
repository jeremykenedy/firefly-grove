package com.jeremykenedy.fireflygrove;

public final class SettingsValues {
    private SettingsValues() {}

    public static boolean isSupported(String key, String value) {
        if (key == null || value == null) return false;
        if ("density".equals(key)) return oneOf(value, "few", "handful", "many", "ton", "schools", "random");
        if ("motion".equals(key)) return oneOf(value, "slow", "natural", "quick", "random");
        if ("glow_color".equals(key)) return oneOf(value, "warm", "lime", "aqua", "random");
        if ("habitat".equals(key)) return oneOf(value, "woodland", "meadow", "riverbank", "random");
        if ("randomize_all".equals(key)) return oneOf(value, "true", "false");
        return false;
    }

    private static boolean oneOf(String value, String... allowed) {
        for (String option : allowed) if (option.equals(value)) return true;
        return false;
    }
}
