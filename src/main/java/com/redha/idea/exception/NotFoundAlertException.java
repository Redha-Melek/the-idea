package com.redha.idea.exception;

import lombok.Getter;

@Getter
public class NotFoundAlertException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String entityName;

	private final String errorKey;

	public NotFoundAlertException(String defaultMessage, String entityName) {
		super(defaultMessage);
		this.entityName = entityName;
		this.errorKey = "notfound";

	}
}
