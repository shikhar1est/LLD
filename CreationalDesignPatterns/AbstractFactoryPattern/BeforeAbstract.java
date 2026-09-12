package AbstractFactoryPattern;

//Concrete classes for Windows
class WindowsButton{
    public void render(){
        System.out.println("Button for Windows");
    }
}
class WindowsCheckbox{
    public void render(){
        System.out.println("Checkbox for Windows");
    }
}
//Concrete classes for Mac
class MacButton {
    public void render() {
        System.out.println("Rendering Mac-style Button.");
    }
}

class MacCheckbox {
    public void render() {
        System.out.println("Rendering Mac-style Checkbox.");
    }
}

public class BeforeAbstract {
    public void renderUI(String osType) {
        if ("WINDOWS".equalsIgnoreCase(osType)) {
            WindowsButton btn = new WindowsButton();
            WindowsCheckbox chk = new WindowsCheckbox();
            btn.render();
            chk.render();
        } else if ("MAC".equalsIgnoreCase(osType)) {
            MacButton btn = new MacButton();
            MacCheckbox chk = new MacCheckbox();
            btn.render();
            chk.render();
        } else {
            throw new IllegalArgumentException("Unsupported OS: " + osType);
        }
    }

    public static void main(String[] args) {
        BeforeAbstract application=new BeforeAbstract();
        application.renderUI("MAC");
    }
}
