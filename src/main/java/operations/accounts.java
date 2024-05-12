package operations;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class accounts {
	@Id
	private int AcNo;
	private String name;
	private int balance;
	private String type;
	private String gender;

	public accounts() {
		AcNo = 0;
		name = null;
		balance = 0;
		type = "saving";
		gender = "male";
	}

	public accounts(int AcNo, String name, int balance, String type, String gender) {
		super();
		this.AcNo = AcNo;
		this.name = name;
		this.balance = balance;
		this.type = type;
		this.gender = gender;
	}

	public int getAcNo() {
		return AcNo;
	}

	public boolean setAcNo(int AcNo) {
		this.AcNo = AcNo;
		return true;
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		this.name = name;
		return true;
	}

	public int getBalance() {
		return balance;
	}

	public boolean setBalance(int balance) {
		this.balance = balance;
		return true;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return " Account AcNo=" + AcNo + "\t Name=" + name + "\t\t Balance=" + balance + "\t Type=" + type + "\t Gender="
				+ gender;
	}

}