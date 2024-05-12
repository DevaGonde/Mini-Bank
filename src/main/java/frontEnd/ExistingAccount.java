package frontEnd;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class ExistingAccount extends JFrame{

	public ExistingAccount() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1920, 1080);
		this.setBackground(Color.gray);
		this.setLayout(new GridLayout(7, 1));

	}
}
