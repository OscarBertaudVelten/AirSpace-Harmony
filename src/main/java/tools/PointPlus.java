package tools;

/**
 * The PointPlus class represents a point in a 2D coordinate system with an additional existence flag.
 */
public class PointPlus {

    /** Coordinates of the point */
    private double x, y;

    /** Flag indicating whether the point exists */
    private boolean exists;

    /**
     * Constructs a new PointPlus instance with default coordinates (0, 0) and existence flag set to false.
     */
    public PointPlus() {
        this.x = 0;
        this.y = 0;
        this.exists = false;
    }

    /**
     * Constructs a new PointPlus instance with specified coordinates and existence flag set to true.
     *
     * @param newX The x-coordinate of the point.
     * @param newY The y-coordinate of the point.
     */
    public PointPlus(double newX, double newY) {
        this.x = newX;
        this.y = newY;
        this.exists = true;
    }

    /**
     * Gets the x-coordinate of the point.
     *
     * @return The x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the point.
     *
     * @return The y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the point.
     *
     * @param newX The new x-coordinate.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * Sets the y-coordinate of the point.
     *
     * @param newY The new y-coordinate.
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     * Checks if the point exists.
     *
     * @return True if the point exists, false otherwise.
     */
    public boolean exists() {
        return exists;
    }

    /**
     * Sets the existence flag of the point.
     *
     * @param newExists The new existence flag.
     */
    public void setExistence(boolean newExists) {
        this.exists = newExists;
    }

    /**
     * Returns a string representation of the point.
     * If the point exists, returns the coordinates in the format (x, y),
     * otherwise returns "The point doesn't exist".
     *
     * @return The string representation of the point.
     */
    @Override
    public String toString() {
        if (exists) {
            return String.format("(%f, %f)", x, y);
        } else {
            return "The point doesn't exist";
        }
    }
}
