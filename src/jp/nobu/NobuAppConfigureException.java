package jp.nobu;

public class NobuAppConfigureException extends RuntimeException {

	private static final long serialVersionUID = 4565133750157390246L;

	public NobuAppConfigureException() {}

	public NobuAppConfigureException(String msg) {
		super(msg);
	}

	public NobuAppConfigureException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public static NobuAppConfigureException wrap(String msg, Throwable cause) {
		if (cause instanceof NobuAppConfigureException) { return (NobuAppConfigureException) cause; }
		throw new NobuAppConfigureException(msg, cause);
	}

}
