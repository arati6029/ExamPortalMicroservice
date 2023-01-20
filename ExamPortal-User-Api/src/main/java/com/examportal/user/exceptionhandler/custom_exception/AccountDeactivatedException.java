package com.examportal.user.exceptionhandler.custom_exception;

@SuppressWarnings("serial")
public class AccountDeactivatedException extends RuntimeException {
	public AccountDeactivatedException(String mesg) {
		super(mesg);
	}
}
