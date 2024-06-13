package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transactions extends JFrame implements ActionListener {

    JButton deposite,withdrawl,check,history,exit;

    static int pinnumber;
    public Transactions(int pinnumber){
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=ii.getImage().getScaledInstance(700,700,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        image.setBounds(0,0,700,700);
        add(image);

        JLabel text = new JLabel("Please Select Your Transaction");
        text.setBounds(165,220,500,35);
        text.setForeground(Color.white);
        image.add(text);

        deposite = new JButton("Deposite");
        deposite.setBounds(135,320,110,25);
        deposite.setBackground(Color.black);
        deposite.setForeground(Color.white);
        deposite.addActionListener(this);
        image.add(deposite);

        withdrawl = new JButton("Withdrawl");
        withdrawl.setBounds(265,320,110,25);
        withdrawl.setBackground(Color.black);
        withdrawl.setForeground(Color.white);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        history = new JButton("History");
        history.setBounds(135,340,110,30);
        history.setBackground(Color.black);
        history.setForeground(Color.white);
        history.addActionListener(this);
        image.add(history);

        check = new JButton("Check Balance");
        check.setBounds(265,340,110,30);
        check.setFont(new Font("System",Font.BOLD,10));
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        check.addActionListener(this);
        image.add(check);

        exit = new JButton("Exit");
        exit.setBounds(265,370,110,30);
        exit.setBackground(Color.black);
        exit.setForeground(Color.white);
        exit.addActionListener(this);
        image.add(exit);


        setTitle("Transaction");
        setSize(700,700);
        setVisible(true);
        setLocation(300,00);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            System.exit(0);
        }
        if(e.getSource() == deposite){
            setVisible(false);
            System.out.println("DEPOSITE");
            new Deposit(pinnumber).setVisible(true);
        }
        if(e.getSource() == withdrawl){
            System.out.println("WITHDRAWL");
            new Withdrawl(pinnumber).setVisible(true);
        }

        if(e.getSource() == check){
            setVisible(false);
            new Check_Balance(pinnumber).setVisible(true);
        }

        if(e.getSource() == history){
            setVisible(false);
            new History(pinnumber).setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Transactions(pinnumber);
    }
}
