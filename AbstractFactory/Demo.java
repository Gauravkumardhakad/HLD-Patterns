
// making the abstract product interfaces
interface Button{
    void paint();
    void onClick();
}

interface CheckBox{
    void paint();
    void onSelect();
}

// creating the concrete product classes

// 1. windows components
class WindowButton implements Button{
    @Override
    public void paint(){
        System.out.println("window button painted");
    }

    @Override
    public void onClick(){
        System.out.println("window button clicked");
    }
}

class WindowCheckbox implements CheckBox{
    @Override
    public void paint(){
        System.out.println("window checkbox painted");
    }

    @Override
    public void onSelect(){
        System.out.println("window checkbox selected");
    }
}

// 2. mac compoments
class MacButton implements Button{
    @Override
    public void paint(){
        System.out.println("mac button painted");
    }

    @Override
    public void onClick(){
        System.out.println("mac button clicked");
    }
}

class MacCheckbox implements CheckBox{
    @Override
    public void paint(){
        System.out.println("mac checkbox painted");
    }

    @Override
    public void onSelect(){
        System.out.println("mac checkbox selected");
    }
}

// deifing the abstract factory
interface GUIFactory{
    Button createButton();
    CheckBox createCheckBox();
}

// implementing the concrete factories

// 1. window factory
class WindowFactory implements GUIFactory{
    @Override
    public Button createButton(){
        return new WindowButton();
    }

    @Override
    public CheckBox createCheckBox(){
        return new WindowCheckbox();
    }
}

// 2. mac factory
class MacFactory implements GUIFactory{
    @Override
    public Button createButton(){
        return new MacButton();
    }

    @Override
    public CheckBox createCheckBox(){
        return new MacCheckbox();
    }
}


public class Demo{
    public static void main(String a[]){
        String os = "os name";
        GUIFactory factory;

        if(os=="Windows"){
            factory=new WindowFactory();
        }
        else{
            factory = new MacFactory();
        }

        Button btn= factory.createButton();
        CheckBox chk= factory.createCheckBox();

        btn.paint();
        chk.paint();
    }
}


