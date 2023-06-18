import java.awt.Choice;
import net.proteanit.sql.*;//to use tables
import java.awt.Color;
import java.sql.ResultSet;
import java.awt.event.*;
import javax.swing.*;
public class DepositeDetails extends JFrame implements ActionListener{

	JLabel searchbymeterno,searchbymonth;
	Choice meternochoice,searchbymonthchoice;
	JTable table;
	JButton search,print;
	
	DepositeDetails()
	{
		super("Deposite Details");
		
		setSize(700,700);
		setLocation(400,100);
		
		setLayout(null);
		
		getContentPane().setBackground(Color.white);
		
		searchbymeterno=new JLabel("Search by meter number");
		searchbymeterno.setBounds(20,20,150,20);
		add(searchbymeterno);
		
		meternochoice=new Choice();
		meternochoice.setBounds(180,20,150,20);
		add(meternochoice);
		
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
		
		searchbymonth=new JLabel("Search by Month");
		searchbymonth.setBounds(400,20,120,20);
		add(searchbymonth);
		
		searchbymonthchoice=new Choice();
		searchbymonthchoice.setBounds(520,20,150,20);
		searchbymonthchoice.add("January");
		searchbymonthchoice.add("February");
		searchbymonthchoice.add("March");
		searchbymonthchoice.add("April");
		searchbymonthchoice.add("May");
		searchbymonthchoice.add("June");
		searchbymonthchoice.add("July");
		searchbymonthchoice.add("August");
		searchbymonthchoice.add("September");
		searchbymonthchoice.add("October");
		searchbymonthchoice.add("November");
		searchbymonthchoice.add("December");
		add(searchbymonthchoice);
		
		table=new JTable();
		
		//to put data in table from db
		try
		{
			Conn c=new Conn();
			String query="select * from bill";
			ResultSet rs=c.s.executeQuery(query);
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		JScrollPane sp=new JScrollPane(table);//on which obj want scrollbar pass that obj in scrollbar
		sp.setBounds(0,100,700,600);
		add(sp);
		
		search=new JButton("Search");
		search.setBounds(20,70,80,20);
		add(search);
		search.addActionListener(this);
		
		print=new JButton("Print");
		print.setBounds(120,70,80,20);
		add(print);
		print.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==search)
		{
			String query="select * from bill where meter_no= '"+meternochoice.getSelectedItem()+"' and month= '"+searchbymonthchoice.getSelectedItem()+"' ";
			
			try
			{
				Conn c=new Conn();
				ResultSet rs=c.s.executeQuery(query);
				table.setModel(DbUtils.resultSetToTableModel(rs));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(ae.getSource()==print)
		{
			try
			{
				table.print();//to print table
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new DepositeDetails();

	}

}
