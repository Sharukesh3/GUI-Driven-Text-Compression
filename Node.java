import java.util.*;

public class Node implements Comparable<Node> { //The comparable class is implemented to compare the nodes. The <Node> makes sure that the  Comparable interface is being parameterized with the type Node.
    private final int frequency;
    private Node leftNode;
    private Node rightNode;

    public Node(Node leftNode, Node rightNode) { //Constructor for the Node class 
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        if (leftNode != null && rightNode != null) {
            this.frequency = leftNode.getFrequency() + rightNode.getFrequency();
        } else {
            this.frequency = 0; // Set frequency to 0 if either leftNode or rightNode is null
        }
    }

    public int getFrequency() {
        return this.frequency;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    @Override
    public int compareTo(Node node) { //overriding the compareTo method, returns a possitive integer if frequency is greater than Node frequency and so on...
        return Integer.compare(frequency, node.getFrequency());
    }
}