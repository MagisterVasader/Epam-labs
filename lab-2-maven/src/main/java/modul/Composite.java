package modul;

import java.util.List;

public class Composite implements Component {
    private List<Component> text;

    public Composite(List<Component> text) {
        this.text = text;
    }

    public void add(Component component) {
        text.add(component);
    }

    public void remove(Component component) {
        text.remove(component);
    }

    public List<Component> getText() {
        return text;
    }

    public void setText(List<Component> text) {
        this.text = text;
    }

    @Override
    public int inclusion(char symbol) {
        for (Component component : text) {
            System.out.println(component.inclusion(symbol));
        }
        return 2;
    }

    @Override
    public String toString() {
        return "Composite{" +
                "text=" + text +
                '}';
    }
}
