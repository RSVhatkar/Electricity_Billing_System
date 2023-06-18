import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;
public class UpdateInformation extends JFrame implements ActionListener{

	JLabel heading,name,nametext,meterno,meternotext,address,city,state,email,phone,background;
	JTextField addresstext,citytext,statetext,emailtext,phonetext;
	JButton update,cancel;
	String smeterno;
	
	UpdateInformation(String smeterno)
	{
		this.smeterno=smeterno;
		setBounds(300,150,1050,450);
		getContentPane().setBackground(Color.white);
		setLayout(null);
		
		heading=new JLabel("UPDATE CUSTOMER INFORMATION");
		heading.setBounds(110,0,400,30);
		heading.setFont(new Font("Tahoma",Font.PLAIN,20));
		add(heading);
		
		name=new JLabel("Name");
		name.setBounds(30,70,100,20);
		add(name);
		
		nametext=new JLabel();
		nametext.setBounds(230,70,200,20);
		add(nametext);
		
		meterno=new JLabel("Meter Number");
		meterno.setBounds(30,110,100,20);
		add(meterno);
		
		meternotext=new JLabel();
		meternotext.setBounds(230,110,200,20);
		add(meternotext);
		
		address=new JLabel("Address");
		address.setBounds(30,150,100,20);
		add(address);
		
		addresstext=new JTextField();
		addresstext.setBounds(230,150,200,20);
		add(addresstext);
		
		city=new JLabel("City");
		city.setBounds(30,190,100,20);
		add(city);
		
		citytext=new JTextField();
		citytext.setBounds(230,190,200,20);
		add(citytext);
		
		state=new JLabel("State");
		state.setBounds(30,230,100,20);
		add(state);
		
		statetext=new JTextField();
		statetext.setBounds(230,230,200,20);
		add(statetext);
		
		email=new JLabel("Email");
		email.setBounds(30,270,100,20);
		add(email);
		
		emailtext=new JTextField();
		emailtext.setBounds(230,270,200,20);
		add(emailtext);
		
		phone=new JLabel("Phone");
		phone.setBounds(30,310,100,20);
		add(phone);
		
		phonetext=new JTextField();
		phonetext.setBounds(230,310,200,20);
		add(phonetext);
		
		//to fetch all customer info from table using meterno
		try
		{
			Conn c=new Conn();
			String query="select * from customer where meter_no = '"+smeterno+"' ";
			ResultSet rs=c.s.executeQuery(query);
			while(rs.next())
			{
				nametext.setText(rs.getString("name"));;
				meternotext.setText(rs.getString("meter_no"));;
				addresstext.setText(rs.getString("address"));;
				citytext.setText(rs.getString("city"));;
				statetext.setText(rs.getString("state"));;
				emailtext.setText(rs.getString("email"));
				phonetext.setText(rs.getString("phone"));;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
		update=new JButton("Update");
		update.setBounds(70,360,100,25);
		update.setBackground(Color.black);
		update.setForeground(Color.white);
		add(update);
		update.addActionListener(this);
		
		cancel=new JButton("Cancel");
		cancel.setBounds(230,360,100,25);
		cancel.setBackground(Color.black);
		cancel.setForeground(Color.white);
		add(cancel);
		cancel.addActionListener(this);
		
		ImageIcon img=new ImageIcon("icons/update.jpg");
		Image i=img.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
		ImageIcon imgnew=new ImageIcon(i);
		
		background=new JLabel("",imgnew,JLabel.CENTER);
		background.setBounds(550,50,400,300);
		add(background);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==update)
		{
			try
			{
				Conn c=new Conn();
				String query="update customer set address= '"+addresstext.getText()+"', city= '"+citytext.getText()+"', state = '"+statetext.getText()+"', email = '"+emailtext.getText()+"', phone = '"+phonetext.getText()+"' where meter_no = '"+meternotext.getText()+"' ";
				c.s.executeUpdate(query);
				
				JOptionPane.showMessageDialog(null, "Customer Information Updated Successfully");
				setVisible(false);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new UpdateInformation("");

	}

}
