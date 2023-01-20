package exceptionhandler.custom_exception;

@SuppressWarnings("serial")
public class IncorrectPasswordException extends RuntimeException {
	public IncorrectPasswordException(String mesg) {
		super(mesg);
	}
}
