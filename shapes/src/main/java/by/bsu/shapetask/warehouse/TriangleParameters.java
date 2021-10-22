package by.bsu.shapetask.warehouse;

import by.bsu.shapetask.entity.TriangleTypeByAngle;
import by.bsu.shapetask.entity.TriangleTypeBySides;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TriangleParameters implements Cloneable {
    private static final Logger logger = LogManager.getLogger();
    private double area;
    private double perimeter;
    private TriangleTypeBySides typeBySides;
    private TriangleTypeByAngle typeByAngle;

    public TriangleParameters(double area, double perimeter, TriangleTypeBySides typeBySides, TriangleTypeByAngle typeByAngle) {
        this.area = area;
        this.perimeter = perimeter;
        this.typeBySides = typeBySides;
        this.typeByAngle = typeByAngle;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public TriangleTypeBySides getTypeBySides() {
        return typeBySides;
    }

    public void setTypeBySides(TriangleTypeBySides typeBySides) {
        this.typeBySides = typeBySides;
    }

    public TriangleTypeByAngle getTypeByAngle() {
        return typeByAngle;
    }

    public void setTypeByAngle(TriangleTypeByAngle typeByAngle) {
        this.typeByAngle = typeByAngle;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TriangleParameters that = (TriangleParameters) o;

        if (Double.compare(that.area, area) != 0) return false;
        if (Double.compare(that.perimeter, perimeter) != 0) return false;
        if (typeBySides != that.typeBySides) return false;
        return typeByAngle == that.typeByAngle;
    }

    @Override
    public int hashCode() {
        long result = 1;
        int prime = 31;
        result = result * prime + Double.hashCode(area);
        result = result * prime + Double.hashCode(perimeter);
        result = result * prime + (typeBySides != null ? typeBySides.hashCode() : 0);
        result = result * prime + (typeByAngle != null ? typeByAngle.hashCode() : 0);
        return (int) result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TriangleParameters{");
        sb.append("area=").append(area);
        sb.append(", perimeter=").append(perimeter);
        sb.append(", typeBySides=").append(typeBySides);
        sb.append(", typeByAngle=").append(typeByAngle);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public TriangleParameters clone() {
        TriangleParameters copy = null;
        try {
            copy = (TriangleParameters) super.clone();
        }
        catch (CloneNotSupportedException e) {
            logger.catching(e);
        }
        return copy;
    }
}
