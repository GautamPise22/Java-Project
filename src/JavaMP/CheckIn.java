package JavaMP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckIn extends JFrame {
    private JPanel checkInPanel;
    private JLabel background;

    public CheckIn() {

        // Setting up the frame
        setTitle("Hotel Management System");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load and set the initial background image
        ImageIcon originalImage = new ImageIcon("images/checkInBackground.png");
        background = new JLabel(new ImageIcon(originalImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, getWidth(), getHeight());

        // Create a JLayeredPane to layer the background and panel
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 800));

        // Add the background to the lowest layer
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);
        background.setBounds(0, 0, getWidth(), getHeight());

        // Setting up the main panel with GridBagLayout
        checkInPanel = new JPanel(new GridBagLayout());
        checkInPanel.setOpaque(false); // Makes the panel transparent to show the background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 100, 15, 100); // Margin between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Customizing fonts
        Font labelFont = new Font("Arial", Font.PLAIN, 18); // New font for labels
        Font fieldFont = new Font("Arial", Font.PLAIN, 16); // Font for input fields

        // Adding labels and text fields with customized look
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(labelFont);
        JTextField nameField = createStyledTextField(20);

        JLabel checkInLabel = new JLabel("Check In Date");
        checkInLabel.setFont(labelFont);
        JSpinner checkInDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(checkInDateSpinner, "yyyy-MM-dd");
        checkInDateSpinner.setEditor(dateEditor);
        checkInDateSpinner.setFont(fieldFont);

        JLabel mobileLabel = new JLabel("Mobile Number");
        mobileLabel.setFont(labelFont);
        JTextField mobileField = createStyledTextField(20);

        JLabel roomTypeLabel = new JLabel("Room Type");
        roomTypeLabel.setFont(labelFont);
        JComboBox<String> roomTypeCombo = new JComboBox<>(new String[]{"AC", "Non-AC"});
        roomTypeCombo.setFont(fieldFont);

        JLabel bedSelectionLabel = new JLabel("Number of Beds");
        bedSelectionLabel.setFont(labelFont);
        JComboBox<String> bedCombo = new JComboBox<>(new String[]{"1", "2", "3"});
        bedCombo.setFont(fieldFont);

        JLabel genderSelectionLabel = new JLabel("Gender");
        genderSelectionLabel.setFont(labelFont);
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderCombo.setFont(fieldFont);

        JLabel nationalityLabel = new JLabel("Nationality");
        nationalityLabel.setFont(labelFont);
        JTextField nationalityField = createStyledTextField(20);

        JLabel aadharDetailsLabel = new JLabel("Aadhar");
        aadharDetailsLabel.setFont(labelFont);
        JTextField aadharField = createStyledTextField(20);

        JLabel daysToStayLabel = new JLabel("Number of days to stay");
        daysToStayLabel.setFont(labelFont);
        JTextField daysToStayField = createStyledTextField(20);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(labelFont);
        JTextArea addressArea = new JTextArea(5, 20);
        addressArea.setFont(fieldFont);
        addressArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Customizing Buttons with increased size
        JButton allotButton = createStyledButton("Allocate Room", Color.BLUE);
        JButton clearButton = createStyledButton("Clear", Color.RED);

        // Adding components to GridBagLayout with positioning
        gbc.gridy = 0;
        gbc.gridx = 0;
        checkInPanel.add(nameLabel, gbc);

        gbc.gridy = 1;
        checkInPanel.add(nameField, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        checkInPanel.add(mobileLabel, gbc);

        gbc.gridy = 1;
        checkInPanel.add(mobileField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        checkInPanel.add(genderSelectionLabel, gbc);

        gbc.gridy = 3;
        checkInPanel.add(genderCombo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        checkInPanel.add(nationalityLabel, gbc);

        gbc.gridy = 3;
        checkInPanel.add(nationalityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        checkInPanel.add(aadharDetailsLabel, gbc);

        gbc.gridy = 5;
        checkInPanel.add(aadharField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        checkInPanel.add(checkInLabel, gbc);

        gbc.gridy = 5;
        checkInPanel.add(checkInDateSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        checkInPanel.add(daysToStayLabel, gbc);

        gbc.gridy = 7;
        checkInPanel.add(daysToStayField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        checkInPanel.add(roomTypeLabel, gbc);

        gbc.gridy = 7;
        checkInPanel.add(roomTypeCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        checkInPanel.add(addressLabel, gbc);

        gbc.gridy = 9;
        checkInPanel.add(addressArea, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        checkInPanel.add(bedSelectionLabel, gbc);

        gbc.gridy = 9;
        checkInPanel.add(bedCombo, gbc);

        // Adding buttons
        gbc.gridx = 0;
        gbc.gridy = 10;
        checkInPanel.add(allotButton, gbc);

        gbc.gridx = 1;
        checkInPanel.add(clearButton, gbc);

        // Set bounds and layer for the checkInPanel and add to layeredPane
        checkInPanel.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(checkInPanel, JLayeredPane.PALETTE_LAYER); // Add to higher layer

        // Add the layeredPane to the frame
        setContentPane(layeredPane);

        // Add a ComponentListener to handle window resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get new dimensions of the window
                int newWidth = getWidth();
                int newHeight = getHeight();

                // Resize the background image
                ImageIcon resizedImage = new ImageIcon(originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
                background.setIcon(resizedImage);
                background.setBounds(0, 0, newWidth, newHeight);

                // Resize the checkInPanel to fill the window (centered)
                checkInPanel.setBounds(0, 0, newWidth, newHeight);
            }
        });
    }

    // Method to create a styled button
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(180, 50)); // Increased size for visual balance
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });

        return button;
    }

    // Method to create a styled text field
    private JTextField createStyledTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.BLACK);

        return textField;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CheckIn ui = new CheckIn();
            ui.setVisible(true);
        });
    }
}