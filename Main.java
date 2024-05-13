public class Main {
    public static void main(String[] args) {
        System.out.println("Huffman coding:");

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

        System.out.println("LZ77 encoding");
        LZ77 LZ77 = new LZ77("cabracadabrarrarrad",10,5);
        System.out.println("\nThe encoded text is :");
        System.out.println("Compressed data: " + LZ77.getcompressedData());
        System.out.println("\nThe decoded text: ");
        System.out.println("Decoded data: " + LZ77.getdecodedData());
    }
}
