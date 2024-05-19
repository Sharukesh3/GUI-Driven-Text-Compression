import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileChooserExample extends JFrame {
    private JButton chooseFileButton;
    private JLabel fileNameLabel;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JComboBox<String> algorithmComboBox;
    private JButton compressButton;
    private StringBuilder fileContent = new StringBuilder();
    private String compressedContent;

    public FileChooserExample() {
        setTitle("File Chooser Example");
        setSize(800, 400); // Adjusted size for better visibility
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        chooseFileButton = new JButton("Choose File");
        fileNameLabel = new JLabel("Selected File: ");
        inputTextArea = new JTextArea();
        outputTextArea = new JTextArea();
        outputTextArea.setLineWrap(true); // Enable line wrapping for outputTextArea
        outputTextArea.setWrapStyleWord(true); // Wrap at word boundaries
        outputTextArea.setEditable(false);
        
        // ComboBox options
        String[] algorithms = {"Choose an algorithm", "Huffman", "LZ77", "LZW"};
        algorithmComboBox = new JComboBox<>(algorithms);
        
        compressButton = new JButton("Compress");
        compressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                if (selectedAlgorithm.equals("Choose an algorithm")) {
                    JOptionPane.showMessageDialog(null, "Please select an algorithm.");
                    return;
                }
                
                // Call the compress function with the selected algorithm
                compress(selectedAlgorithm);
            }
        });

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose a File");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    fileNameLabel.setText("Selected File: " + selectedFile.getName());
                    
                    // Read file contents and display in the input text area
                    fileContent.setLength(0); // Clear existing content
                    String content = readFile(selectedFile);
                    inputTextArea.setText(content);
                }
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(fileNameLabel, BorderLayout.WEST); // Place label on the left
        topPanel.add(chooseFileButton, BorderLayout.EAST); // Place button on the right
        
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input"));
        
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));

        JPanel algorithmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        algorithmPanel.add(algorithmComboBox);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(compressButton);
        
        JPanel mainPanel = new JPanel(new GridLayout(1, 2)); // Split into two columns
        mainPanel.add(inputPanel);
        mainPanel.add(outputPanel);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(algorithmPanel, BorderLayout.CENTER);
        bottomPanel.add(chooseFileButton, BorderLayout.WEST); // Adjusted placement of "Choose File" button
        bottomPanel.add(buttonPanel, BorderLayout.EAST); // Adjusted placement of compress button

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(topPanel, BorderLayout.NORTH);
        containerPanel.add(mainPanel, BorderLayout.CENTER);
        containerPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(containerPanel);
    }

    // Read contents of a file and return as a single string
    private String readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }

    private void compress(String algorithm) {

        JOptionPane.showMessageDialog(null, "Compressing file using " + algorithm);
        compression compresser = new compression(algorithm, inputTextArea.getText());
        if (algorithm.equals("Huffman")){
            compressedContent = compresser.getCompressed();
            outputTextArea.setText(compresser.getCompressed());
        }
        else if (algorithm.equals("LZ77")){
            compressedContent = compresser.getCompressedLZ77().toString();
            outputTextArea.setText(compresser.getCompressedLZ77().toString());
        }
        else if (algorithm.equals("LZW")){
            compressedContent = compresser.getCompressedLZW().toString();
            outputTextArea.setText(compresser.getCompressedLZW().toString());
        }
        else {
            JOptionPane.showMessageDialog(null, "Invalid compression algorithm.");
            return;
        }
        // Fixed path to save the compressed file
    String fixedPath = "C:\\familyfolders\\profolders\\Collage stuff\\Sem 2 project\\DSA\\output_files\\output.txt";

    try {
        // Create a new file
        File outputFile = new File(fixedPath);
        if (!outputFile.exists()) {
            if (outputFile.createNewFile()) {
                JOptionPane.showMessageDialog(null, "File created successfully at: " + outputFile.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(null, "Failed to create the file at the specified location.");
                return;
            }
        }

        // Write the compressed content to the file
        FileWriter writer = new FileWriter(outputFile);
        writer.write(compressedContent);
        writer.close();

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "An error occurred while writing to the file.");
        e.printStackTrace();
    }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FileChooserExample().setVisible(true);
            }
        });
    }
}
