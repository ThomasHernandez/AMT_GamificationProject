package ch.heigvd.gamification.api;

/**
 *
 * @author Antony
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-29T09:54:16.483Z")

public class ApiException extends Exception{
	private int code;

    /**
     *
     * @param code
     * @param msg
     */
    public ApiException (int code, String msg) {
		super(msg);
		this.code = code;
	}
}
