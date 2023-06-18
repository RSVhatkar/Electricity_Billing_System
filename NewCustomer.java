import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.*;//to use Random class
import javax.swing.*;
import java.awt.event.*;
public class NewCustomer extends JFrame implements ActionListener{

	JPanel p;
	JLabel newcustomer,customername,meterno,meternolbl,address,city,state,email,phone,image;
	JTextField customernametext,addresstext,citytext,statetext,emailtext,phonetext;
	JButton next,cancel;
	
	NewCustomer()
	{
		setSize(700,500);
		setLocation(400,200);
		
		p=new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(173,216,230));
		add(p);
		
		newcustomer=new JLabel("New Customer");
		newcustomer.setBounds(180,10,200,25);
		newcustomer.setFont(new Font("Tahoma",Font.BOLD,26));
		p.add(newcustomer);
		
		customername=new JLabel("Customer Name");
		customername.setBounds(100,80,100,20);
		p.add(customername);
		
		customernametext=new JTextField();
		customernametext.setBounds(240,80,200,20);
		p.add(customernametext);
		
		meterno=new JLabel("Meter Number");
		meterno.setBounds(100,120,100,20);
		p.add(meterno);
		
		meternolbl=new JLabel("");
		meternolbl.setBounds(240,120,100,20);
		p.add(meternolbl);
		
		Random ran=new Random();
		long number=ran.nextLong()%1000000;//to generate meterno randomly
		
		meternolbl.setText(""+Math.abs(number));//Math.abs used to ignore -ve generated no
		
		address=new JLabel("Address");
		address.setBounds(100,160,100,20);
		p.add(address);
		
		addresstext=new JTextField();
		addresstext.setBounds(240,160,200,20);
		p.add(addresstext);
		
		city=new JLabel("City");
		city.setBounds(100,200,100,20);
		p.add(city);
		
		citytext=new JTextField();
		citytext.setBounds(240,200,200,20);
		p.add(citytext);
		
		state=new JLabel("State");
		state.setBounds(100,240,100,20);
		p.add(state);
		
		statetext=new JTextField();
		statetext.setBounds(240,240,200,20);
		p.add(statetext);
		
		email=new JLabel("Email");
		email.setBounds(100,280,100,20);
		p.add(email);
		
		emailtext=new JTextField();
		emailtext.setBounds(240,280,200,20);
		p.add(emailtext);
		
		phone=new JLabel("Phone Number");
		phone.setBounds(100,320,100,20);
		p.add(phone);
		
		phonetext=new JTextField();
		phonetext.setBounds(240,320,200,20);
		p.add(phonetext);
		
		next=new JButton("Next");
		next.setBounds(120,390,100,25);
		next.setBackground(Color.black);
		next.setForeground(Color.white);
		p.add(next);
		next.addActionListener(this);
		
		cancel=new JButton("Cancel");
		cancel.setBounds(250,390,100,25);
		cancel.setBackground(Color.black);
		cancel.setForeground(Color.white);
		p.add(cancel);
		cancel.addActionListener(this);
		
		setLayout(new BorderLayout());
		
		add(p,"Center");
		
		ImageIcon img=new ImageIcon("icons/hicon1.jpg");
		Image i=img.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
		ImageIcon imgnew=new ImageIcon(i);
		
		image=new JLabel("",imgnew,JLabel.CENTER);
		add(image,"West"); //using borederlayout
		
		getContentPane().setBackground(Color.white);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==next)
		{
			String smeterno=meternolbl.getText();
			String query1="insert into customer values('"+customernametext.getText()+"','"+meternolbl.getText()+"','"+addresstext.getText()+"','"+citytext.getText()+"','"+statetext.getText()+"','"+emailtext.getText()+"','"+phonetext.getText()+"')";
			String query2="insert into login values('"+meternolbl.getText()+"','','"+customernametext.getText()+"','','')";
			
			try
			{
				Conn c=new Conn();
				c.s.executeUpdate(query1);
				c.s.executeUpdate(query2);
				
				JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
				
				setVisible(false);
				
				//new frame
				new MeterInfo(smeterno);
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
		new NewCustomer();

	}

}
