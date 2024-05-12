package operations;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.cfg.Configuration;

import Exceptions.UserNotFoundException;

@Entity(name = "Employee")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class emp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;
	private String name;
	private String password;

	public emp(int empId, String name, String password) {
		super();
		this.empId = empId;
		this.name = name;
		this.password = password;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public emp() {

	}

	@Override
	public String toString() {
		return "emp [empId=" + /* empId + */ ", name=" + name + ", password=" + password + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

public class Employee {

	public static boolean Login(String ii, String password) {
		boolean login = false;
		int id = Integer.parseInt(ii);
		Configuration con = new Configuration().configure().addAnnotatedClass(emp.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		SQLQuery query = session.createSQLQuery("select * from Employee;");
		query.setCacheable(true);
		query.addEntity(emp.class);
		List<emp> li = query.list();

		boolean pass=false;
		boolean idd=false;
		for (emp e : li) {
			if (e.getEmpId() == id) {
				idd=true;
				if (e.getPassword().equals(password)) {
					System.out.println(" Access Granted | Welcome");
					login = true;
					pass=true;
					break;
				}
			}
		}
		if (idd) {
			if(!pass)
			System.out.println(" Invalid Password ");
		}
		else System.out.println(" Invalid Id ");

		tx.commit();
		session.close();
		sf.close();
		return login;
	}
	
	public static void checking() {
		Session s=new Configuration().configure().addAnnotatedClass(emp.class).buildSessionFactory().openSession();
		Transaction tx=s.beginTransaction();
		
		emp e=s.get(emp.class, 1001);
		e=null;
		
		tx.commit();
		s.close();
	}
	public static void main(String[] args) {
		checking();
	}
}
