package MAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class LoginPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel background, l1, l2;

    public LoginPage() {
    
        // Set up the frame
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load and scale the background image
        ImageIcon originalImage = new ImageIcon("Java-Project\\\\images\\\\Login.jpg");
        background = new JLabel(new ImageIcon(originalImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
    
        // Use GridBagLayout to place components over the background
        background.setLayout(new GridBagLayout());
    
        // Create a panel for the login form
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.white);
        loginPanel.setOpaque(true);
    
        // Set GridBagConstraints for form components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add username label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        l1 = new JLabel("Username:");
        l1.setForeground(Color.green); // Set font color
        loginPanel.add(l1, gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(20);
        loginPanel.add(usernameField, gbc);
    
        // Add password label and password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        l2 = new JLabel("Password:");
        l2.setForeground(Color.green); // Set font color
        loginPanel.add(l2, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        loginPanel.add(passwordField, gbc);

        // Add login button
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginButton = new JButton("Login");
        loginButton.setBackground(Color.green); // Button background color
        loginButton.setForeground(Color.black); // Button text color
        loginButton.addActionListener(new LoginButtonListener());
        loginPanel.add(loginButton, gbc);
    
        // Add a border around the login panel
        Border border = new LineBorder(new Color(179, 245, 183), 4, true);
        loginPanel.setBorder(border);
    
        // Add the login panel on top of the background
        background.add(loginPanel);
    
        // Add the background to the frame
        add(background, BorderLayout.CENTER);
    
        // Listen for window resize to adjust background image
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Resize the background image to match new window dimensions
                int newWidth = getWidth();
                int newHeight = getHeight();
                ImageIcon resizedImage = new ImageIcon(originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
                background.setIcon(resizedImage);
            }
        });

        // Make the frame visible
        setVisible(true);       
    }

    // Action listener for the login button
    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get username and password input
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            System.out.println("Username: " + username + ", Password: " + password);
            // Add your login validation logic here
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
