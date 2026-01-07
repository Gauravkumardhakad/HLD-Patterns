
//Base component interface
interface TextView{
    void render();
}

// implementing the concrete component
class PlainTextView implements TextView{
    private final String text;

    public PlainTextView(String text) {
        this.text = text;
    }

    @Override
    public void render(){
        System.out.println(text);
    }
}

// creating the abstract decorator
abstract class TextDecorator implements TextView{
    protected final TextView inner;

    public TextDecorator(TextView inner){
        this.inner = inner;
    }
}

// implementing the concrete decorators
class BoldDecorator extends TextDecorator{
    public BoldDecorator(TextView inner){
        super(inner);
    }

    @Override
    public void render(){
        System.out.println("Bolded ");
        inner.render();
    }
}

class ItalicDecorator extends TextDecorator{
    public ItalicDecorator(TextView inner){
        super(inner);
    }

    @Override
    public void render(){
        System.out.println("Italic ");
        inner.render();
    }
}

class UnderlineDecorator extends TextDecorator{
    public UnderlineDecorator(TextView inner){
        super(inner);
    }

    @Override
    public void render(){
        System.out.println("Underlined ");
        inner.render();
    }
}

public class Demo{
    public static void main(String a[]){
        TextView text = new PlainTextView("Gaurav");

        System.out.println("Palin: ");
        text.render();
        System.out.println();

        System.out.println("Bold: ");
        TextView boldText = new BoldDecorator(text);
        boldText.render();
        System.out.println();

        System.out.println("Italic + Underlined: ");
        TextView italicUnderlined = new ItalicDecorator(new UnderlineDecorator(text));
        italicUnderlined.render();
        System.out.println();
    }
}