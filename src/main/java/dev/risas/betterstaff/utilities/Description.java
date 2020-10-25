package dev.risas.betterstaff.utilities;

import dev.risas.betterstaff.BetterStaff;

import java.util.List;

public class Description {

    public static String getName() {
        return BetterStaff.getInstance().getDescription().getName();
    }

    public static String getVersion() {
        return BetterStaff.getInstance().getDescription().getVersion();
    }

    public static List<String> getAuthor() {
        return BetterStaff.getInstance().getDescription().getAuthors();
    }
}
