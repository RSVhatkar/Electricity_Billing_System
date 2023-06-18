import java.awt.Choice;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;
public class Login extends JFrame implements ActionListener{

	JLabel username,password,loginas,background;
	JTextField usertext;
	JPasswordField passwordtext;
	Choice loginaschoice;
	JButton login,cancel,signup;
	
	Login()
	{
		super("Login Page");//it sets title of frame & super should be 1st statement inside constructor
		
		setSize(640,300);
		setLocation(400,200);
		setLayout(null);
		
		getContentPane().setBackground(Color.white);
		
		username=new JLabel("Username");
		username.setBounds(300,20,100,20);
		add(username);
		
		usertext=new JTextField();
		usertext.setBounds(400,20,100,20);
		add(usertext);
		
		password=new JLabel("Password");
		password.setBounds(300,60,100,20);
		add(password);
		
		passwordtext=new JPasswordField();
		passwordtext.setBounds(400,60,100,20);
		add(passwordtext);
		
		loginas=new JLabel("Login as");
		loginas.setBounds(300,100,100,20);
		add(loginas);
		
		loginaschoice=new Choice();
		loginaschoice.setBounds(400,100,150,20);
		loginaschoice.add("Admin");
		loginaschoice.add("Customer");
		add(loginaschoice);
		
		ImageIcon img=new ImageIcon("icons/login.png");
		Image i=img.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT);
		ImageIcon imgnew=new ImageIcon(i);
		
		login=new JButton("Login",imgnew);
		login.setBounds(330,160,100,20);
		add(login);
		login.addActionListener(this);
		
		ImageIcon img1=new ImageIcon("icons/cancel.jpg");
		Image i1=img1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT);
		ImageIcon imgnew1=new ImageIcon(i1);
		
		cancel=new JButton("Cancel",imgnew1);
		cancel.setBounds(450,160,100,20);
		add(cancel);
		cancel.addActionListener(this);
		
		ImageIcon img2=new ImageIcon("icons/signup.png");
		Image i2=img2.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
		ImageIcon imgnew2=new ImageIcon(i2);
		
		signup=new JButton("Signup",imgnew2);
		signup.setBounds(380,200,100,20);
		add(signup);
		signup.addActionListener(this);
		
		ImageIcon img3=new ImageIcon("icons/second.jpg");
		Image i3=img3.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
		ImageIcon imgnew3=new ImageIcon(i3);
		
		background=new JLabel("",imgnew3,JLabel.CENTER);
		background.setBounds(0,0,250,250);
		add(background);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==login)
		{
			String susername=usertext.getText();
			String spassword=passwordtext.getText();
			String suser=loginaschoice.getSelectedItem();
			
			try
			{
				Conn c=new Conn();
				String query="select * from login where username= '"+susername+"' and password= '"+spassword+"' and user= '"+suser+"' ";
				
				ResultSet rs=c.s.executeQuery(query);
				if(rs.next())
				{
					String meterno=rs.getString("meter_no");//fetch meter no from login table to pass it to viewcustomer form
					setVisible(false);
					new Project(suser,meterno);//pass value to check if user is admin or customer
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid Login");
					usertext.setText("");
					passwordtext.setText("");
				}
			}
			catch(Exception e)
			{
				
			}
		}
		else if(ae.getSource()==cancel)
		{
			setVisible(false);
		}
		else if(ae.getSource()==signup)
		{
			setVisible(false);
			new Signup();
		}
	}
	
	public static void main(String[] args) {
		new Login();

	}

}
