package intersectionTools;

import tools.PointPlus;

import static java.lang.Double.max;
import static java.lang.Double.min;

/**
 * Represents a line intersection point calculator.
 */
public class IntersectionPoint {

    /** The first point of the first line segment. */
    final PointPlus A;

    /** The second point of the first line segment. */
    final PointPlus B;

    /** The first point of the second line segment. */
    final PointPlus C;

    /** The second point of the second line segment. */
    final PointPlus D;

    /** The intersection point of the two line segments. */
    PointPlus Intersection;

    /**
     * Constructs an IntersectionPoint object with the given points representing two line segments.
     * 
     * @param newA The first point of the first line segment.
     * @param newB The second point of the first line segment.
     * @param newC The first point of the second line segment.
     * @param newD The second point of the second line segment.
     */
    public IntersectionPoint(PointPlus newA, PointPlus newB, PointPlus newC, PointPlus newD) {
        A = newA;
        B = newB;
        C = newC;
        D = newD;
    }

    /**
     * Checks if a point lies on a line segment.
     * 
     * @param P The first endpoint of the line segment.
     * @param Q The point to be checked.
     * @param R The second endpoint of the line segment.
     * @return True if the point lies on the line segment, false otherwise.
     */
    public boolean isOnSegment(PointPlus P, PointPlus Q, PointPlus R) {
        return (Q.getX() <= max(P.getX(), R.getX()) &&
                Q.getX() >= min(P.getX(), R.getX()) &&
                Q.getY() <= max(P.getY(), R.getY()) &&
                Q.getY() >= min(P.getY(), R.getY()));
    }

    /**
     * Determines the orientation of three points (clockwise, counterclockwise, or collinear).
     * 
     * @param P The first point.
     * @param Q The second point.
     * @param R The third point.
     * @return 1 if clockwise, 2 if counterclockwise, 0 if collinear.
     */
    public int orientation(PointPlus P, PointPlus Q, PointPlus R) {
        int result = 0;
        double val = ((Q.getY() - P.getY()) * (R.getX() - Q.getX())) - ((Q.getX() - P.getX()) * (R.getY() - Q.getY()));
        if (val > 0)
            result = 1;
        else if (val < 0)
            result = 2;
        return result;
    }

    /**
     * Checks if two line segments intersect.
     * 
     * @return True if the line segments intersect, false otherwise.
     */
    public boolean hasIntersection() {
        boolean result = false;
        int oA = orientation(A, B, C);
        int oB = orientation(A, B, D);
        int oC = orientation(C, D, A);
        int oD = orientation(C, D, B);

        if (oA != oB && oC != oD)
            result = true;
        else if (oA == 0 && isOnSegment(A, C, B))
            result = true;
        else if (oB == 0 && isOnSegment(A, D, B))
            result = true;
        else if (oC == 0 && isOnSegment(C, A, D))
            result = true;
        else if (oD == 0 && isOnSegment(C, B, D))
            result = true;
        return result;
    }

    /**
     * Finds the intersection point of two line segments.
     */
    public void findIntersectionPointPlus() {
        Intersection = new PointPlus();
        if (hasIntersection()) {
            Intersection.setX(((A.getX() * B.getY() - A.getY() * B.getX()) * (C.getX() - D.getX()) - 
                               (A.getX() - B.getX()) * (C.getX() * D.getY() - C.getY() * D.getX())) / 
                               ((A.getX() - B.getX()) * (C.getY() - D.getY()) - 
                               (A.getY() - B.getY()) * (C.getX() - D.getX())));
            Intersection.setY(((A.getX() * B.getY() - A.getY() * B.getX()) * (C.getY() - D.getY()) - 
                               (A.getY() - B.getY()) * (C.getX() * D.getY() - C.getY() * D.getX())) / 
                               ((A.getX() - B.getX()) * (C.getY() - D.getY()) - 
                               (A.getY() - B.getY()) * (C.getX() - D.getX())));
            Intersection.setExistence(true);
        }
    }

    /**
     * Returns a string representation of the intersection point.
     * 
     * @return A string representation of the intersection point.
     */
    @Override
    public String toString() {
        if (Intersection.exists()) {
            return String.format("(%f, %f)", Intersection.getX(), Intersection.getY());
        } else {
            return "The point doesn't exist";
        }
    }
}
