import java.util.*;

public class Node implements Comparable<Node> {
    private final int frequency;
    private Node leftNode;
    private Node rightNode;

    public Node(Node leftNode, Node rightNode) {
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
    public int compareTo(Node node) {
        return Integer.compare(frequency, node.getFrequency());
    }
}