package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {

    static int pinnumber;
    JTextField amount;
    JButton withdraw,back;
    Withdrawl(int pinnumber){
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=ii.getImage().getScaledInstance(700,700,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        image.setBounds(0,0,700,700);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setBounds(125,220,500,35);
        text.setFont(new Font("Arial",Font.BOLD,14));
        text.setForeground(Color.white);
        image.add(text);

        amount = new JTextField();
        amount.setBounds(165,290,200,30);
        amount.setBackground(Color.white);
        add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBackground(Color.white);
        withdraw.setBounds(165,360,200,25);
        withdraw.setForeground(Color.black);
        withdraw.addActionListener(this);
        image.add(withdraw);

        back = new JButton("Back");
        back.setBackground(Color.white);
        back.setForeground(Color.black);
        back.setBounds(165,390,200,25);
        back.addActionListener(this);
        image.add(back);


        setTitle("Withdrawl");
        setSize(700,700);
        setVisible(true);
        setLocation(300,00);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }

        if(e.getSource() == withdraw){
            int num = Integer.parseInt(amount.getText());
            Date date = new Date();

            if(num == 0){
                JOptionPane.showMessageDialog(null,"Please Enter Amount First");
            }else{
                Conn conn = new Conn();
                String selectQuery = "SELECT available FROM users WHERE pin = ?";
                String updateQuery = "UPDATE users SET available = ? WHERE pin = ?";
                String insertQuery = "INSERT INTO history(pin, date, type, amount) VALUES(?, ?, 'Withdraw', ?)";

                try (Connection connection = conn.s.getConnection()) {
                    // Select current available balance
                    try (PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
                        selectStmt.setInt(1, pinnumber);
                        ResultSet rs = selectStmt.executeQuery();
                        int available = 0;
                        if (rs.next()) {
                            available = rs.getInt("available");
                        }

                        if(available < num){
                            JOptionPane.showMessageDialog(null,"Insufficient Balance");
                        }else{
                            int newAvailable = available - num;
                            try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                                updateStmt.setInt(1, newAvailable);
                                updateStmt.setInt(2, pinnumber);
                                updateStmt.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Rs " + num + " withdraw successfully");

                            }

                            // Insert transaction history
                            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                                insertStmt.setInt(1, pinnumber);
                                insertStmt.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
                                insertStmt.setInt(3, num);
                                insertStmt.executeUpdate();
                            }

                        }

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        new Withdrawl(pinnumber);
    }
}
