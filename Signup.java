import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.*;
public class Signup extends JFrame implements ActionListener{
	
	JPanel panel;
	JLabel createaccountas,meterno,username,name,password,background;
	Choice createaccountaschoice;
	JTextField meternotext,usernametext,nametext;
	JPasswordField passwordtext;
	JButton create,back;

	Signup()
	{
		setBounds(450,150,700,400);
		setLayout(null);
		
		getContentPane().setBackground(Color.white);
		
		panel=new JPanel();
		panel.setBounds(30,30,650,300);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255,0,0),2),"Create Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(255,0,0)));//2nd argument ie 2 value is shade
		panel.setBackground(Color.white);
		panel.setForeground(new Color(34,139,34));//sets this color to text on panel
		add(panel);
		
		createaccountas=new JLabel("Create Account as");
		createaccountas.setBounds(100,50,140,20);
		createaccountas.setFont(new Font("Tahoma",Font.BOLD,14));
		createaccountas.setForeground(Color.gray);
		panel.add(createaccountas);
		
		createaccountaschoice=new Choice();
		createaccountaschoice.setBounds(260,50,150,20);
		createaccountaschoice.add("Admin");
		createaccountaschoice.add("Customer");
		panel.add(createaccountaschoice);
		
		meterno=new JLabel("Meter Number");
		meterno.setBounds(100,90,140,20);
		meterno.setFont(new Font("Tahoma",Font.BOLD,14));
		meterno.setForeground(Color.gray);
		meterno.setVisible(false); //set to false becoz when admin is selected in combobox there is no meterno for admin so this meterno field will be disabled whne admin is selected
		panel.add(meterno);
		
		meternotext=new JTextField();
		meternotext.setBounds(260,90,150,20);
		meternotext.setVisible(false); //set to false becoz when admin is selected in combobox there is no meterno for admin so this meterno field will be disabled whne admin is selected
		panel.add(meternotext);
			
		username=new JLabel("Username");
		username.setBounds(100,130,140,20);
		username.setFont(new Font("Tahoma",Font.BOLD,14));
		username.setForeground(Color.GRAY);
		panel.add(username);
		
		usernametext=new JTextField();
		usernametext.setBounds(260,130,150,20);
		panel.add(usernametext);
		
		name=new JLabel("Name");
		name.setBounds(100,170,140,20);
		name.setFont(new Font("Tahoma",Font.BOLD,14));
		name.setForeground(Color.gray);
		panel.add(name);
		
		nametext=new JTextField();
		nametext.setBounds(260,170,140,20);
		panel.add(nametext);
		
		//to disp name of customer when meter no is typed and that textfield is left 
				meternotext.addFocusListener(new FocusListener()
				{
					public void focusGained(FocusEvent fe)
					{
						
					}
					
					public void focusLost(FocusEvent fe)
					{
						try
						{
							Conn c=new Conn();
							String query="select * from login where meter_no= '"+meternotext.getText()+"' ";
							ResultSet rs=c.s.executeQuery(query);
							while(rs.next())
							{
								nametext.setText(rs.getString("name"));
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				});
		
		password=new JLabel("Password");
		password.setBounds(100,210,140,20);
		password.setFont(new Font("Tahoma",Font.BOLD,14));
		password.setForeground(Color.gray);
		panel.add(password);
		
		passwordtext=new JPasswordField();
		passwordtext.setBounds(260,210,140,20);
		panel.add(passwordtext);
		
		//to check which item is selected in choice ie if customer is selected then meterno should be visible
		createaccountaschoice.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent ie)
			{
				String user=createaccountaschoice.getSelectedItem();
				if(user.equals("Customer"))
				{
					meterno.setVisible(true);
					meternotext.setVisible(true);
					nametext.setEditable(false);//customer cannot edit name
				}
				else
				{
					meterno.setVisible(false);
					meternotext.setVisible(false);
					nametext.setEditable(true);//admin can edit name
				}
			}
		});
		
		create=new JButton("Create");
		create.setBounds(140,260,120,25);
		create.setBackground(Color.black);
		create.setForeground(Color.white);
		panel.add(create);
		create.addActionListener(this);
		
		back=new JButton("Back");
		back.setBounds(300,260,120,25);
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		panel.add(back);
		back.addActionListener(this);
		
		ImageIcon img=new ImageIcon("icons/signupImage.png");
		Image i=img.getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT);
		ImageIcon imgnew=new ImageIcon(i);
		
		background=new JLabel("",imgnew,JLabel.CENTER);
		background.setBounds(415,30,250,250);
		panel.add(background);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==create)
		{
			String saccountas=createaccountaschoice.getSelectedItem();
			String smeterno=meternotext.getText();
			String susername=usernametext.getText();
			String sname=nametext.getText();
			String spassword=passwordtext.getText();
			try
			{
				Conn c=new Conn();
				String query=null;
				
				//it will check if admin is user if admin is user then insert data in login else update data in login
				if(saccountas.equals("Admin"))
				{
					query="insert into login values('"+smeterno+"','"+susername+"','"+sname+"','"+spassword+"','"+saccountas+"')";
				}
				else
				{
					query="update login set username = '"+susername+"', password = '"+spassword+"',user= '"+saccountas+"' where meter_no='"+smeterno+"' ";
				}
				c.s.executeUpdate(query);
				
				JOptionPane.showMessageDialog(null,"Account Created Successfully");
				
				setVisible(false);
				new Login();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(ae.getSource()==back)
		{
			setVisible(false);
			new Login();
		}
	}
	
	public static void main(String[] args) {
		new Signup();

	}

}
