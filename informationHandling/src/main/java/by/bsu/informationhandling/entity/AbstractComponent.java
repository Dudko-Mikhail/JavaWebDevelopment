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
}

