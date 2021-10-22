package by.bsu.shapetask.observer;

import by.bsu.shapetask.entity.Triangle;

import java.util.EventObject;

public class TriangleEvent extends EventObject {
    public TriangleEvent(Triangle source) {
        super(source);
    }

    @Override
    public Triangle getSource() {
        return (Triangle) super.getSource();
    }
}
