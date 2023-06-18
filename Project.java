import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
public class Project extends JFrame implements ActionListener{

	JLabel background;
	JMenuBar mb;
	JMenu master,info,user,report,utility,exit;
	JMenuItem newcustomer,customerdetails,depositedetails,calculatebill,updateinformation,viewinformation,paybill,billdetails,generatebill,notepad,calculator,exitt;
	String suser,meterno;
	
	Project(String suser,String meterno)
	{
		this.suser=suser;
		this.meterno=meterno;
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); //full screen frame will be created
		
		ImageIcon img=new ImageIcon("icons/elect1.jpg");
		Image i=img.getImage().getScaledInstance(2000, 1000, Image.SCALE_DEFAULT);
		ImageIcon imgnew=new ImageIcon(i);
		
		background=new JLabel("",imgnew,JLabel.CENTER);
		add(background);
		
		mb=new JMenuBar();
		setJMenuBar(mb);
		
		master=new JMenu("Master");
		master.setForeground(Color.blue);
				
		newcustomer=new JMenuItem("New Customer");
		newcustomer.setFont(new Font("monospaced",Font.PLAIN,12));
		newcustomer.setBackground(Color.white);
		ImageIcon c=new ImageIcon("icons/icon1.png");
		Image c1=c.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		ImageIcon cnew=new ImageIcon(c1);
		newcustomer.setIcon(cnew); //adds icon on menuitem
		newcustomer.setMnemonic('D'); //used to create shortcut keys on keyboard means when we will press ctrl + D then new customer form will be opened
		newcustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));//used to set to press key with ctrl means it is used to create shortcut keys
		master.add(newcustomer);
		newcustomer.addActionListener(this);
		
		customerdetails=new JMenuItem("Customer Details");
		customerdetails.setFont(new Font("monospaced",Font.PLAIN,12));
		customerdetails.setBackground(Color.white);
		ImageIcon cd=new ImageIcon("icons/icon2.png");
		Image cd1=cd.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT);
		customerdetails.setIcon(new ImageIcon(cd1));
		customerdetails.setMnemonic('M');
		customerdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
		master.add(customerdetails);
		customerdetails.addActionListener(this);
		
		depositedetails=new JMenuItem("Deposite Details");
		depositedetails.setFont(new Font("monospaced",Font.PLAIN,12));
		depositedetails.setBackground(Color.white);
		ImageIcon dd=new ImageIcon("icons/icon3.png");
		Image dd1=dd.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		depositedetails.setIcon(new ImageIcon(dd1));
		depositedetails.setMnemonic('N');
		depositedetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		master.add(depositedetails);
		depositedetails.addActionListener(this);
		
		calculatebill=new JMenuItem("Calculate Bill");
		calculatebill.setFont(new Font("monospaced",Font.PLAIN,12));
		calculatebill.setBackground(Color.white);
		ImageIcon cb=new ImageIcon("icons/icon5.png");
		Image cb1=cb.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		calculatebill.setIcon(new ImageIcon(cb1));
		calculatebill.setMnemonic('B');
		calculatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
		master.add(calculatebill);
		calculatebill.addActionListener(this);
		
		info=new JMenu("Information");
		info.setForeground(Color.red);
						
		updateinformation=new JMenuItem("Update Information");
		updateinformation.setFont(new Font("monospaced",Font.PLAIN,12));
		updateinformation.setBackground(Color.white);
		ImageIcon ui=new ImageIcon("icons/icon4.png");
		Image ui1=ui.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		updateinformation.setIcon(new ImageIcon(ui1));
		updateinformation.setMnemonic('P');
		updateinformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		info.add(updateinformation);
		updateinformation.addActionListener(this);
		
		viewinformation=new JMenuItem("View Information");
		viewinformation.setFont(new Font("monospaced",Font.PLAIN,12));
		viewinformation.setBackground(Color.white);
		ImageIcon vi=new ImageIcon("icons/icon6.png");
		Image vi1=vi.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		viewinformation.setIcon(new ImageIcon(vi1));
		viewinformation.setMnemonic('L');
		viewinformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
		info.add(viewinformation);
		viewinformation.addActionListener(this);
		
		user=new JMenu("User");
		user.setForeground(Color.blue);
				
		paybill=new JMenuItem("Pay Bill");
		paybill.setFont(new Font("monospaced",Font.PLAIN,12));
		paybill.setBackground(Color.white);
		ImageIcon pb=new ImageIcon("icons/icon7.png");
		Image pb1=pb.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		paybill.setIcon(new ImageIcon(pb1));
		paybill.setMnemonic('R');
		paybill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
		user.add(paybill);
		paybill.addActionListener(this);
		
		billdetails=new JMenuItem("Bill Details");
		billdetails.setFont(new Font("monospaced",Font.PLAIN,12));
		billdetails.setBackground(Color.white);
		ImageIcon bd=new ImageIcon("icons/icon8.png");
		Image bd1=bd.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		billdetails.setIcon(new ImageIcon(bd1));
		billdetails.setMnemonic('O');
		billdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		user.add(billdetails);
		billdetails.addActionListener(this);
		
		report=new JMenu("Report");
		report.setForeground(Color.red);
				
		generatebill=new JMenuItem("Generate Bill");
		generatebill.setFont(new Font("monospaced",Font.PLAIN,12));
		generatebill.setBackground(Color.white);
		ImageIcon gb=new ImageIcon("icons/icon7.png");
		Image gb1=gb.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		generatebill.setIcon(new ImageIcon(gb1));
		generatebill.setMnemonic('R');
		generatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
		report.add(generatebill);
		generatebill.addActionListener(this);
		
		utility=new JMenu("Utility");
		utility.setForeground(Color.blue);
				
		notepad=new JMenuItem("Notepad");
		notepad.setFont(new Font("monospaced",Font.PLAIN,12));
		notepad.setBackground(Color.white);
		ImageIcon n=new ImageIcon("icons/icon12.png");
		Image n1=n.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		notepad.setIcon(new ImageIcon(n1));
		notepad.setMnemonic('N');
		notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		utility.add(notepad);
		notepad.addActionListener(this);
		
		calculator=new JMenuItem("Calculator");
		calculator.setFont(new Font("monospaced",Font.PLAIN,12));
		calculator.setBackground(Color.white);
		ImageIcon cc=new ImageIcon("icons/icon9.png");
		Image cc1=cc.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		calculator.setIcon(new ImageIcon(cc1));
		calculator.setMnemonic('C');
		calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		utility.add(calculator);
		calculator.addActionListener(this);
		
		exit=new JMenu("Exit");
		exit.setForeground(Color.red);
				
		exitt=new JMenuItem("Exit");
		exitt.setFont(new Font("monospaced",Font.PLAIN,12));
		exitt.setBackground(Color.white);
		ImageIcon e=new ImageIcon("icons/icon11.png");
		Image e1=e.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		exitt.setIcon(new ImageIcon(e1));
		exitt.setMnemonic('E');
		exitt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		exit.add(exitt);
		exitt.addActionListener(this);
		
		if(suser.equals("Admin"))
		{
			mb.add(master);//if user is admin then add master
		}
		else
		{
			mb.add(info);//if user is customer then add this 3 field
			mb.add(user);
			mb.add(report);
		}
		
		mb.add(utility); //add this 2 fields for both users
		mb.add(exit);
		
		setLayout(new FlowLayout());
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String msg=ae.getActionCommand(); //to get value of which item is selected
		if(msg.equals("New Customer"))
		{
			new NewCustomer();
		}
		else if(msg.equals("Customer Details"))
		{
			new CustomerDetails();
		}
		else if(msg.equals("Deposite Details"))
		{
			new DepositeDetails();
		}
		else if(msg.equals("Calculate Bill"))
		{
			new CalculateBill();
		}
		else if(msg.equals("Update Information"))
		{
			new UpdateInformation(meterno);
		}
		else if(msg.equals("View Information"))
		{
			new ViewInformation(meterno);
		}
		else if(msg.equals("Pay Bill"))
		{
			new PayBill(meterno);
		}
		else if(msg.equals("Bill Details"))
		{
			new BillDetails(meterno);
		}
		else if(msg.equals("Generate Bill"))
		{
			new GenerateBill(meterno);
		}
		else if(msg.equals("Notepad"))
		{
			try
			{
				Runtime.getRuntime().exec("notepad.exe");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(msg.equals("Calculator"))
		{
			try
			{
				Runtime.getRuntime().exec("calc.exe");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(msg.equals("Exit"))
		{
			setVisible(false);
			new Login();
		}
	}
	
	public static void main(String[] args) {
		new Project("","");

	}

}
