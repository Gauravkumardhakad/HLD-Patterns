
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// creating the flyweight interface
interface CharacterFlyweight{
    void draw(int x,int y);
}

// implementing the concrete flyweight
class CharacterGlyph implements CharacterFlyweight{
    private final char symbol;
    private final String fontFamily;
    private final int fontSize;
    private final String color;

    public CharacterGlyph(char symbol, String fontFamily, int fontSize, String color) {
        this.symbol = symbol;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.color = color;
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("Drawing '" + symbol + "' [Font: " + fontFamily +
            ", Size: " + fontSize + ", Color: " + color + "] at (" + x + "," + y + ")");
    }
}

// creating the flyweight factory
class CharacterFlyweightFactory{
    private final Map<String, CharacterFlyweight>flyweightMap = new HashMap<>();

    public CharacterFlyweight getFlyweight(char symbol, String fontFamily, int fontSize, String color) {
        String key = symbol + fontFamily + fontSize + color;
        flyweightMap.putIfAbsent(key, new CharacterGlyph(symbol, fontFamily, fontSize, color));
        return flyweightMap.get(key);
    }

    public int getFlyweightCount() {
        return flyweightMap.size();
    }
}

// creating the client
class TextEditorClient{
    private final CharacterFlyweightFactory factory= new CharacterFlyweightFactory();
    private final List<RenderedCharacter> document= new ArrayList<>();

    public void addCharacter(char c, int x, int y, String font, int size, String color){
        CharacterFlyweight glyph = factory.getFlyweight(c, font, size, color);
        document.add(new RenderedCharacter(glyph, x, y));
    }

    public void renderDocument() {
        for (RenderedCharacter rc : document) {
            rc.render();
        }
        System.out.println("Total flyweight objects used: " + factory.getFlyweightCount());
    }

    private static class RenderedCharacter {
        private final CharacterFlyweight glyph;
        private final int x, y;

        public RenderedCharacter(CharacterFlyweight glyph, int x, int y) {
            this.glyph = glyph;
            this.x = x;
            this.y = y;
        }

        public void render() {
            glyph.draw(x, y);
        }
    }
} 


// creating the main program
public class Demo{
    public static void main(String a[]){
        TextEditorClient editor = new TextEditorClient();

        // Render "Hello" with same style
        String word = "Hello";
        for (int i = 0; i < word.length(); i++) {
            editor.addCharacter(word.charAt(i), 10 + i * 15, 50, "Arial", 14, "#000000");
        }

        // Render "World" with different font and color
        String word2 = "World";
        for (int i = 0; i < word2.length(); i++) {
            editor.addCharacter(word2.charAt(i), 10 + i * 15, 100, "Times New Roman", 14, "#3333FF");
        }

        editor.renderDocument();
    }
}