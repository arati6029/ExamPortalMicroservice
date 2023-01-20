package exceptionhandler.custom_exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String mesg) {
		super(mesg);
	}
}
