package JavaMP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

class PhoneException extends Exception {
    PhoneException(String s) {
        super(s);
    }
}

class AadharException extends Exception {
    AadharException(String s) {
        super(s);
    }
}

public class CheckIn extends JFrame implements ActionListener {
    private JPanel checkInPanel;
    private JLabel background;
    private JTextField nameField, mobileField, nationalityField, aadharField, daysToStayField;
    private JSpinner checkInDateSpinner;
    private JComboBox<String> roomTypeCombo, bedCombo, genderCombo;
    private JTextArea addressArea;
    private JButton clearButton, allotButton, homeButton;

    public CheckIn() {
        // Setting up the frame
        setTitle("Saffron Sands Hotel Check In");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load and set the initial background image
        ImageIcon originalImage = new ImageIcon("images/checkInBackground.png");
        background = new JLabel(
                new ImageIcon(originalImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
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
        nameField = createStyledTextField(20);

        JLabel checkInLabel = new JLabel("Check In Date");
        checkInLabel.setFont(labelFont);
        checkInDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(checkInDateSpinner, "yyyy-MM-dd");
        checkInDateSpinner.setEditor(dateEditor);
        checkInDateSpinner.setFont(fieldFont);

        JLabel mobileLabel = new JLabel("Mobile Number");
        mobileLabel.setFont(labelFont);
        mobileField = createStyledTextField(20);

        JLabel roomTypeLabel = new JLabel("Room Type");
        roomTypeLabel.setFont(labelFont);
        roomTypeCombo = new JComboBox<>(new String[] { "AC", "Non-AC" });
        roomTypeCombo.setFont(fieldFont);

        JLabel bedSelectionLabel = new JLabel("Number of Beds");
        bedSelectionLabel.setFont(labelFont);
        bedCombo = new JComboBox<>(new String[] { "1", "2", "3" });
        bedCombo.setFont(fieldFont);

        JLabel genderSelectionLabel = new JLabel("Gender");
        genderSelectionLabel.setFont(labelFont);
        genderCombo = new JComboBox<>(new String[] { "Male", "Female", "Other" });
        genderCombo.setFont(fieldFont);

        JLabel nationalityLabel = new JLabel("Nationality");
        nationalityLabel.setFont(labelFont);
        nationalityField = createStyledTextField(20);

        JLabel aadharDetailsLabel = new JLabel("Aadhar");
        aadharDetailsLabel.setFont(labelFont);
        aadharField = createStyledTextField(20);

        JLabel daysToStayLabel = new JLabel("Number of days to stay");
        daysToStayLabel.setFont(labelFont);
        daysToStayField = createStyledTextField(20);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(labelFont);
        addressArea = new JTextArea(5, 20);
        addressArea.setFont(fieldFont);
        addressArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Customizing Buttons with increased size
        allotButton = createStyledButton("Allocate Room", Color.BLUE);
        clearButton = createStyledButton("Clear", Color.RED);
        homeButton = createStyledButtonWithIcon("Home", "images\\homeicon.jpg"); // Add the home button with an icon

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
        allotButton.addActionListener(this);

        gbc.gridx = 1;
        checkInPanel.add(clearButton, gbc);
        clearButton.addActionListener(this);

        homeButton.setBounds(10, 10, 10, 10); // Set the position and size (x, y, width, height)
        checkInPanel.add(homeButton); // Add to the panel
        homeButton.addActionListener(this);

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
                ImageIcon resizedImage = new ImageIcon(
                        originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
                background.setIcon(resizedImage);
                background.setBounds(0, 0, newWidth, newHeight);

                // Resize the checkInPanel to fill the window (centered)
                checkInPanel.setBounds(0, 0, newWidth, newHeight);
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == homeButton) {
            HotelManagementUI hi = new HotelManagementUI();
            hi.setVisible(true);
            dispose();
        }
        if (ae.getSource() == clearButton) {
            // Clear all fields
            nameField.setText("");
            mobileField.setText("");
            nationalityField.setText("");
            aadharField.setText("");
            daysToStayField.setText("");
            addressArea.setText("");
            checkInDateSpinner.setValue(new Date());
            roomTypeCombo.setSelectedIndex(0);
            bedCombo.setSelectedIndex(0);
            genderCombo.setSelectedIndex(0);
        }

        if (ae.getSource() == allotButton) {
            String name = nameField.getText();
            String mobile = mobileField.getText();
            String nationality = nationalityField.getText();
            String aadhar = aadharField.getText();
            String daysToStay = daysToStayField.getText();
            String address = addressArea.getText();
            Date checkInDate = (Date) checkInDateSpinner.getValue();
            String roomType = (String) roomTypeCombo.getSelectedItem();
            String numberOfBeds = (String) bedCombo.getSelectedItem();
            String gender = (String) genderCombo.getSelectedItem();

            // Basic validation
            if (name.isEmpty() || mobile.isEmpty() || nationality.isEmpty() || aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!length(mobile, 10) || !length(aadhar, 12)) {
                try {
                    if (!length(mobile, 10))
                        throw new PhoneException("Enter valid Phone Number.");
                    if (!length(aadhar, 12))
                        throw new AadharException("Enter valid Aadhar Card Number.");
                } catch (AadharException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (PhoneException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Database connection
                String url = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12737707";
                String user = "sql12737707";
                String password = "1FaNC3IdnW";

                try (Connection con = DriverManager.getConnection(url, user, password)) {
                    int roomNo = getAvailableRoomNumber(con, roomType); // Fetch an available room number
                    String sql = "INSERT INTO Customer (c_name, c_mobileNo, c_gender, c_nationality, c_aadhar, c_checkInDate, c_checkOutDate, c_noOfDays, c_roomType, c_address, c_noOfBeds, c_roomNoAllocated, OccupiedOrNot, c_roomNoWhichWasAllocated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    try (PreparedStatement pst = con.prepareStatement(sql)) {
                        pst.setString(1, name); // c_name
                        pst.setString(2, mobile); // c_mobileNo
                        pst.setString(3, gender); // c_gender
                        pst.setString(4, nationality); // c_nationality
                        pst.setString(5, aadhar); // c_aadhar
                        pst.setDate(6, new java.sql.Date(checkInDate.getTime())); // c_checkInDate
                        pst.setDate(7, null); // c_checkOutDate
                        pst.setInt(8, Integer.parseInt(daysToStay)); // c_noOfDays
                        pst.setString(9, roomType); // c_roomType
                        pst.setString(10, address); // c_address
                        pst.setInt(11, Integer.parseInt(numberOfBeds)); // c_noOfBeds
                        pst.setInt(12, roomNo); // c_roomNoAllocated
                        pst.setBoolean(13, true); // OccupiedOrNot
                        pst.setInt(14, 0); // c_roomNoWhichWasAllocated (if needed)

                        // Execute the update
                        pst.executeUpdate();

                        // Update the hotel table to mark the room as occupied and set the occupant's
                        // name
                        String updateSql = "UPDATE Hotel SET Occupied = true, OccupiedBy = ? WHERE RoomNo = ?";
                        try (PreparedStatement updatePst = con.prepareStatement(updateSql)) {
                            updatePst.setString(1, name); // Set the name of the person occupying the room
                            updatePst.setInt(2, roomNo); // Set the room number being updated
                            updatePst.executeUpdate();
                        }
                        JOptionPane.showMessageDialog(this,
                                "Room allocated successfully! Your Room Number is " + roomNo, "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error while allocating room: " + e.getMessage(),
                            "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Method to create styled text fields
    private JTextField createStyledTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        return textField;
    }

    // Method to create styled buttons
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 50));
        return button;
    }

    private JButton createStyledButtonWithIcon(String text, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(50, 50)); // Set the size of the button
        button.setContentAreaFilled(false); // Transparent background
        button.setBorderPainted(false); // Remove border
        return button;
    }

    // Method to get an available room number based on room type
    private int getAvailableRoomNumber(Connection con, String roomType) throws SQLException {
        // Determine the room number range based on room type
        String query;
        if (roomType.equals("AC")) {
            query = "SELECT RoomNo FROM Hotel WHERE Occupied = false AND RoomNo BETWEEN 1 AND 25 LIMIT 1";
        } else { // Assuming Non-AC
            query = "SELECT RoomNo FROM Hotel WHERE Occupied = false AND RoomNo BETWEEN 26 AND 50 LIMIT 1";
        }

        try (Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("RoomNo");
            } else {
                throw new SQLException("No available rooms.");
            }
        }
    }

    private boolean length(String str, int k) {
        int c;
        for (int j = 0; j < str.length(); j++) {
            c = str.charAt(j);
            if (c >= 48 && c <= 57 && str.length() == k) {
                return true;
            }
        }
        return false;
    }
}
