package by.bsu.informationhandling.entity;

import by.bsu.informationhandling.constant.AdditionalComponentText;
import by.bsu.informationhandling.constant.ComponentType;

import java.util.ArrayList;
import java.util.List;

public class TextComposite extends AbstractComponent {
    private List<AbstractComponent> components;

    {
        components = new ArrayList<>();
    }

    public TextComposite(ComponentType type) {
        super(type);
    }

    public AbstractComponent getChild(int index) {
        return components.get(index);
    }

    public int getComponentsAmount() {
        return components.size();
    }

    public boolean add(AbstractComponent component) {
        return components.add(component);
    }

    public boolean remove(AbstractComponent component) {
        return components.remove(component);
    }

    @Override
    public String restoreText() {
        StringBuilder sb = new StringBuilder();
        switch (getComponentType()) {
            case TEXT -> {
                components.forEach(c -> sb.append(AdditionalComponentText.PARAGRAPH_TEXT.getText())
                                          .append(c.restoreText()));
            }
            case PARAGRAPH, LEXEME -> {
                components.forEach(c -> sb.append(c.restoreText()));
            }
            case SENTENCE -> {
                for (int i = 0; i < components.size() - 1; i++) {
                    sb.append(components.get(i).restoreText())
                            .append(AdditionalComponentText.LEXEME.getText());
                }
                sb.append(components.get(components.size() - 1).restoreText());
            }
        }
        return sb.toString();
    }
}
