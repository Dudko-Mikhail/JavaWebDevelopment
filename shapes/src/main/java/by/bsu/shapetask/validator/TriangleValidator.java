package by.bsu.shapetask.validator;

import by.bsu.shapetask.entity.Point;

public class TriangleValidator {
    public static TriangleValidator instance;

    public static TriangleValidator getInstance() {
        if (instance == null) {
            instance = new TriangleValidator();
        }
        return instance;
    }

    public boolean isValid(Point a, Point b, Point c) {
        return a != null && b != null && c != null
                && !isLyingOnOneLine(a, b, c)
                && !a.equals(b) && !a.equals(c) && !b.equals(c);
    }

    private boolean isLyingOnOneLine(Point a, Point b, Point c) {
        return ((b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX()) == 0);
    }

    private TriangleValidator() {}
}
