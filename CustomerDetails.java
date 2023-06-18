import java.awt.Choice;
import net.proteanit.sql.*;//to use tables
import java.awt.Color;
import java.sql.ResultSet;
import java.awt.event.*;
import javax.swing.*;
public class CustomerDetails extends JFrame implements ActionListener{

	JLabel searchbymeterno,searchbymonth;
	Choice meternochoice,searchbymonthchoice;
	JTable table;
	JButton print;
	
	CustomerDetails()
	{
		super("Customer Details");
		
		setSize(1200,650);
		setLocation(200,150);
		
		table=new JTable();
		
		//to put data in table from db
		try
		{
			Conn c=new Conn();
			String query="select * from customer";
			ResultSet rs=c.s.executeQuery(query);
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		JScrollPane sp=new JScrollPane(table);//on which obj want scrollbar pass that obj in scrollbar
		add(sp);
				
		print=new JButton("Print"); //setBounds not taken becoz setLayout is not taken so it will not work
		add(print,"South"); //so bydefault it will take border layout and then u will have to set direction in which u want to add the obj
		print.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==print)
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
		new CustomerDetails();

	}

}
