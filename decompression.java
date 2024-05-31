import java.util.List;

public class decompression {
    private String decompressed;
    private String compressedHuffman;
    private List<Triple> compressedLZ77;
    private List<Integer> compressedLZW;

    public decompression(String algorithm, Object compressedData) {
        if (algorithm.equals("Huffman")) {
            this.compressedHuffman = (String) compressedData;
            this.HuffmanDecoding();
        } else if (algorithm.equals("LZ77")) {
            this.compressedLZ77 = (List<Triple>) compressedData;
            this.LZ77Decoding();
        } else if (algorithm.equals("LZW")) {
            this.compressedLZW = (List<Integer>) compressedData;
            this.LZWDecoding();
        } else {
            this.decompressed = "Invalid algorithm";
        }
    }

    private void HuffmanDecoding() {
        Huffman huffman = new Huffman("");
        decompressed = huffman.decode(compressedHuffman);
    }

    private void LZ77Decoding() {
        decompressed = LZ77.decode(compressedLZ77);
    }

    private void LZWDecoding() {
        decompressed = LZW.decode1(compressedLZW);
    }

    public String getDecompressed() {
        return decompressed;
    }
}
