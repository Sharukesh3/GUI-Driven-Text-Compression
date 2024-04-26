public class Leaf extends Node { //leaf class is a special Node class where both character and frequency is stored. As the name Leaf class they dont have any children.
    private final char character;

    public Leaf(char character, int frequency) {
        super(null, null); // Initialize left and right nodes to null in Node class
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
