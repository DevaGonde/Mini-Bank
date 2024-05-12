package frontEnd;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import operations.Employee;

public class UserLogin extends JFrame implements ActionListener{

	JLabel t1 = null;
	JLabel t2 = null;
	JLabel t3 = null;
	JLabel Heading = null;
	JTextField ta1 = null;
	JTextField ta2 = null;
	JButton bt1=null;

	public UserLogin() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1920, 1080);
		this.setBackground(Color.gray);
		this.setLayout(new GridLayout(20, 1));
		Heading = new JLabel(" @Deva's Banking.....");
		Heading.setHorizontalAlignment(SwingConstants.CENTER);
		Heading.setFont(new Font("Areal", Font.PLAIN, 20));
		this.add(Heading);
		Panel g1 = new Panel();
		Panel g2 = new Panel();
		Panel g3 = new Panel();
		Panel g4 = new Panel();
		g1.setLayout(new FlowLayout());
		g2.setLayout(new FlowLayout());
		g3.setLayout(new FlowLayout());

		t1 = new JLabel(" Enter Id : ");
		ta1 = new JTextField(10);
		t2 = new JLabel(" Password : ");
		ta2 = new JTextField(10);

		bt1=new JButton("  Login  ");
		bt1.addActionListener(this);

		g1.add(t1);
		g1.add(ta1);
		g2.add(t2);
		g2.add(ta2);
		g3.add(bt1);
		g4.add(t3=new JLabel());

		this.add(new Panel());
		this.add(new Panel());
		this.add(g1);
		this.add(g2);
		this.add(g3);
		this.add(g4);
		

	}

	public static void main(String[] args) {
		new UserLogin();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt1) {
			try {
				String id=ta1.getText();
				String pass=ta2.getText();
				if(Employee.Login(id, pass)) {
					JOptionPane.showMessageDialog(null, "Access Granted");
					this.dispose();
					new Operations();
				}
				else {
				  JOptionPane.showMessageDialog(null, "Access Denied");
				  ta1.setText(null);
				  ta2.setText(null);
				  t3.setText("Invalid Details | Please Try again ");
				}
				
			}catch(InputMismatchException ee) {
				System.out.println(" Invalid Input");
				ta1.setText(null);
				 ta2.setText(null);
				t3.setText("Invalid Input Please Try again ");
			}
		}
	}

}
