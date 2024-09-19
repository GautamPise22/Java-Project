package JavaMP;

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
        ImageIcon originalImage = new ImageIcon("images\\newlog.jpg");
        background = new JLabel(new ImageIcon(originalImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));

        // Set the background layout as BorderLayout so components can be positioned relative to edges
        background.setLayout(new BorderLayout());

        // Create a panel for the login form with a vertical BoxLayout
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(new Color(255, 255, 255, 180)); // Semi-transparent background to show the image behind
        loginPanel.setMaximumSize(new Dimension(250, 200)); // Set maximum size for the login panel
        loginPanel.setPreferredSize(new Dimension(250, 200)); // Set preferred size for the panel

        // Set padding for the components inside the login panel
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Top, left, bottom, right padding

        // Add username label and text field
        l1 = new JLabel("Username:");
        l1.setForeground(Color.green); // Set font color
        l1.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
        loginPanel.add(l1);
        loginPanel.add(Box.createVerticalStrut(10)); // Space between components

        usernameField = new JTextField(20);
        usernameField.setMaximumSize(new Dimension(200, 30)); // Set field size
        loginPanel.add(usernameField);
        loginPanel.add(Box.createVerticalStrut(10)); // Space between components

        // Add password label and password field
        l2 = new JLabel("Password:");
        l2.setForeground(Color.green); // Set font color
        l2.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
        loginPanel.add(l2);
        loginPanel.add(Box.createVerticalStrut(10)); // Space between components

        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(200, 30)); // Set field size
        loginPanel.add(passwordField);
        loginPanel.add(Box.createVerticalStrut(20)); // Space between components

        // Add login button
        loginButton = new JButton("Login");
        loginButton.setBackground(Color.green); // Button background color
        loginButton.setForeground(Color.black); // Button text color
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
        loginButton.addActionListener(new LoginButtonListener());
        loginPanel.add(loginButton);

        // Add a border around the login panel
        Border border = new LineBorder(new Color(179, 245, 183), 4, true);
        loginPanel.setBorder(border);

        // Create a wrapper panel to center the loginPanel horizontally and add vertical spacing
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.setOpaque(false); // Make the wrapper transparent to show the background

        // Add vertical spacing between the top of the window and the login panel
        wrapperPanel.add(Box.createVerticalStrut(100)); // Adjust the value to change the vertical spacing
        wrapperPanel.add(Box.createHorizontalStrut(110)); // Adjust the value to change the horizontal spacing
        wrapperPanel.add(loginPanel); // Add loginPanel to the wrapper centered
        wrapperPanel.add(Box.createVerticalGlue()); // Add vertical glue to push content upwards

        // Add the wrapper panel to the north of the background (centered horizontally)
        background.add(wrapperPanel, BorderLayout.NORTH);

        // Add the background to the frame
        add(background);

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