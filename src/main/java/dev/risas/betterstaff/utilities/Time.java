package dev.risas.betterstaff.utilities;

public class Time {

    public static String formatIntMin(int time) {
        return String.format("%02d:%02d", time / 60, time % 60);
    }
}
