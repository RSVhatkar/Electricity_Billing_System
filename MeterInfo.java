import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.*;//to use Random class
import javax.swing.*;
import java.awt.event.*;
public class MeterInfo extends JFrame implements ActionListener{

	JPanel p;
	JLabel meterinfo,meterno,meternolbl,meterlocation,metertype,phasecode,billtype,image,days,dayslbl,note,notelbl;
	JButton submit;
	Choice meterlocationchoice,metertypechoice,phasecodechoice,billtypechoice;
	String smeterno;
	
	MeterInfo(String smeterno)
	{
		this.smeterno=smeterno;
		setSize(700,500);
		setLocation(400,200);
		
		p=new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(173,216,230));
		add(p);
		
		meterinfo=new JLabel("Meter Information");
		meterinfo.setBounds(180,10,400,25);
		meterinfo.setFont(new Font("Tahoma",Font.BOLD,26));
		p.add(meterinfo);
		
		meterno=new JLabel("Meter Number");
		meterno.setBounds(100,80,100,20);
		p.add(meterno);
		
		meternolbl=new JLabel();
		meternolbl.setBounds(240,80,100,20);
		p.add(meternolbl);
		meternolbl.setText(smeterno);
		
		meterlocation=new JLabel("Meter Location");
		meterlocation.setBounds(100,120,100,20);
		p.add(meterlocation);
		
		meterlocationchoice=new Choice();
		meterlocationchoice.setBounds(240,120,200,20);
		meterlocationchoice.add("Outside");
		meterlocationchoice.add("Inside");
		p.add(meterlocationchoice);
			
		metertype=new JLabel("Meter Type");
		metertype.setBounds(100,160,100,20);
		p.add(metertype);
		
		metertypechoice=new Choice();
		metertypechoice.setBounds(240,160,200,20);
		metertypechoice.add("Electric Meter");
		metertypechoice.add("Solar Meter");
		metertypechoice.add("Smart Meter");
		p.add(metertypechoice);
				
		phasecode=new JLabel("Phase Code");
		phasecode.setBounds(100,200,100,20);
		p.add(phasecode);
		
		phasecodechoice=new Choice();
		phasecodechoice.setBounds(240,200,200,20);
		phasecodechoice.add("011");
		phasecodechoice.add("022");
		phasecodechoice.add("033");
		phasecodechoice.add("044");
		phasecodechoice.add("055");
		phasecodechoice.add("066");
		phasecodechoice.add("077");
		phasecodechoice.add("088");
		phasecodechoice.add("099");
		p.add(phasecodechoice);
		
		billtype=new JLabel("Bill Type");
		billtype.setBounds(100,240,100,20);
		p.add(billtype);
		
		billtypechoice=new Choice();
		billtypechoice.setBounds(240,240,200,20);
		billtypechoice.add("Normal");
		billtypechoice.add("Industrial");
		p.add(billtypechoice);
		
		days=new JLabel("Days");
		days.setBounds(100,280,100,20);
		p.add(days);
		
		dayslbl=new JLabel("30 Days");
		dayslbl.setBounds(240,280,100,20);
		p.add(dayslbl);
		
		note=new JLabel("Note");
		note.setBounds(100,320,100,20);
		p.add(note);
		
		notelbl=new JLabel("By default bill is calculated for 30 days only");
		notelbl.setBounds(240,320,500,20);
		p.add(notelbl);
		
		submit=new JButton("Submit");
		submit.setBounds(220,390,100,25);
		submit.setBackground(Color.black);
		submit.setForeground(Color.white);
		p.add(submit);
		submit.addActionListener(this);
		
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
		if(ae.getSource()==submit)
		{
			String query1="insert into meter_info values('"+meternolbl.getText()+"','"+meterlocationchoice.getSelectedItem()+"','"+metertypechoice.getSelectedItem()+"','"+phasecodechoice.getSelectedItem()+"','"+billtypechoice.getSelectedItem()+"','"+dayslbl.getText()+"')";
			
			try
			{
				Conn c=new Conn();
				c.s.executeUpdate(query1);
				
				JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
				
				setVisible(false);
				
				//new frame
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new MeterInfo("");

	}

}
