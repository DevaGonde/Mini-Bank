package frontEnd;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class NewAccount extends JFrame{
	
	JTextField nameval=null;
	JTextField balanceval=null;
	JRadioButton g1=null;
	JRadioButton g2=null;
	JRadioButton t1=null;
	JRadioButton t2=null;
	
	public NewAccount() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1920, 1080);
		this.setBackground(Color.gray);
		this.setLayout(new GridLayout(5, 1));
		
		JLabel hedding=new JLabel(" -------- Create New Account -------- ");
		JLabel name=new JLabel(" Name :");
		JLabel gender=new JLabel(" gender :");
		JLabel type=new JLabel(" Account Type :");
		JLabel balance=new JLabel(" Depositing Amt :");
		
		nameval=new JTextField(10);
		
		g1=new JRadioButton(" Male ");
		g2=new JRadioButton(" Female ");
		ButtonGroup bg1=new ButtonGroup();
		bg1.add(g1);
		bg1.add(g2);
		
		t1=new JRadioButton(" Saving ");
		t2=new JRadioButton(" Current ");
		ButtonGroup bg2=new ButtonGroup();
		bg2.add(t1);
		bg2.add(t2);
		
		balanceval=new JTextField(10);
		
		Panel ge=new Panel(new GridLayout(2,2));
		ge.add(gender);
		ge.add(g1);
		ge.add(new JLabel());
		ge.add(g2);
		
		Panel ty=new Panel(new GridLayout(2,2));
		ty.add(type);
		ty.add(t1);
		ty.add(new JLabel());
		ty.add(t2);
		
		Panel p1= new Panel(new FlowLayout());
		p1.add(hedding);
		Panel p2=new Panel(new FlowLayout());
		p2.add(name);
		p2.add(nameval);
		p2.add(gender);
		p2.add(ge);
		p2.add(type);
		p2.add(ty);
		p2.add(balance);
		p2.add(balanceval);
		
		this.add(p1);
		this.add(p2);

	}
	public static void main(String[] args) {
		new NewAccount();
	}
}
