package by.bsu.shapetask.entity;

import by.bsu.shapetask.exception.ShapeException;
import by.bsu.shapetask.observer.Observable;
import by.bsu.shapetask.observer.Observer;
import by.bsu.shapetask.observer.TriangleEvent;
import by.bsu.shapetask.validator.TriangleValidator;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends Shape implements Observable {
    private Point a;
    private Point b;
    private Point c;
    private final List<Observer> observers;

    {
        observers = new ArrayList<>();
    }

    public Triangle(String name, Point a, Point b, Point c) throws ShapeException {
        super(name);
        TriangleValidator validator = TriangleValidator.getInstance();
        if (!validator.isValid(a, b, c)) {
            throw new ShapeException("Points don't form a triangle");
        }
        this.a = a.clone();
        this.b = b.clone();
        this.c = c.clone();
    }

    public Point getA() {
        return a.clone();
    }

    public void setA(Point a) throws ShapeException {
        TriangleValidator validator = TriangleValidator.getInstance();
        if (!validator.isValid(a, b, c)) {
            throw new ShapeException("Points don't form a triangle");
        }
        this.a = a.clone();
        notifyObservers();
    }

    public Point getB() {
        return b.clone();
    }

    public void setB(Point b) throws ShapeException {
        TriangleValidator validator = TriangleValidator.getInstance();
        if (!validator.isValid(a, b, c)) {
            throw new ShapeException("Points don't form a triangle");
        }
        this.b = b.clone();
        notifyObservers();
    }

    public Point getC() {
        return c.clone();
    }

    public void setC(Point c) throws ShapeException {
        TriangleValidator validator = TriangleValidator.getInstance();
        if (!validator.isValid(a, b, c)) {
            throw new ShapeException("Points don't form a triangle");
        }
        this.c = c.clone();
        notifyObservers();
    }



    @Override
    public void attach(Observer observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        if (!observers.isEmpty()) {
            TriangleEvent event = new TriangleEvent(this);
            observers.forEach(obs -> obs.parameterChanged(event));
        }
    }

    public boolean equalsIgnoreNameAndId(Triangle triangle) {
        if (this == triangle) {
            return true;
        }
        if (triangle == null) {
            return false;
        }
        if (a != null ? !a.equals(triangle.a) : triangle.a != null) return false;
        if (b != null ? !b.equals(triangle.b) : triangle.b != null) return false;
        return c != null ? c.equals(triangle.c) : triangle.c == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Triangle triangle = (Triangle) o;

        if (a != null ? !a.equals(triangle.a) : triangle.a != null) return false;
        if (b != null ? !b.equals(triangle.b) : triangle.b != null) return false;
        return c != null ? c.equals(triangle.c) : triangle.c == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (a != null ? a.hashCode() : 0);
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Triangle{");
        sb.append(super.toString());
        sb.append(", a=").append(a);
        sb.append(", b=").append(b);
        sb.append(", c=").append(c);
        sb.append('}');
        return sb.toString();
    }
}
