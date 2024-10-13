package JavaMP;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.*;

public class HotelProgressBar extends JFrame {

    private JProgressBar progressBar;
    private JLabel background;

    public HotelProgressBar() {
        // Set up the JFrame
        setTitle("Saffron Sands Hotel Launching Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Apply BorderLayout
        

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
        
        // Create a panel for the progress bar
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false); // Make the panel transparent
        
        // Create the progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(300, 30)); // Set preferred size
        progressBar.setStringPainted(true); // Show the percentage text
        progressBar.setForeground(Color.GREEN); // Set progress bar color

        // Create a label for the hotel title
        JLabel hotelTitle = new JLabel("Saffron Sands Hotel");
        hotelTitle.setForeground(Color.BLACK);
        hotelTitle.setFont(new Font("Serif", Font.BOLD, 24));
        hotelTitle.setHorizontalAlignment(SwingConstants.CENTER);

        // Add components to the panel
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(20, 0, 20, 0);
        gc.gridx = 0;
        gc.gridy = 0;
        panel.add(hotelTitle, gc);

        gc.gridy=1;
        panel.add(progressBar, gc);

         // Set bounds and layer for the checkInPanel and add to layeredPane
         panel.setBounds(0, 0, getWidth(), getHeight());
         layeredPane.add(panel, JLayeredPane.PALETTE_LAYER); // Add to higher layer

        // Add the layeredPane to the frame
        setContentPane(layeredPane);
        
        // Start the progress bar simulation
        startProgress();
        
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

                
                panel.setBounds(0, 0, newWidth, newHeight);
            }
        });
    }

    private void startProgress() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int progressValue = 0;

            @Override
            public void run() {
                if (progressValue < 100) {
                    progressValue++;
                    progressBar.setValue(progressValue);
                    progressBar.setString("Launching Application... " + progressValue + "%");
                } else {
                    timer.cancel(); // Stop the timer when progress reaches 100
                    LoginPage lp=new LoginPage();
                    lp.setVisible(true);
                    dispose();
                }
            }
        };

        // Schedule the task to run every 100 milliseconds
        timer.scheduleAtFixedRate(task, 0, 100);
    }

    public static void main(String[] args) {
        // Run the GUI in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            HotelProgressBar progressBarFrame = new HotelProgressBar();
            progressBarFrame.setVisible(true);
        });
    }
}
