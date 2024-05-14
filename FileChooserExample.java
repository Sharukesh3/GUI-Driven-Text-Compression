import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileChooserExample extends JFrame {
    private JButton chooseFileButton;
    private JLabel fileNameLabel;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JComboBox<String> algorithmComboBox;
    private JButton compressButton;
    private StringBuilder fileContent = new StringBuilder();

    public FileChooserExample() {
        setTitle("File Chooser Example");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        chooseFileButton = new JButton("Choose File");
        fileNameLabel = new JLabel("Selected File: ");
        inputTextArea = new JTextArea();
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        
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
        topPanel.add(fileNameLabel, BorderLayout.NORTH);
        
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input"));
        
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));

        JPanel algorithmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        algorithmPanel.add(algorithmComboBox);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(chooseFileButton);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.WEST);
        mainPanel.add(outputPanel, BorderLayout.EAST);
        mainPanel.add(algorithmPanel, BorderLayout.SOUTH);
        
        // Adding separator between input and output
        mainPanel.add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.CENTER);

        // Adding both button and dropdown panels to the bottom
        JPanel bottomButtonPanel = new JPanel(new BorderLayout());
        bottomButtonPanel.add(buttonPanel, BorderLayout.WEST);
        bottomButtonPanel.add(compressButton, BorderLayout.EAST);
        bottomButtonPanel.add(algorithmPanel, BorderLayout.CENTER);
        mainPanel.add(bottomButtonPanel, BorderLayout.SOUTH);

        // Adding main panel to the frame
        add(mainPanel);
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

    // Dummy compress function
    private void compress(String algorithm) {
        // You can replace this with your actual compression logic
        JOptionPane.showMessageDialog(null, "Compressing file using " + algorithm);
        compression compresser = new compression(algorithm, inputTextArea.getText());
        if (algorithm.equals("Huffman") || algorithm.equals("LZW")){
            outputTextArea.setText(compresser.getCompressed());
        }
        else if (algorithm.equals("LZ77")){
            outputTextArea.setText(compresser.getCompressed());
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
