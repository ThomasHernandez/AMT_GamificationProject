package ch.heigvd.gamification.api;

/**
 *
 * @author Antony
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-29T09:54:16.483Z")

public class NotFoundException extends ApiException {
	private int code;

    /**
     *
     * @param code
     * @param msg
     */
    public NotFoundException (int code, String msg) {
		super(code, msg);
		this.code = code;
	}
}
