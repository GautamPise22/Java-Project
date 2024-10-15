package JavaMP;

import javax.swing.*;
import java.awt.*;

public class BillDetails extends JFrame {

    // Declare all labels as instance variables
    JLabel billIdDetails, nameDetails, mobileDetails, emailDetails, roomNoDetails, roomTypeDetails, totalBedsDetails, priceDetail, checkInDetails, checkOutDetails, noOfDaysDetails, totalAmountDetails;
    int roomId;

    public BillDetails() {
        // Set the title of the JFrame
        super("Hotel Bill");

        // JOptionPane
        roomId = Integer.parseInt(JOptionPane.showInputDialog("What is your room id?"));

        // Create a JPanel with a GridBagLayout for structured placement
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6); // More padding for better spacing
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally

        // Increase font size for all components
        Font largeFont = new Font("Arial", Font.PLAIN, 18); // Font with size 18

        // Bill Header
        JLabel hotelLabel = new JLabel("-: Saffron Sands Hotel :-", SwingConstants.CENTER);
        hotelLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Larger, bold font for title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(hotelLabel, gbc);

        // Separator
        JLabel separator1 = new JLabel("**********************************************", SwingConstants.CENTER);
        separator1.setFont(largeFont);
        gbc.gridy++;
        panel.add(separator1, gbc);

        // Bill Details
        JLabel billIdLabel = new JLabel("Bill ID:");
        billIdLabel.setFont(largeFont);
        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(billIdLabel, gbc);

        // Bill ID Details Label
        billIdDetails = new JLabel("1");  // Placeholder value
        billIdDetails.setFont(largeFont);
        gbc.gridx++;
        panel.add(billIdDetails, gbc);

        // Reset gbc.gridx for next row
        gbc.gridx = 0;

        // Customer Details
        JLabel customerDetailsLabel = new JLabel("Customer Details:");
        customerDetailsLabel.setFont(largeFont);
        gbc.gridy++;
        panel.add(customerDetailsLabel, gbc);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(largeFont);
        gbc.gridy++;
        panel.add(nameLabel, gbc);

        // Customer Name Details Label
        nameDetails = new JLabel("Gautam");  // Placeholder value
        nameDetails.setFont(largeFont);
        gbc.gridx++;
        panel.add(nameDetails, gbc);

        gbc.gridx = 0;

        JLabel mobileLabel = new JLabel("Mobile Number:");
        mobileLabel.setFont(largeFont);
        gbc.gridy++;
        panel.add(mobileLabel, gbc);

        // Customer Mobile Details Label
        mobileDetails = new JLabel("1234567899");  // Placeholder value
        mobileDetails.setFont(largeFont);
        gbc.gridx++;
        panel.add(mobileDetails, gbc);

        gbc.gridx = 0;



        // Separator
        JLabel separator2 = new JLabel("**********************************************", SwingConstants.CENTER);
        separator2.setFont(largeFont);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(separator2, gbc);

        // Room Details
        JLabel roomDetailsLabel = new JLabel("Room Details:");
        roomDetailsLabel.setFont(largeFont);
        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(roomDetailsLabel, gbc);

        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setFont(largeFont);
        gbc.gridy++;
        panel.add(roomNumberLabel, gbc);

        // Room Number Details Label
        roomNoDetails = new JLabel("1");  // Placeholder value
        roomNoDetails.setFont(largeFont);
        gbc.gridx++;
        panel.add(roomNoDetails, gbc);

        gbc.gridx = 0;

        JLabel roomTypeLabel = new JLabel("Type:");
        roomTypeLabel.setFont(largeFont);
        gbc.gridy++;
        panel.add(roomTypeLabel, gbc);

        // Room Type Details Label
        roomTypeDetails = new JLabel("AC");  // Placeholder value
        roomTypeDetails.setFont(largeFont);
        gbc.gridx++;
        panel.add(roomTypeDetails, gbc);

        gbc.gridx = 0;

        JLabel bedTypeLabel = new JLabel("Bed:");
        bedTypeLabel.setFont(largeFont);
        gbc.gridy++;
        panel.add(bedTypeLabel, gbc);

        // Bed Type Details Label
        totalBedsDetails = new JLabel("Single");  // Placeholder value
        totalBedsDetails.setFont(largeFont);
        gbc.gridx++;
        panel.add(totalBedsDetails, gbc);

        gbc.gridx = 0;

        JLabel checkInLabel = new JLabel("Check IN Date:");
        checkInLabel.setFont(largeFont);
        gbc.gridy++;
        panel.add(checkInLabel, gbc);

        // Check In Details Label
        checkInDetails = new JLabel("2022-05-08");  // Placeholder value
        checkInDetails.setFont(largeFont);
        gbc.gridx++;
        panel.add(checkInDetails, gbc);

        gbc.gridx = 0;

        JLabel checkOutLabel = new JLabel("Check OUT Date:");
        checkOutLabel.setFont(largeFont);
        gbc.gridy++;
        panel.add(checkOutLabel, gbc);

        // Check Out Details Label
        checkOutDetails = new JLabel("2022-05-09");  // Placeholder value
        checkOutDetails.setFont(largeFont);
        gbc.gridx++;
        panel.add(checkOutDetails, gbc);

        gbc.gridx = 0;

        JLabel numberOfDaysLabel = new JLabel("Number of Days:");
        numberOfDaysLabel.setFont(largeFont);
        gbc.gridy++;
        panel.add(numberOfDaysLabel, gbc);

        // Number of Days Details Label
        noOfDaysDetails = new JLabel("1");  // Placeholder value
        noOfDaysDetails.setFont(largeFont);
        gbc.gridx++;
        panel.add(noOfDaysDetails, gbc);

        gbc.gridx = 0;

        JLabel totalAmountLabel = new JLabel("Total Amount:");
        totalAmountLabel.setFont(largeFont);
        gbc.gridy++;
        panel.add(totalAmountLabel, gbc);

        // Total Amount Details Label
        totalAmountDetails = new JLabel("250.0");  // Placeholder value
        totalAmountDetails.setFont(largeFont);
        gbc.gridx++;
        panel.add(totalAmountDetails, gbc);

        // Separator
        JLabel separator3 = new JLabel("**********************************************", SwingConstants.CENTER);
        separator3.setFont(largeFont);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(separator3, gbc);

        // Footer
        JLabel footerLabel = new JLabel("Thank You, Please Visit Again.", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Slightly larger font for emphasis
        gbc.gridy++;
        panel.add(footerLabel, gbc);

        // Print Button
        JButton printButton = new JButton("Submit");
        printButton.setFont(new Font("Arial", Font.BOLD, 20)); // Bigger font for the button
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(printButton, gbc);

        // Add panel to the frame
        add(panel);

        // Set frame properties for larger size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 800); // Increased the size for better visibility
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private void handleRoomId(int roomId) {


    }

    public static void main(String[] args) {
        new BillDetails();
    }
}
