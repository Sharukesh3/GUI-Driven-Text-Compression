import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class FileChooserExample extends JFrame {
    private JButton chooseFileButton;
    private JLabel fileNameLabel;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JComboBox<String> algorithmComboBox;
    private JButton compressButton;
    private JButton decompressButton;
    private StringBuilder fileContent = new StringBuilder();
    private String compressedContent;
    private List<Triple> compressedLZ77;
    private List<Integer> compressedLZW;

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

        decompressButton = new JButton("Decompress");
        decompressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                if (selectedAlgorithm.equals("Choose an algorithm")) {
                    JOptionPane.showMessageDialog(null, "Please select an algorithm.");
                    return;
                }

                // Call the decompress function with the selected algorithm
                decompress(selectedAlgorithm);
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

        // Create bottom panel and sub-panels
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Create left panel for "Choose File" button
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(chooseFileButton);

        // Create center panel for algorithm combo box
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(algorithmComboBox);

        // Create right panel for "Compress" and "Decompress" buttons
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(compressButton);
        rightPanel.add(decompressButton);

        // Add sub-panels to bottom panel
        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(centerPanel, BorderLayout.CENTER);
        bottomPanel.add(rightPanel, BorderLayout.EAST);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2)); // Split into two columns
        mainPanel.add(inputPanel);
        mainPanel.add(outputPanel);

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
        if (algorithm.equals("Huffman")) {
            compressedContent = compresser.getCompressed();
            outputTextArea.setText(compressedContent);
        } else if (algorithm.equals("LZ77")) {
            compressedLZ77 = compresser.getCompressedLZ77();
            outputTextArea.setText(compressedLZ77.toString());
        } else if (algorithm.equals("LZW")) {
            compressedLZW = compresser.getCompressedLZW();
            outputTextArea.setText(compressedLZW.toString());
        } else {
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
            try (FileWriter writer = new FileWriter(outputFile)) {
                if (algorithm.equals("Huffman")) {
                    writer.write(compressedContent);
                } else if (algorithm.equals("LZ77")) {
                    writer.write(compressedLZ77.toString());
                } else if (algorithm.equals("LZW")) {
                    writer.write(compressedLZW.toString());
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    private void decompress(String algorithm) {
        JOptionPane.showMessageDialog(null, "Decompressing file using " + algorithm);
        decompression decompressor;
        String decompressedContent;
        if (algorithm.equals("Huffman")) {
            decompressor = new decompression(algorithm, compressedContent);
        } else if (algorithm.equals("LZ77")) {
            decompressor = new decompression(algorithm, compressedLZ77);
        } else if (algorithm.equals("LZW")) {
            decompressor = new decompression(algorithm, compressedLZW);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid decompression algorithm.");
            return;
        }
        decompressedContent = decompressor.getDecompressed();
        outputTextArea.setText(decompressedContent);
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
