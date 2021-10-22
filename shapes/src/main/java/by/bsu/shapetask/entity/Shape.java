package by.bsu.shapetask.entity;

import by.bsu.shapetask.util.IdGenerator;

import java.util.UUID;

public abstract class Shape {
    private String name;
    private final UUID shapeId;

    public Shape(String name) {
        this.name = name;
        IdGenerator generator = IdGenerator.getInstance();
        this.shapeId = generator.generateId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getShapeId() {
        return shapeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shape shape = (Shape) o;

        if (name != null ? !name.equals(shape.name) : shape.name != null) return false;
        return shapeId != null ? shapeId.equals(shape.shapeId) : shape.shapeId == null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Shape{");
        sb.append("name='").append(name).append('\'');
        sb.append(", shapeId=").append(shapeId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + shapeId.hashCode();
        return result;
    }
}
