package Exceptions;

public class UserNotFoundException extends Exception {
	String msg;
	public UserNotFoundException() {
		msg=" The Provided user is not found : ";
	}
	public UserNotFoundException(String msg) {
		this.msg=msg;
	}
	@Override
	public String toString() {
		return msg;
	}

}
