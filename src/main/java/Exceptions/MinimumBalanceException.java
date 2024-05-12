package Exceptions;

public class MinimumBalanceException extends Exception {
	String msg;
	public MinimumBalanceException() {
		msg=" Amount is invalid enter the valid Amount : ";
	}
	public MinimumBalanceException(String msg) {
		this.msg=msg;
	}
	@Override
	public String toString() {
		return msg;
	}

}
