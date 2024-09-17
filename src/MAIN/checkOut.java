package MAIN;
import java.util.Arrays;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class checkOut extends JFrame implements ActionListener{
    JTextField tf;
    JLabel l, bg;
    JButton b;
    static int[] roomnumber = new int[51];
    public checkOut(){
        setTitle("Check Out");
        setVisible(true);
        setSize(400,400);
        setLayout(null);

        bg=new JLabel(new ImageIcon("C:\\Users\\koshe\\Desktop\\Java MP\\Java-Project\\images\\homeback.png"));

        ImageIcon originalImage = new ImageIcon("C:\\\\Users\\\\koshe\\\\Desktop\\\\Java MP\\\\Java-Project\\\\images\\\\homeback.png");
        bg = new JLabel(new ImageIcon(originalImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        bg.setBounds(0, 0, getWidth(), getHeight());
        
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

        

        tf= new JTextField(10);
        b=new JButton("Submit");
        l=new JLabel("Enter Room Number to Leave: ");
       add(l);add(tf);add(b);
        
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae){
        int room = Integer.parseInt(tf.getText());
        if (room>0 && room<51) {
            if (roomnumber[room] == -1) {
                JOptionPane.showMessageDialog(this, "Room " + room + " is already vacant! Please try valid Room Number", 
                "Not Found", JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(this, "Room " + room + " has been vacant. Thank you!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);    
            }
        } else {
            JOptionPane.showMessageDialog(this, "Room " + room + " is not available. Please try again!", 
                "Not Found", JOptionPane.ERROR_MESSAGE); 
        }
        
    }
    public static void main(String[] args) {
        new checkOut();
    }
}

