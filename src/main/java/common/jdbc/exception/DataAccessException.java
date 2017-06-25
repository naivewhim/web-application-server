package common.jdbc.exception;

public class DataAccessException extends RuntimeException {
	private static final long serialVersionUID = -6897313855129024109L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}

	public DataAccessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
