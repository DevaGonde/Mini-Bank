package operations;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import Exceptions.InsufficientAmountException;
import Exceptions.InvalidAmountException;
import Exceptions.InvalidChoiceException;
import Exceptions.MinimumBalanceException;


public class AccountOperations {

	private static Scanner sc = new Scanner(System.in);

	public static List<accounts> showAllDetails() {
		Session session = new Configuration().configure().addAnnotatedClass(accounts.class).buildSessionFactory()
				.openSession();
		
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from accounts order by AcNo");
		q.setCacheable(true);
		List<accounts> li = q.list();

		if (li.isEmpty())
			System.out.println(" it is null");
		for (accounts a : li) {
			System.out.println(a);
		}
		tx.commit();
		session.close();
		return li;
	}

	public static boolean checkExistance(int accountNo) {
		boolean exist = false;
		Session session = new Configuration().configure().addAnnotatedClass(accounts.class).buildSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();

		accounts a = session.get(accounts.class, accountNo);
		if (a != null)
			exist = true;

		tx.commit();
		session.close();
		return exist;
	}

	public static accounts fetchSingleRecord(int accountNo) {
		Session session = new Configuration().configure().addAnnotatedClass(accounts.class).buildSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
		accounts a = session.get(accounts.class, accountNo);
		tx.commit();
		session.close();
		return a;
	}

	public static String giveGender() {
		String gender = null;
		String choice = "0";
		char ch = '0';
		while (ch != '1' || ch != '2') {
			try {
				System.out.println(" 1 for MALE\n 2 for FEMALE : ");
				choice = sc.next();
				ch = choice.charAt(0);
				if (ch == '1') {
					gender = "male";
					break;
				} else if (ch == '2') {
					gender = "female";
					break;
				} else
					throw new InvalidChoiceException("Choose a valid choice :");
			} catch (InvalidChoiceException e) {
				System.out.println(e);
			} catch (Exception e) {
				System.out.println(" Other Exception found : " + e);
			}
		}
		return gender;
	}

	public static String giveAccountType() {
		String type = null;
		String choice = "0";
		char ch = '0';
		while (ch != '1' || ch != '2') {
			try {
				System.out.println(" 1 for Saving\n 2 for Current : ");
				choice = sc.next();
				ch = choice.charAt(0);
				if (ch == '1') {
					type = "saving";
					break;
				} else if (ch == '2') {
					type = "current";
					break;
				} else
					throw new InvalidChoiceException("Choose a valid choice :");
			} catch (InvalidChoiceException e) {
				System.out.println(e);
			} catch (Exception e) {
				System.out.println(" Other Exception found : " + e);
			}
		}
		return type;
	}

	public static int generateAccountNum() {

		Session s = new Configuration().configure().addAnnotatedClass(accounts.class).buildSessionFactory()
				.openSession();
		Transaction tx = s.beginTransaction();
		Criteria c = s.createCriteria(accounts.class).setProjection(Projections.max("AcNo"));
		int no = (int) c.uniqueResult();

		tx.commit();
		s.close();
		return no + 1;
	}

	public static void createNewAccount() {
		int Accountnum = 0;
		try {
			Accountnum = generateAccountNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name = null, gender = null, type = null;
		int amt = 0;

		try {
			System.out.println(" Enter your name : ");
			name = sc.next();

			gender = giveGender();

			type = giveAccountType();
			String a = "";
			while (true) {
				try {
					System.out.println(" Enter Amount of First Deposite (minimum 500) : ");
					a = sc.next();
					amt = Integer.parseInt(a);
					if (amt < 0)
						throw new InvalidAmountException("Amount is less than zero ");
					if (amt < 500)
						throw new MinimumBalanceException(" enter amount greater than 500");
					break;
				} catch (InvalidAmountException e) {
					System.out.println(e);
					continue;
				} catch (MinimumBalanceException e) {
					System.out.println(e);
					continue;
				} catch (Exception e) {
					System.out.println(" Invalid Input");
					continue;
				}

			}
		} catch (InputMismatchException e) {
			System.out.println(" Invalid input ");
		}
		accounts a = new accounts(Accountnum, name.toLowerCase(), amt, type, gender);
		try {

			Session s = new Configuration().configure().addAnnotatedClass(accounts.class).buildSessionFactory()
					.openSession();
			Transaction tx = s.beginTransaction();
			s.save(a);
			tx.commit();
			s.close();

			accounts aa = fetchSingleRecord(Accountnum);
			if (aa != null) {
				System.out.println(" Account Created successfully");
				System.out.println(aa);
			} else
				System.out.println(" something went wrong unable to create account ");
		} catch (Exception e) {
			System.out.println(" other Exception :" + e);
		}
	}

	public static boolean deposite(int accountNum) {
		System.out.println(" Enter the amount : ");
		int amt;
		boolean isDeposited = false;
		do {
			try {
				String amttemp = sc.next();
				amt = Integer.parseInt(amttemp);
				if (amt < 0)
					throw new InvalidAmountException(" Amount is less than zero");
			} catch (InvalidAmountException e) {
				System.out.println(e);
				continue;
			} catch (Exception e) {
				System.out.println(" Enter the valid amount : ");
				continue;
			}
			break;
		} while (true);

		try {
			Session s = new Configuration().configure().addAnnotatedClass(accounts.class).buildSessionFactory()
					.openSession();
			Transaction tx = s.beginTransaction();
			accounts a = s.get(accounts.class, accountNum);
			boolean count = a.setBalance(a.getBalance() + amt);
			tx.commit();
			s.close();
			if (count) {
				isDeposited = true;
				System.out.println(fetchSingleRecord(accountNum));
			} else
				System.out.println(" Something went Wrong : ");

		} catch (Exception e) {
			System.out.println(" other Exception :" + e);
		}
		return isDeposited;
	}

	public static int balanceGiver(int accountNum) {
		accounts a = null;
		try {
			Session s = new Configuration().configure().addAnnotatedClass(accounts.class).buildSessionFactory()
					.openSession();
			Transaction tx = s.beginTransaction();
			a = s.get(accounts.class, accountNum);
			tx.commit();
			s.close();
		} catch (Exception e) {
			System.out.println(" other Exception :" + e);
		}

		return a.getBalance();

	}

	public static void withdraw(int accountNum) {
		do {
			System.out.println(" Enter the amount : ");
			int amt;

			try {
				String amttemp = sc.next();
				amt = Integer.parseInt(amttemp);

				int dbamt = AccountOperations.balanceGiver(accountNum);

				if (amt < 0)
					throw new InvalidAmountException(" Amount less than zero ");

				if (dbamt < amt)
					throw new InsufficientAmountException("the balance is insufficient");

				if (dbamt - amt < 500)
					throw new MinimumBalanceException("The minimum balance should be 500");

				Session s = new Configuration().configure().addAnnotatedClass(accounts.class).buildSessionFactory()
						.openSession();
				Transaction tx = s.beginTransaction();
				accounts a = s.get(accounts.class, accountNum);
				boolean count = a.setBalance(a.getBalance() - amt);
				tx.commit();
				s.close();

				if (count) {
					System.out.println(" Money successfully withdraw ");
					System.out.println(fetchSingleRecord(accountNum));
					break;
				} else {
					System.out.println(" Unable to withdraw money");
				}
			} catch (InvalidAmountException e) {
				System.out.println(e);
				continue;
			} catch (InsufficientAmountException e) {
				System.out.println(e);
				continue;
			} catch (MinimumBalanceException e) {
				System.out.println(e);
				continue;
			} catch (Exception e) {
				System.out.println(" other Exception :" + e);
				continue;
			}

		} while (true);
	}

	public static boolean delete(int accountNum) {
		boolean isDelete = false;
		if (AccountOperations.checkExistance(accountNum)) {
			try {
				Session s = new Configuration().configure().addAnnotatedClass(accounts.class).buildSessionFactory()
						.openSession();
				Transaction tx = s.beginTransaction();
				Query q = s.createQuery("delete from accounts where AcNo=" + accountNum);
				q.setCacheable(true);
				int count = q.executeUpdate();
				tx.commit();
				s.close();
				if (count > 0) {
					isDelete = true;
					System.out.println(" Account deleted ");
				}

			} catch (HibernateException e) {
				System.out.println(" Hibernate Exception :" + e);
			} catch (Exception e) {
				System.out.println(" other Exception :" + e);
			}

		} else {
			System.out.println(" No user found of Account no : " + accountNum);
		}
		return isDelete;
	}

	public static void main(String[] args) {
		System.out.println(generateAccountNum());
	}
}
