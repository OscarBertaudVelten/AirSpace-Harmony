package tools;

/**
 * A utility class for handling time-related operations.
 */
public class TimeTools {
    /** Stores the hours component of the time */
    private int hours;
    /** Stores the minutes component of the time */
    private double minutes;

    /**
     * Constructs a new TimeTools object with default values.
     */
    public TimeTools() {}

    /**
     * Converts hours and minutes to total minutes.
     *
     * @return The total minutes equivalent of the time.
     */
    public double HM_to_M() {
        return (hours * 60) + minutes;
    }

    /**
     * Retrieves the hours component of the time.
     *
     * @return The hours component.
     */
    public int getHours() {
        return hours;
    }

    /**
     * Retrieves the minutes component of the time.
     *
     * @return The minutes component.
     */
    public double getMinutes() {
        return minutes;
    }

    /**
     * Sets the hours component of the time.
     *
     * @param newHours The new value for the hours component.
     */
    public void setHours(int newHours) {
        hours = newHours;
    }

    /**
     * Sets the minutes component of the time.
     *
     * @param newMinutes The new value for the minutes component.
     */
    public void setMinutes(double newMinutes) {
        minutes = newMinutes;
    }

    /**
     * Converts total minutes to hours and minutes.
     *
     * @param time The total minutes to be converted.
     */
    public void M_to_HM(double time) {
        hours = (int) (time / 60);
        minutes = time % 60;
    }

    /**
     * Returns a string representation of the time in the format "hours h minutes".
     *
     * @return A string representation of the time.
     */
    @Override
    public String toString() {
        if (minutes >= 10)
            return String.format("%dh%.0f", hours, minutes);
        else
            return String.format("%dh0%.0f", hours, minutes);

    }
}
