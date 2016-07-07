package jp.nobu;

/**
 * ノブアプリのシステムエラーを表す。
 * 
 * @author yosuke
 *
 */
public class NobuSystemException extends RuntimeException {

	private static final long serialVersionUID = 4565133750157390242L;

	public NobuSystemException() {}

	public NobuSystemException(String msg) {
		super(msg);
	}

	public NobuSystemException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public static NobuSystemException wrap(String msg, Throwable cause) {
		if (cause instanceof NobuAppConfigureException) { return (NobuSystemException) cause; }
		throw new NobuSystemException(msg, cause);
	}

}
