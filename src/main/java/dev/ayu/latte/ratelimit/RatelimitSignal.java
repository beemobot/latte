/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package dev.ayu.latte;

public enum RatelimitSignal {

    REQUEST_PERMIT,
    REQUEST_NUM_REMAINING_PERMITS,
    REQUEST_REMAINING_PAUSE_NANOS,
    GRANT_PERMIT,
    GRANT_NUM_REMAINING_PERMITS,
    GRANT_REMAINING_PAUSE_NANOS;

    public static String getSeparator() {
        return "::";
    }

}