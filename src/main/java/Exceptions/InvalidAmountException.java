package Exceptions;

public class InvalidAmountException extends Exception {
	String msg;
	public InvalidAmountException() {
		msg=" Amount is invalid enter the valid Amount : ";
	}
	public InvalidAmountException(String msg) {
		this.msg=msg;
	}
	@Override
	public String toString() {
		return msg;
	}
}
