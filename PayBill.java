import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.ResultSet;
import java.awt.event.*;
import javax.swing.*;
public class PayBill extends JFrame implements ActionListener{

	JLabel background,heading,meterno,meternotext,name,nametext,month,units,unitstext,totalbill,totalbilltext,status,statustext;
	Choice monthchoice;
	String smeterno;
	JButton pay,back;
	
	PayBill(String smeterno)
	{
		this.smeterno=smeterno;
		
		setLayout(null);
		setBounds(300,150,900,600);
		getContentPane().setBackground(Color.white);
		
		heading=new JLabel("Electricity Bill");
		heading.setBounds(120,5,400,30);
		heading.setFont(new Font("Tahoma",Font.BOLD,24));
		add(heading);
		
		meterno=new JLabel("Meter Number");
		meterno.setBounds(35,80,220,20);
		add(meterno);
		
		meternotext=new JLabel();
		meternotext.setBounds(300,80,200,20);
		add(meternotext);
		
		name=new JLabel("Name");
		name.setBounds(35,140,200,20);
		add(name);
		
		nametext=new JLabel();
		nametext.setBounds(300,140,200,20);
		add(nametext);
		
		month=new JLabel("Month");
		month.setBounds(35,200,200,20);
		add(month);
		
		monthchoice=new Choice();
		monthchoice.setBounds(300,200,200,20);
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
		add(monthchoice);
		
		units=new JLabel("Units");
		units.setBounds(35,260,200,20);
		add(units);
		
		unitstext=new JLabel();
		unitstext.setBounds(300,260,200,20);
		add(unitstext);
		
		totalbill=new JLabel("Total Bill");
		totalbill.setBounds(35,320,200,20);
		add(totalbill);
		
		totalbilltext=new JLabel();
		totalbilltext.setBounds(300,320,200,20);
		add(totalbilltext);
		
		status=new JLabel("Bill Status");
		status.setBounds(35,380,200,20);
		add(status);
		
		statustext=new JLabel();
		statustext.setBounds(300,380,200,20);
		statustext.setForeground(Color.red);
		add(statustext);
		
		//to fetch info from customer & bill table
		try
		{
			Conn c=new Conn();
			String query="select * from customer where meter_no = '"+smeterno+"' ";
			ResultSet rs=c.s.executeQuery(query);
			while(rs.next())
			{
				meternotext.setText(smeterno);
				nametext.setText(rs.getString("name"));
			}
			
			String query1="select * from bill where month='January' ";
			rs=c.s.executeQuery(query1);
			while(rs.next())
			{
				unitstext.setText(rs.getString("units"));
				totalbilltext.setText(rs.getString("totalbill"));
				statustext.setText(rs.getString("status"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		monthchoice.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent ie)
			{
				//to fetch bill details from bill table of selected month
				try
				{
					Conn c=new Conn();
					String query="select * from bill where meter_no = '"+smeterno+"' and month = '"+monthchoice.getSelectedItem()+"' ";
					ResultSet rs=c.s.executeQuery(query);
					while(rs.next())
					{
						unitstext.setText(rs.getString("units"));
						totalbilltext.setText(rs.getString("totalbill"));
						statustext.setText(rs.getString("status"));
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		);
		
		pay=new JButton("Pay");
		pay.setBounds(100,460,100,25);
		pay.setBackground(Color.black);
		pay.setForeground(Color.white);
		add(pay);
		pay.addActionListener(this);
		
		back=new JButton("Back");
		back.setBounds(230,460,100,25);
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		add(back);
		back.addActionListener(this);
		
		ImageIcon i1 = new ImageIcon("icons/bill.png");
	    Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    background= new JLabel(i3);
	    background.setBounds(400, 120, 600, 300);
	    add(background);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==pay)
		{
			try
			{
				Conn c=new Conn();
				String query="update bill set status= 'Paid' where meter_no = '"+smeterno+"' and month= '"+monthchoice.getSelectedItem()+"' ";
				c.s.executeUpdate(query);
				
				setVisible(false);
				new Paytm(smeterno);
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
		new PayBill("");

	}

}
