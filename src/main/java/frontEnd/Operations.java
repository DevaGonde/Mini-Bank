package frontEnd;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import operations.AccountOperations;
import operations.accounts;

public class Operations extends JFrame implements ActionListener {

	JButton showAllAccounts = null;
	JButton createNewAccount = null;
	JButton existingAccountOperations = null;
	JButton logout = null;
	JTextArea ta=null;

	public Operations() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1920, 1080);
		this.setBackground(Color.gray);
		this.setLayout(new GridLayout(7, 1));

		JLabel l1 = new JLabel(" ---------- Operations -------");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		Panel p1 = new Panel();
		p1.setLayout(new FlowLayout());
		p1.add(l1);

		this.add(p1);
		this.add(new Panel());

		showAllAccounts = new JButton(" show All Accounts ");
		createNewAccount = new JButton(" Create New Account ");
		existingAccountOperations = new JButton(" Existing Account Operations ");
		logout = new JButton(" Logout Account ");
		showAllAccounts.addActionListener(this);
		createNewAccount.addActionListener(this);
		existingAccountOperations.addActionListener(this);
		logout.addActionListener(this);

		Panel op = new Panel(new GridLayout(4, 1));
		op.add(showAllAccounts);
		op.add(createNewAccount);
		op.add(existingAccountOperations);
		op.add(logout);

		this.add(op);
		this.add(ta=new JTextArea());

	}

	public static void main(String[] args) {
		new Operations();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

//		showAllAccounts = null;
//		JButton createNewAccount = null;
//		JButton existingAccountOperations = null;
//		JButton existingAccountOperations 
		if (e.getSource() == showAllAccounts) {
			List<accounts> li=AccountOperations.showAllDetails();
			String s="";
			ta.setText(null);
			for(accounts a:li)
			  s+=a.toString()+"\n";
			ta.setText(s);
		} else if (e.getSource() == createNewAccount) {
			this.dispose();
			new NewAccount();
		} else if (e.getSource() == existingAccountOperations) {
			this.dispose();
			new ExistingAccount();
		} else if (e.getSource() == logout) {
			this.dispose();
			new UserLogin();
		} else {
			JOptionPane.showMessageDialog(null, " Unknown Entity Pressed ");
		}

	}
}
