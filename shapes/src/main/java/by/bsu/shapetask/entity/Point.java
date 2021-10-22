package by.bsu.shapetask.entity;

public class Point implements Cloneable {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Double.compare(point.x, x) != 0) return false;
        return Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        long prime = 31;
        long result = 1;
        result = result * prime + Double.hashCode(x);
        result = result * prime + Double.hashCode(y);
        return (int) result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("(");
        sb.append(x);
        sb.append(", ").append(y);
        sb.append(')');
        return sb.toString();
    }

    @Override
    public Point clone() {
        Point clone = null;
        try {
            clone = (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
