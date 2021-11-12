package by.bsu.informationhandling.entity;

import by.bsu.informationhandling.constant.ComponentType;

public abstract class AbstractComponent {
    private final ComponentType componentType;

    public AbstractComponent(ComponentType componentType) {
        this.componentType = componentType;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public abstract String restoreText();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractComponent component = (AbstractComponent) o;

        return componentType == component.componentType;
    }

    @Override
    public int hashCode() {
        return componentType != null ? componentType.hashCode() : 0;
    }
}

