package MAIN;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel backgroundLabel;

    public LoginPage() {
        // Set up the frame
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a label for the background image
        backgroundLabel = new JLabel(new ImageIcon("green-natural-background-vector-illustration-59110.jpg")); // Replace with your image path
        backgroundLabel.setLayout(new GridBagLayout()); // Use layout on JLabel to place the form
        add(backgroundLabel); // Add the label directly to the frame

        // Create a panel for the login form
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setOpaque(false); // Make the panel transparent to allow the background to show through

        // Create a GridBagConstraints for the login form components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add the username label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        usernameField = new JTextField(20);
        loginPanel.add(usernameField, gbc);

        // Add the password label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        passwordField = new JPasswordField(20);
        loginPanel.add(passwordField, gbc);

        // Add the login button
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginButton = new JButton("Login");
        loginButton.setForeground(Color.RED); // Set the login button text color to red
        loginButton.addActionListener(new LoginButtonListener());
        loginPanel.add(loginButton, gbc);

        // Add the login panel to the background label (so it appears on top of the background)
        backgroundLabel.add(loginPanel, gbc);

        // Make the frame visible
        setVisible(true);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
// Handle the login button click event
String username = usernameField.getText();
String password = new String(passwordField.getPassword());
System.out.println("Username: " + username + ", Password: " + password);
// Add your login logic here
}
}

public static void main(String[] args) {
new LoginPage();
}
}