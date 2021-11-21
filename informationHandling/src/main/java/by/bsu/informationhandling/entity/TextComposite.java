package by.bsu.informationhandling.entity;

import by.bsu.informationhandling.constant.AdditionalComponentText;
import by.bsu.informationhandling.constant.ComponentType;

import java.util.ArrayList;
import java.util.List;

public class TextComposite extends AbstractComponent {
    private final List<AbstractComponent> components;

    public TextComposite(ComponentType type) {
        super(type);
        components = new ArrayList<>();
    }

    public AbstractComponent getChild(int index) {
        return components.get(index);
    }

    public List<AbstractComponent> getChildren() {
        return components;
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
            case TEXT -> components.forEach(component -> {
                String paragraphText = component.restoreText();
                if (!paragraphText.endsWith(AdditionalComponentText.PARAGRAPH_END.getText())) {
                    paragraphText += AdditionalComponentText.PARAGRAPH_END.getText();
                }
                sb.append(AdditionalComponentText.PARAGRAPH_TEXT.getText())
                        .append(paragraphText);
            });
            case PARAGRAPH -> {
                for (int i = 0; i < components.size(); i++) {
                    String sentence = components.get(i).restoreText();
                    if (i == components.size() - 1) {
                        sentence = sentence.stripTrailing();
                    }
                    sb.append(sentence);
                }
            }
            case SENTENCE -> {
                for (int i = 0; i < components.size() - 1; i++) {
                    sb.append(components.get(i).restoreText())
                            .append(AdditionalComponentText.LEXEME.getText());
                }
                sb.append(components.get(components.size() - 1).restoreText());
            }
            case LEXEME -> components.forEach(c -> sb.append(c.restoreText()));
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TextComposite composite = (TextComposite) o;

        return components.equals(composite.components);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + components.hashCode();
        return result;
    }
}