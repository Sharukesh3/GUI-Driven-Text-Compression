import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class python {
    public static void main(String[] args) {
        Huffman_tree_visualisation();
    }
    public static void Huffman_tree_visualisation() {
        try {
            // Replace "python3" with "python" if you're using Python 2.x
            String[] command = {"python", "Huffman_tree_visualisation.py"};

            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Python script execution completed with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
