package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {

    JButton login,signup,clear;
    JTextField mtf;
    JPasswordField ptf;
    Login(){

        setLayout(null);
        ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = ii.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);


        JLabel label = new JLabel(i3);
        label.setBounds(70,10,100,100);
        add(label);

        JLabel text = new JLabel("Welcome to Kishu's Bank");
        text.setFont(new Font("Osword",Font.BOLD,30));
        text.setBounds(200,40,400,40);
        add(text);

        JLabel mobileno = new JLabel("Mobile No.");
        mobileno.setFont(new Font("Osword",Font.BOLD,24));
        mobileno.setBounds(120,150,150,40);
        add(mobileno);

        mtf= new JTextField();
        mtf.setBounds(300,150,250,30);
        add(mtf);

        JLabel PIN = new JLabel("PIN");
        PIN.setFont(new Font("Osword",Font.BOLD,24));
        PIN.setBounds(120,220,400,40);
        add(PIN);

         ptf= new JPasswordField();
        ptf.setBounds(300,230,250,30);
        add(ptf);

         login = new JButton("Sign In");
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.setBounds(300,300,100,30);
        login.addActionListener(this);
        add(login);

         signup = new JButton("Sign Up");
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.setBounds(450,300,100,30);
        signup.addActionListener(this);
        add(signup);

         clear = new JButton("Clear");
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.setBounds(300,370,250,30);
        clear.addActionListener(this);
        add(clear);



        setTitle("ATM Machine");
        setSize(700,700);
        setVisible(true);
        setLocation(300,50);
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clear){
            mtf.setText("");
            ptf.setText("");

        }else if(e.getSource() == login){
            Conn conn = new Conn();
            String mn = mtf.getText();
            int pass = Integer.parseInt(ptf.getText());

            String query = "select * from users where mobile ='"+mn+"' and PIN ='"+pass+"'";

            try {
               ResultSet rs = conn.s.executeQuery(query);
               if(rs.next()){
                   setVisible(false);
                   new Transactions(pass).setVisible(true);
               }else{
                   JOptionPane.showMessageDialog(null,"Incorrect Mobile Number or PIN");
               }
            } catch (SQLException ex) {
                System.out.println(ex);
            }


        }else if(e.getSource() == signup){
            setVisible(false);
            new SignUp().setVisible(true);
        }
    }
}
