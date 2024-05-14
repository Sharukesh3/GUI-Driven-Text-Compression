import java.util.ArrayList;
import java.util.List;

public class compression {
    private String compressed;
    private List<Triple> compressedLZ77;

    public compression(String algorithm, String content){
        if (algorithm == "Huffman"){
            this.HuffmanEncoding(content);
        }
        else if (algorithm == "LZ77"){
            this.LZ77Encoding(content);
        }
        else if (algorithm == "LZW"){
            this.LZWEncoding(content);
        }
        else{
            this.getCompressed();
        }
        System.out.println(compressed);
    }

    private void HuffmanEncoding(String content){
        Huffman huffman = new Huffman(content);
        compressed = huffman.encode();
    }

    private void LZ77Encoding(String content){
        LZ77 LZ77 = new LZ77(content,10,5);
        compressedLZ77 = LZ77.getcompressedData();
    }

    private void LZWEncoding(String content){
        compressed = "Yet to be implemented";
    }

    public String getCompressed() {
        return compressed;
    }

    public List<Triple> getCompressedLZ77() {
        return compressedLZ77;
    }

}
