package exceptionhandler.custom_exception;

@SuppressWarnings("serial")
public class SubscriptionExpiredException extends RuntimeException {
	public SubscriptionExpiredException(String mesg) {
		super(mesg);
	}
}
