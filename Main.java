public class Main {
    public static void main(String[] args) {
        Huffman huffman = new Huffman("sadkljfijlawfkjlasdfsdkhfasdkhfgasdjgfadshfjasdfjhsdfj");
        String encodedText = huffman.encode();
        System.out.println("\nThe encoded text is :");
        System.out.println(encodedText);
        System.out.println("\nThe following are the code for each letter: ");

        huffman.printCodes();

        String originalText = huffman.decode(encodedText);
        System.out.println("\nThe decoded text: ");
        System.out.println(originalText);
        CompressionRatio ratio = new CompressionRatio(8*originalText.length(),encodedText.length());
        Double R=ratio.calculateRatio();
        System.out.println("\nThe compression ratio: ");
        System.out.println(R);
    }
}
