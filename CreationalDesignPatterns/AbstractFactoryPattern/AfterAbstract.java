package AbstractFactoryPattern;

interface Button{
    void paint();
}
interface Checkbox{
    void paint();
}

class WindowsButton1 implements Button {
    @Override
    public void paint() {
        System.out.println("Rendered Windows-style Button.");
    }
}

class WindowsCheckbox1 implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendered Windows-style Checkbox.");
    }
}

// 3. Concrete Products for Mac
class MacButton1 implements Button {
    @Override
    public void paint() {
        System.out.println("Rendered Mac-style Button.");
    }
}

class MacCheckbox1 implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendered Mac-style Checkbox.");
    }
}

interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// 5. Concrete Factories: Guarantees products from the SAME family are created
class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton1();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox1();
    }
}

class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton1();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox1();
    }
}

public class AfterAbstract {
    private final Button button;
    private final Checkbox checkbox;

    // The client accepts a factory and never calls 'new WindowsButton()' directly
    public AfterAbstract(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
    public static void main(String[] args) {
// App decides the factory once during startup or config read:
        String os = "MAC";
        GUIFactory factory;

        if ("WINDOWS".equalsIgnoreCase(os)) {
            factory = new WindowsFactory();
        } else if ("MAC".equalsIgnoreCase(os)) {
            factory = new MacFactory();
        } else {
            throw new IllegalArgumentException("Unknown OS: " + os);
        }

        // Entire UI renders matching controls without knowing the concrete classes
        AfterAbstract app = new AfterAbstract(factory);
        app.paint();
    }
}
