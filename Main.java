public class Main {
    public static void main(String[] args) {
        String inpString = "This is a line written to the output file.\r\n" + //
                        "Another line.\r\n" + //
                        "Yet another line.";
        System.out.println("Huffman coding:");

        Huffman huffman = new Huffman(inpString);
        String encodedText = huffman.encode();
        System.out.println("\nThe encoded text is :");
        System.out.println(encodedText);

        System.out.println("\nThe following are the code for each letter: ");
        huffman.printCodes();

        String originalText = huffman.decode(encodedText);
        System.out.println("\nThe decoded text: ");
        System.out.println(originalText);

        System.out.println("\nTree visualisation");
        python python = new python(inpString);
        python.Huffman_tree_visualisation();

        CompressionRatio ratio = new CompressionRatio(8*originalText.length(),encodedText.length());
        Double R=ratio.calculateRatio();
        System.out.println("\nThe compression ratio: ");
        System.out.println(R);

        System.out.println("\n\nLZ77 encoding:");
        LZ77 LZ77 = new LZ77(inpString,10,7);
        System.out.println("\nThe encoded text is :");
        System.out.println(LZ77.getcompressedData());
        System.out.println("\nThe decoded text: ");
        System.out.println(LZ77.getdecodedData());
    }
}
