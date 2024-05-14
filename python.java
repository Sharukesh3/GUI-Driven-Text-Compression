import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class python {
    private static String inpString;
    public python (String input){
        inpString = input;
    }
    public static void Huffman_tree_visualisation() {
        try {
            String[] pythonCommand = {"python", "Huffman_tree_visualisation.py"};
            ProcessBuilder pb = new ProcessBuilder(pythonCommand);
            Process process = pb.start();

            // Provide input to the Python script
            OutputStreamWriter writer = new OutputStreamWriter(process.getOutputStream());
            writer.write(inpString);
            writer.flush();
            writer.close(); // Close the writer when done writing input

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Python script execution completed with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
