package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Check_Balance extends JFrame implements ActionListener {

    JButton back,check;
    static int pinnumber;
    JLabel text1;

    Check_Balance(int pinnumber){
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = ii.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 700, 700);
        add(image);

        JLabel text = new JLabel("Available Balance");
        text.setBounds(125, 220, 500, 35);
        text.setFont(new Font("Arial", Font.BOLD, 18));
        text.setForeground(Color.white);
        image.add(text);

        text1 = new JLabel("");
        text1.setBounds(235, 290, 500, 35);
        text1.setFont(new Font("Arial", Font.BOLD, 18));
        text1.setForeground(Color.white);
        image.add(text1);

        check = new JButton("Check Balance");
        check.setBackground(Color.white);
        check.setForeground(Color.black);
        check.setBounds(165, 360, 200, 25);
        check.addActionListener(this);
        image.add(check);

        back = new JButton("Back");
        back.setBackground(Color.white);
        back.setForeground(Color.black);
        back.setBounds(165, 390, 200, 25);
        back.addActionListener(this);
        image.add(back);



        setTitle("Deposit");
        setSize(700, 700);
        setVisible(true);
        setLocation(300, 0);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == check){
            Conn conn = new Conn();
            String selectQuery = "SELECT available FROM users WHERE pin = ?";
            String updateQuery = "UPDATE users SET available = ? WHERE pin = ?";
            String insertQuery = "INSERT INTO history(pin, date, type, amount) VALUES(?, ?, 'Deposit', ?)";

            try (Connection connection = conn.s.getConnection()) {
                // Select current available balance
                try (PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
                    selectStmt.setInt(1, pinnumber);
                    ResultSet rs = selectStmt.executeQuery();
                    int available = 0;
                    if (rs.next()) {
                        available = rs.getInt("available");
                    }

                    text1.setText(String.valueOf(available));
//                    setVisible(false);
//                    new Transactions(pinnumber).setVisible(true);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        if(e.getSource() == back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Check_Balance(pinnumber);
    }
}
