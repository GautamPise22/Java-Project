package JavaMP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DatabaseTables extends JFrame implements ActionListener{

    JTable customerTable, hotelTable;
    JScrollPane customerScrollPane, hotelScrollPane;
    Connection con;
    JButton homeButton;

    public DatabaseTables() {
        setTitle("Database Table Display");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        setLocationRelativeTo(null);

        // Initialize database connection
        try {
            con = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12739584", "sql12739584", "UIu7Hjemb9");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Create a top panel with a light blue background
        JPanel topPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(173, 216, 230)); // Light blue color
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align button to the left
        homeButton = createStyledButtonWithIcon("Home", "images\\homeicon.jpg");
        topPanel.add(homeButton);

        // Initialize tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add Customer Table
        DefaultTableModel customerModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the table non-editable
            }
        };
        customerTable = new JTable(customerModel);
        customerScrollPane = new JScrollPane(customerTable);
        fetchTableData("Customer", customerModel);
        tabbedPane.addTab("Customer", customerScrollPane);

        // Add Hotel Table
        DefaultTableModel hotelModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the table non-editable
            }
        };
        hotelTable = new JTable(hotelModel);
        hotelScrollPane = new JScrollPane(hotelTable);
        fetchTableData("Hotel", hotelModel);
        tabbedPane.addTab("Hotel", hotelScrollPane);

        // Set layout and add components
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);  // Add button panel with light blue background at the top
        add(tabbedPane, BorderLayout.CENTER);  // Add tabbed pane in the center
        homeButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == homeButton) {
            HotelManagementUI hi = new HotelManagementUI();
            hi.setVisible(true);
            dispose();
        }
    }

    private void fetchTableData(String tableName, DefaultTableModel model) {
        try {
            // Prepare SQL query to fetch all data from the specified table
            String query = "SELECT * FROM " + tableName;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Fetch metadata to get column names
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Add column names to the table model
            model.setRowCount(0);  // Clear the model
            model.setColumnCount(0);  // Clear previous columns
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rsmd.getColumnName(i));
            }

            // Add rows to the table model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JButton createStyledButtonWithIcon(String text, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(30, 30)); // Set the size of the button
        button.setContentAreaFilled(false); // Transparent background
        button.setBorderPainted(false); // Remove border
        return button;
    }

}
