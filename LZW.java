import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class LZW {
    private List<Integer> compressed;
    private String decompressed;
    public LZW (String input){
        this.compressed = encode1(input);
        this.decompressed = decode1(compressed);
    }
    public static List<Integer> encode1(String text) {
        int dictSize = 256;
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < dictSize; i++) {
            dictionary.put(String.valueOf((char) i), i);
        }
        String foundchars = "";
        List<Integer> result = new ArrayList<>();
        for (char character : text.toCharArray()) {
            String charsToAdd = foundchars + character;
            if (dictionary.containsKey(charsToAdd)) {
                foundchars = charsToAdd;
            } else {
                result.add(dictionary.get(foundchars));
                dictionary.put(charsToAdd, dictSize++);
                foundchars = String.valueOf(character);
            }
        }
        if (!foundchars.isEmpty()) {
            result.add(dictionary.get(foundchars));
        }
        return result;
    }
    public static String decode1(List<Integer> encodedText) {
        int dictSize = 256;
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < dictSize; i++) {
            dictionary.put(i, String.valueOf((char) i));
        }
        String ch = String.valueOf((char) encodedText.remove(0).intValue());
        StringBuilder result = new StringBuilder(ch);
        for (int code:encodedText) {
            String entry = dictionary.containsKey(code) ? dictionary.get(code):ch+ch.charAt(0);
            result.append(entry);
            dictionary.put(dictSize++,ch+entry.charAt(0));
            ch=entry;
        }
        return result.toString();
    }

    public List<Integer> getCompressed() {
        return compressed;
    }

    public String getDecompressed() {
        return decompressed;
    }
}