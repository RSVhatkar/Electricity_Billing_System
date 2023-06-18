import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.ResultSet;
import java.awt.event.*;
import javax.swing.*;
public class ViewInformation extends JFrame implements ActionListener{
	
	JLabel background,heading,name,nametext,meterno,meternotext,address,addresstext,city,citytext,state,statetext,email,emailtext,phone,phonetext;
	JButton cancel;
	String smeterno;
	
	ViewInformation(String smeterno)
	{
		this.smeterno=smeterno;
		
		setBounds(350,150,850,650);
		getContentPane().setBackground(Color.white);
		setLayout(null);
		
		heading=new JLabel("VIEW CUSTOMER INFORMATION");
		heading.setBounds(250,0,500,40);
		heading.setFont(new Font("Tahoma",Font.PLAIN,20));
		add(heading);
		
		name=new JLabel("Name");
		name.setBounds(70,80,100,20);
		add(name);
		
		nametext=new JLabel();
		nametext.setBounds(250,80,100,20);
		add(nametext);
		
		meterno=new JLabel("Meter Number");
		meterno.setBounds(70,140,100,20);
		add(meterno);
		
		meternotext=new JLabel();
		meternotext.setBounds(250,140,100,20);
		add(meternotext);
		
		address=new JLabel("Address");
		address.setBounds(70,200,100,20);
		add(address);
		
		addresstext=new JLabel();
		addresstext.setBounds(250,200,300,20);
		add(addresstext);
		
		city=new JLabel("City");
		city.setBounds(70,260,100,20);
		add(city);
		
		citytext=new JLabel();
		citytext.setBounds(250,260,100,20);
		add(citytext);
		
		state=new JLabel("State");
		state.setBounds(500,80,100,20);
		add(state);
		
		statetext=new JLabel();
		statetext.setBounds(650,80,100,20);
		add(statetext);
		
		email=new JLabel("Email");
		email.setBounds(500,140,100,20);
		add(email);
		
		emailtext=new JLabel();
		emailtext.setBounds(650,140,300,20);
		add(emailtext);
		
		phone=new JLabel("Phone");
		phone.setBounds(500,200,100,20);
		add(phone);
		
		phonetext=new JLabel();
		phonetext.setBounds(650,200,100,20);
		add(phonetext);
		
		//to fetch all customer details using meterno
		try
		{
			Conn c=new Conn();
			String query="select * from customer where meter_no = '"+smeterno+"' ";
			ResultSet rs=c.s.executeQuery(query);
			while(rs.next())
			{
				nametext.setText(rs.getString("name"));
				meternotext.setText(rs.getString("meter_no"));
				addresstext.setText(rs.getString("address"));
				citytext.setText(rs.getString("city"));
				statetext.setText(rs.getString("state"));
				emailtext.setText(rs.getString("email"));
				phonetext.setText(rs.getString("phone"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		cancel=new JButton("Cancel");
		cancel.setBounds(350,340,100,25);
		cancel.setBackground(Color.black);
		cancel.setForeground(Color.white);
		add(cancel);
		cancel.addActionListener(this);
		
		ImageIcon img=new ImageIcon("icons/viewcustomer.jpg");
		Image i=img.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
		ImageIcon imgnew=new ImageIcon(i);
		
		background=new JLabel("",imgnew,JLabel.CENTER);
		background.setBounds(20,350,600,300);
		add(background);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		setVisible(false);
	}

	public static void main(String[] args) {
		new ViewInformation("");

	}

}
