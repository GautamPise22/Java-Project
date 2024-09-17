package MAIN;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;

public class checkOut extends JFrame implements ActionListener {
    JTextField tf;
    JLabel l, bg;
    JButton b;
    static int[] roomnumber = new int[51];

    public checkOut() {
        setTitle("Check Out");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Using null layout, which requires manual placement of components

        // Load and set background image
        ImageIcon originalImage = new ImageIcon("Java-Project\\images\\checkOutHome.png");
        bg = new JLabel(new ImageIcon(originalImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        bg.setBounds(0, 0, getWidth(), getHeight());

        // Add component listener to resize the background image when the window is resized
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get new dimensions of the window
                int newWidth = getWidth();
                int newHeight = getHeight();

                // Resize the background image
                ImageIcon resizedImage = new ImageIcon(originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
                bg.setIcon(resizedImage);
                bg.setBounds(0, 0, newWidth, newHeight);
            }
        });

        Arrays.fill(roomnumber, 0);

        // Initialize components
        tf = new JTextField(10);
        b = new JButton("Submit");
        l = new JLabel("Enter Room Number to Leave: ");

        // Set bounds for each component
        l.setBounds(20, 20, 200, 30);
        tf.setBounds(220, 20, 150, 30);
        b.setBounds(150, 70, 100, 30);

        // Add components to the frame
        add(l);
        add(tf);
        add(b);
        add(bg); // Add background image label last to ensure it is on top of other components

        // Add action listener to the button
        b.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            int room = Integer.parseInt(tf.getText());
            if (room > 0 && room < 51) {
                if (roomnumber[room] == -1) {
                    JOptionPane.showMessageDialog(this, "Room " + room + " is already vacant! Please try valid Room Number", 
                        "Not Found", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Room " + room + " has been vacant. Thank you!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);    
                }
            } else {
                JOptionPane.showMessageDialog(this, "Room " + room + " is not available. Please try again!", 
                    "Not Found", JOptionPane.ERROR_MESSAGE); 
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid room number.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            checkOut ui = new checkOut();
            ui.setVisible(true);
        });
    }
}