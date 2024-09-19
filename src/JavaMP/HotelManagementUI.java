package JavaMP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HotelManagementUI extends JFrame {

    private JLabel background;
    private JPanel buttonPanel;

    public HotelManagementUI() {   
           
        // Setting up the frame
        setTitle("Hotel Management System");
        setSize(800, 600); // Initial window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null); // Use null layout for custom positioning

        // Load and set the initial background image
        ImageIcon originalImage = new ImageIcon("Java-Project\\images\\homeback.png");
        background = new JLabel(new ImageIcon(originalImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, getWidth(), getHeight());

        // Creating the button panel on the right
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 rows, 1 column
        buttonPanel.setOpaque(false); // Transparent panel
        buttonPanel.setBounds(getWidth() - 200, 50, 180, 400); // Adjust based on window size

        // Adding buttons
        JButton manageRoomButton = createCustomButton("Manage Room", Color.decode("#FFCC00"));
        JButton checkInButton = createCustomButton("Customer Check In", Color.decode("#FF6600"));
        JButton checkOutButton = createCustomButton("Customer Check Out", Color.decode("#FFCC33"));
        JButton billButton = createCustomButton("Customer Details Bill", Color.decode("#FFCC99"));
        JButton logoutButton = createCustomButton("", Color.decode("#FF3366"));

        // Adding icons to the buttons
        manageRoomButton.setIcon(new ImageIcon("Java-Project\\images\\manage.png"));
        checkInButton.setIcon(new ImageIcon("Java-Project\\images\\checkin.png"));
        checkOutButton.setIcon(new ImageIcon("Java-Project\\\\images\\\\checkout.png"));
        billButton.setIcon(new ImageIcon("Java-Project\\\\images\\\\bill.png"));
        logoutButton.setIcon(new ImageIcon("Java-Project\\images\\logoutHome.png"));
        makeButtonTransparent(logoutButton); // Transparent logout button

        // Add buttons to the panel
        buttonPanel.add(manageRoomButton);
        buttonPanel.add(checkInButton);
        buttonPanel.add(checkOutButton);
        buttonPanel.add(billButton);
        buttonPanel.add(logoutButton);

        // Add components to the frame
        add(buttonPanel);
        add(background);

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

                // Adjust button panel position and size based on the new width and height
                buttonPanel.setBounds(newWidth - 200, 50, 180, newHeight - 200);
            }
        });

        
    }

    // Transparent Button method
    private void makeButtonTransparent(JButton button) {
        button.setContentAreaFilled(false);  
        button.setBorderPainted(false);     
        button.setOpaque(false);             
    }

    private JButton createCustomButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFocusPainted(false); // Remove focus border
        button.setContentAreaFilled(true); // Fill the button area with color
        button.setOpaque(true); // Make sure the button is opaque
        button.setBackground(bgColor); // Set background color
        button.setForeground(Color.WHITE); // Set text color
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Set custom font
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Create a border

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker()); // Darken color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor); // Reset to original color
            }
        });

        return button;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelManagementUI ui = new HotelManagementUI();
            ui.setVisible(true);
        });
    }
}