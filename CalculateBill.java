import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.*;//to use Random class
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
public class CalculateBill extends JFrame implements ActionListener{

	JPanel p;
	JLabel calculateelectricitybill,meterno,name,namelbl,address,unitsconsumed,month,image;
	JTextField addresstext,unitsconsumedtext;
	JButton submit,cancel;
	Choice meternochoice,monthchoice;
	
	CalculateBill()
	{
		setSize(700,500);
		setLocation(400,150);
		
		p=new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(173,216,230));
		add(p);
		
		calculateelectricitybill=new JLabel("Calculate Electricity Bill");
		calculateelectricitybill.setBounds(150,10,400,25);
		calculateelectricitybill.setFont(new Font("Tahoma",Font.BOLD,26));
		p.add(calculateelectricitybill);
		
		meterno=new JLabel("Meter Number");
		meterno.setBounds(100,80,100,20);
		p.add(meterno);
		
		meternochoice=new Choice();
		meternochoice.setBounds(240,80,200,20);
		p.add(meternochoice);
		
		//to fetch meter no from customer table
		try
		{
			Conn c=new Conn();
			String query="select * from customer";
			ResultSet rs=c.s.executeQuery(query);
			while(rs.next())
			{
				meternochoice.add(rs.getString("meter_no"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		name=new JLabel("Name");
		name.setBounds(100,120,100,20);
		p.add(name);
		
		namelbl=new JLabel();
		namelbl.setBounds(240,120,200,20);
		p.add(namelbl);
				
		address=new JLabel("Address");
		address.setBounds(100,160,100,20);
		p.add(address);
		
		addresstext=new JTextField();
		addresstext.setBounds(240,160,200,20);
		p.add(addresstext);
		
		//to fetch name & address of corresponding meterno
		try
		{
			Conn c=new Conn();
			String query="select * from customer where meter_no= '"+meternochoice.getSelectedItem()+"' ";
			ResultSet rs=c.s.executeQuery(query);
			while(rs.next())
			{
				namelbl.setText(rs.getString("name"));
				addresstext.setText(rs.getString("address"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//to fetch data from db when selected different values from choice
		meternochoice.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent ie)
			{
				//to fetch name & address of corresponding meterno
				try
				{
					Conn c=new Conn();
					String query="select * from customer where meter_no= '"+meternochoice.getSelectedItem()+"' ";
					ResultSet rs=c.s.executeQuery(query);
					while(rs.next())
					{
						namelbl.setText(rs.getString("name"));
						addresstext.setText(rs.getString("address"));
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		
		unitsconsumed=new JLabel("Units Consumed");
		unitsconsumed.setBounds(100,200,100,20);
		p.add(unitsconsumed);
		
		unitsconsumedtext=new JTextField();
		unitsconsumedtext.setBounds(240,200,200,20);
		p.add(unitsconsumedtext);
		
		month=new JLabel("Month");
		month.setBounds(100,240,100,20);
		p.add(month);
		
		monthchoice=new Choice();
		monthchoice.setBounds(240,240,200,20);
		monthchoice.add("January");
		monthchoice.add("February");
		monthchoice.add("March");
		monthchoice.add("April");
		monthchoice.add("May");
		monthchoice.add("June");
		monthchoice.add("July");
		monthchoice.add("August");
		monthchoice.add("September");
		monthchoice.add("October");
		monthchoice.add("November");
		monthchoice.add("December");
		p.add(monthchoice);
			
		submit=new JButton("Submit");
		submit.setBounds(120,350,100,25);
		submit.setBackground(Color.black);
		submit.setForeground(Color.white);
		p.add(submit);
		submit.addActionListener(this);
		
		cancel=new JButton("Cancel");
		cancel.setBounds(250,350,100,25);
		cancel.setBackground(Color.black);
		cancel.setForeground(Color.white);
		p.add(cancel);
		cancel.addActionListener(this);
		
		setLayout(new BorderLayout());
		
		add(p,"Center"); 
		
		ImageIcon img=new ImageIcon("icons/hicon2.jpg");
		Image i=img.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
		ImageIcon imgnew=new ImageIcon(i);
		
		image=new JLabel("",imgnew,JLabel.CENTER);
		add(image,"West"); //using borederlayout
		
		getContentPane().setBackground(Color.white);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==submit)
		{
			String smeterno=meternochoice.getSelectedItem();
			String sunitsconsumed=unitsconsumedtext.getText();
			String smonth=monthchoice.getSelectedItem();
			
			int totalbill=0;
			int unitsconsumed=Integer.parseInt(sunitsconsumed);//converting unit in int
						
			String query="select * from tax"; //fetching values from tax table to  calculate bill
			
			try
			{
				Conn c=new Conn();
				ResultSet rs=c.s.executeQuery(query);
				
				while(rs.next())
				{
					totalbill += unitsconsumed * Integer.parseInt(rs.getString("cost_per_unit")); //calculating totalbill
					totalbill += Integer.parseInt(rs.getString("meter_rent"));
					totalbill += Integer.parseInt(rs.getString("service_charge"));
					totalbill += Integer.parseInt(rs.getString("service_tax"));
					totalbill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
					totalbill += Integer.parseInt(rs.getString("fixed_tax"));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				Conn c=new Conn();
				String query1="insert into bill values('"+meternochoice.getSelectedItem()+"','"+monthchoice.getSelectedItem()+"','"+unitsconsumedtext.getText()+"','"+totalbill+"','Not paid')";
				c.s.executeUpdate(query1);
				
				JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully");
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
		new CalculateBill();

	}

}

