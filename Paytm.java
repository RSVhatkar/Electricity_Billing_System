import javax.swing.*;
import java.awt.event.*;
public class Paytm extends JFrame implements ActionListener{

	String smeterno;
	JButton back;
	
	Paytm(String smeterno)
	{
		this.smeterno=smeterno;
		
		setSize(800,600);
		setLocation(400,150);
		
		JEditorPane pane=new JEditorPane();
		pane.setEditable(false);
		
		try
		{
			pane.setPage("https://paytm.com/rent-payment"); //to load the paytm page
		}
		catch(Exception e)
		{
			pane.setContentType("text/html");
			pane.setText("<html>Could not load</html>");
		}
		
		JScrollPane sp=new JScrollPane(pane);
		getContentPane().add(sp);
		
		back=new JButton("Back");
		back.setBounds(640,20,80,30);
		pane.add(back);
		back.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		setVisible(false);
		new PayBill(smeterno);
	}
	
	public static void main(String[] args) {
		new Paytm("");

	}

}
