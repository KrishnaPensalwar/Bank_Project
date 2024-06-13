package pages;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class SignUp extends JFrame implements ActionListener {

    JTextField name , address , dob, type , aadhar_no , PAN_no , mobile_no , PIN;
    JButton create_account;
    JDateChooser date;
    JComboBox<String> comboBox;
    SignUp(){

        setLayout(null);


        // Name , address , age , account type , aadhar card , PAN card , mobile number , PIN

        JLabel text = new JLabel("Please Fill The Below Details");
        text.setBounds(220,20,600,40);
        text.setFont(new Font("Osword",Font.BOLD,24));
        add(text);

        JLabel name_d = new JLabel("Enter Name");
        name_d.setBounds(40 , 100, 200, 40);
        name_d.setFont(new Font("Osword",Font.BOLD,18));
        add(name_d);

        name = new JTextField();
        name.setBounds(220,105,300,30);
        add(name);

        JLabel dob_d = new JLabel("Enter Date of Birth");
        dob_d.setBounds(40 , 170, 200, 40);
        dob_d.setFont(new Font("Osword",Font.BOLD,16));
        add(dob_d);

        date=new JDateChooser();
        date.setBounds(220,175,300,30);
        add(date);

        JLabel address_d = new JLabel("Enter Address");
        address_d.setBounds(40 , 240, 200, 40);
        address_d.setFont(new Font("Osword",Font.BOLD,18));
        add(address_d);

        address = new JTextField();
        address.setBounds(220,245,300,30);
        add(address);

        JLabel mobile_d = new JLabel("Enter Mobile No.");
        mobile_d.setBounds(40 , 310, 200, 40);
        mobile_d.setFont(new Font("Osword",Font.BOLD,18));
        add(mobile_d);

        mobile_no = new JTextField();
        mobile_no.setBounds(220,315,300,30);
        add(mobile_no);

        JLabel aadhar_d = new JLabel("Enter Aadhar No.");
        aadhar_d.setBounds(40 , 380, 200, 40);
        aadhar_d.setFont(new Font("Osword",Font.BOLD,16));
        add(aadhar_d);

        aadhar_no = new JTextField();
        aadhar_no.setBounds(220,385,300,30);
        add(aadhar_no);

        JLabel pan_d = new JLabel("Enter PAN No.");
        pan_d.setBounds(40 , 450, 200, 40);
        pan_d.setFont(new Font("Osword",Font.BOLD,18));
        add(pan_d);

        PAN_no = new JTextField();
        PAN_no.setBounds(220,455,300,30);
        add(PAN_no);

        JLabel account_type = new JLabel("Select Account Type");
        account_type.setBounds(40 , 510, 200, 40);
        account_type.setFont(new Font("Osword",Font.BOLD,17));
        add(account_type);

        String[] items = {"Select","Savings", "Current", "Salary"};
        comboBox = new JComboBox<>(items);
        comboBox.setBounds(220,515,300,30);
        add(comboBox);

        JLabel pin = new JLabel("Set PIN");
        pin.setBounds(40,580,200,40);
        pin.setFont(new Font("Osword",Font.BOLD,18));
        add(pin);

        PIN = new JTextField();
        PIN.setBounds(220,580,300,30);
        add(PIN);


        create_account = new JButton("Create Account");
        create_account.setFont(new Font("Osword",Font.BOLD,20));
        create_account.setBounds(170,640,300,40);
        create_account.setBackground(Color.black);
        create_account.setForeground(Color.white);
        create_account.addActionListener(this);
        add(create_account);

        setSize(700,750);
        setLocation(400,50);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SignUp();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String user_name = name.getText();
        String user_age = ((JTextField) date.getDateEditor().getUiComponent()).getText();
        String user_address = address.getText();
        String user_aadhar = aadhar_no.getText();
        String user_PAN = PAN_no.getText();
        String account_type =(String) comboBox.getSelectedItem();
        String user_mobile = mobile_no.getText();
        int available = 0;

        int user_pin = Integer.parseInt(PIN.getText());


        try{
            if(user_name.equals("") ||
                    user_age.equals("") ||
                    user_PAN.equals("") ||
                    user_aadhar.equals("") ||
                    user_address.equals("") ||
                    user_mobile.equals("") ||
                    Objects.equals(account_type, "Select"))
            {
                JOptionPane.showMessageDialog(null,"Please Fill All Details");
            }else{
                Conn conn=new Conn();
                String query = "insert into users values('"+user_name+"'," +
                        "'"+user_age+"'," +
                        "'"+user_address+"'," +
                        "'"+user_aadhar+"'," +
                        "'"+user_mobile+"'," +
                        "'"+user_PAN+"'," +
                        "'"+account_type+"'," +
                        "'"+user_pin+"'," +
                        "'"+available+"')";

                conn.s.executeUpdate(query);
                setVisible(false);
                new Login().setVisible(true);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }


    }
}


