package JavaMP;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.Date;

public class checkOut extends JFrame implements ActionListener {
    JTextField tf;
    JLabel l, bg;
    JButton b, homeButton;
    Connection con; // Database connection

    public checkOut() {
        // Initialize database connection
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12737707", "sql12737707","1FaNC3IdnW");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        setTitle("Saffron Sands Hotel Check Out");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Using null layout

        // Load and set background image
        ImageIcon originalImage = new ImageIcon("images\\checkouthotel.png");
        bg = new JLabel(
                new ImageIcon(originalImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        bg.setBounds(0, 0, getWidth(), getHeight());

        // Add component listener to resize the background image and adjust component
        // positions
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newWidth = getWidth();
                int newHeight = getHeight();
                ImageIcon resizedImage = new ImageIcon(
                        originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
                bg.setIcon(resizedImage);
                bg.setBounds(0, 0, newWidth, newHeight);
                centerComponents(newWidth, newHeight);
            }
        });

        // Initialize components
        tf = createStyledTextField(20);
        b = createCustomButton(" Submit ", Color.decode("#FFCC33"));
        l = new JLabel("Enter Room Number to Leave: ");
        l.setFont(new Font("Arial", Font.PLAIN, 18));
        b.setBackground(Color.GREEN);
        homeButton = createStyledButtonWithIcon("Home", "images\\homeicon.jpg");

        // Set bounds for each component
        l.setBounds(20, 120, 200, 30);
        tf.setBounds(220, 120, 150, 30);
        b.setBounds(150, 170, 100, 30);
        homeButton.setBounds(10, 10, 30, 30);

        // Add components to the frame
        add(l);
        add(tf);
        add(b);
        add(homeButton);
        add(bg); // Add background image label last to ensure it is on top

        // Add action listener to the button
        b.addActionListener(this);
        homeButton.addActionListener(this);
        // Initial centering of components
        centerComponents(getWidth(), getHeight());
    }

    private void centerComponents(int width, int height) {
        int labelWidth = l.getPreferredSize().width;
        int labelHeight = l.getPreferredSize().height;
        int textFieldWidth = tf.getPreferredSize().width;
        int textFieldHeight = tf.getPreferredSize().height;
        int buttonWidth = b.getPreferredSize().width;
        int buttonHeight = b.getPreferredSize().height;

        int centerX = width / 2;
        int centerY = height / 2;

        l.setBounds(centerX - labelWidth / 2, centerY - labelHeight / 2 - 40, labelWidth, labelHeight);
        tf.setBounds(centerX - textFieldWidth / 2, centerY - textFieldHeight / 2, textFieldWidth, textFieldHeight);
        b.setBounds(centerX - buttonWidth / 2, centerY - buttonHeight / 2 + 40, buttonWidth, buttonHeight);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b) {
            try {
                int room = Integer.parseInt(tf.getText());
                if (room > 0 && room < 51) {
                    // Check if the room is currently occupied
                    String checkRoomSql = "SELECT Occupied, OccupiedBy FROM Hotel WHERE RoomNo = ?";
                    try (PreparedStatement checkRoomPst = con.prepareStatement(checkRoomSql)) {
                        checkRoomPst.setInt(1, room);
                        ResultSet rs = checkRoomPst.executeQuery();

                        if (rs.next()) {
                            boolean isOccupied = rs.getBoolean("Occupied");
                            String occupiedBy = rs.getString("OccupiedBy");

                            if (!isOccupied) {
                                JOptionPane.showMessageDialog(this,
                                        "Room " + room + " is already vacant! Please try a valid Room Number.",
                                        "Not Found", JOptionPane.ERROR_MESSAGE);
                            } else {
                                // Proceed with checkout
                                // Update the hotel table to mark the room as not occupied and clear the
                                // occupied by field
                                String updateHotelSql = "UPDATE Hotel SET Occupied = false, OccupiedBy = NULL WHERE RoomNo = ?";
                                try (PreparedStatement updateHotelPst = con.prepareStatement(updateHotelSql)) {
                                    updateHotelPst.setInt(1, room);
                                    updateHotelPst.executeUpdate();
                                }

                                // Update the customer record
                                String updateCustomerSql = "UPDATE Customer SET OccupiedOrNot = false, CheckoutDate = ?, roomNoAllocated = 0 WHERE c_name = ?";
                                try (PreparedStatement updateCustomerPst = con.prepareStatement(updateCustomerSql)) {
                                    updateCustomerPst.setDate(1, new java.sql.Date(new Date().getTime())); // Current
                                                                                                           // date
                                    updateCustomerPst.setString(2, occupiedBy);
                                    updateCustomerPst.executeUpdate();
                                }

                                JOptionPane.showMessageDialog(this, "Room " + room + " has been vacated. Thank you!",
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Room " + room + " does not exist. Please try again!",
                                    "Not Found", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Room " + room + " is not available. Please try again!",
                            "Not Found", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid room number.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error occurred. Please try again.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (ae.getSource() == homeButton) {
            HotelManagementUI hi = new HotelManagementUI();
            hi.setVisible(true);
            dispose();
        }
    }

    private JButton createStyledButtonWithIcon(String text, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(50, 50)); // Set the size of the button
        button.setContentAreaFilled(false); // Transparent background
        button.setBorderPainted(false); // Remove border
        return button;
    }

    private JButton createCustomButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private JTextField createStyledTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.BLACK);

        return textField;
    }

    
}
