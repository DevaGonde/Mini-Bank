package Exceptions;

public class InvalidChoiceException extends Exception {
	String msg;
	public InvalidChoiceException() {
		msg=" Amount is invalid enter the valid Amount : ";
	}
	public InvalidChoiceException(String msg) {
		this.msg=msg;
	}
	@Override
	public String toString() {
		return msg;
	}
}
