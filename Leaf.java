public class Leaf extends Node {
    private final char character;

    public Leaf(char character, int frequency) {
        super(null, null); // Initialize left and right nodes to null
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
