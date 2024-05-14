import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandeling {
    public static void main(String[] args) {
        // Reading from a text file
        try (BufferedReader br = new BufferedReader(new FileReader("output.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line); // Print each line to console
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        // Writing to a new text file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write("This is a line written to the output file.\n");
            bw.write("Another line.\n");
            bw.write("Yet another line.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
