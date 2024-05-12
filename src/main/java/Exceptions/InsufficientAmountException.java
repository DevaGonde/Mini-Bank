package Exceptions;

public class InsufficientAmountException extends Exception {
	String msg;
	public InsufficientAmountException() {
		msg=" Amount is invalid enter the valid Amount : ";
	}
	public InsufficientAmountException(String msg) {
		this.msg=msg;
	}
	@Override
	public String toString() {
		return msg;
	}
}
