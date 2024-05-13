import java.util.ArrayList;
import java.util.List;
public class LZ77 {
    private String input;
    private final int windowSize;
    private final int bufferSize;
    private List<Triple> compressedData;
    private String decodedData;

    public LZ77 (String input, int windowSize, int bufferSize){
        this.input = input;
        this.windowSize = windowSize;
        this.bufferSize = bufferSize;
        this.compressedData = compress(this.input, this.windowSize, this.bufferSize);
        this.decodedData = this.decode(compressedData);
    }

    public static List<Triple> compress(String input, int windowSize, int bufferSize) {
        List<Triple> compressedData = new ArrayList<>();
        int currentIndex = 0;
        while (currentIndex < input.length()) {
            int maxmatchlen = 0;
            int maxmatchdist = 0;
            int start = Math.max(0, currentIndex - windowSize);
            for (int i = currentIndex - 1; i >= start; i--) {
                int matchlen = 0;
                while (currentIndex + matchlen < input.length()
                        && input.charAt(i + matchlen) == input.charAt(currentIndex + matchlen)
                        && matchlen < bufferSize) {
                    matchlen++;
                }
                if (matchlen>maxmatchlen) {
                    maxmatchlen=matchlen;
                    maxmatchdist=currentIndex - i;
                }
            }
            if (maxmatchlen > 0 && currentIndex + maxmatchlen < input.length()) {
                compressedData.add(new Triple(maxmatchdist, maxmatchlen, input.charAt(currentIndex + maxmatchlen)));
                currentIndex += maxmatchlen + 1;
            } else {
                compressedData.add(new Triple(0, 0, input.charAt(currentIndex)));
                currentIndex++;
            }
        }
        return compressedData;
    }

    public static String decode(List<Triple> compressedData) {
        StringBuilder output=new StringBuilder();
        for (Triple triple : compressedData) {
            if (triple.dist==0 && triple.len==0) {
                output.append(triple.symbol);
            } else {
                int start=output.length()-triple.dist;
                for (int i=0;i < triple.len;i++) {
                    output.append(output.charAt(start+i));
                }
                output.append(triple.symbol);
            }
        }
        return output.toString();
    }

    public List<Triple> getcompressedData(){
        return compressedData;
    }

    public String getdecodedData(){
        return decodedData;
    }
}