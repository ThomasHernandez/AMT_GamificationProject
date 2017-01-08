package ch.heigvd.gamification.api;

import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Antony
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-29T09:54:16.483Z")

@javax.xml.bind.annotation.XmlRootElement
public class ApiResponseMessage {

    /**
     *
     */
    public static final int ERROR = 1;

    /**
     *
     */
    public static final int WARNING = 2;

    /**
     *
     */
    public static final int INFO = 3;

    /**
     *
     */
    public static final int OK = 4;

    /**
     *
     */
    public static final int TOO_BUSY = 5;

	int code;
	String type;
	String message;
	
    /**
     *
     */
    public ApiResponseMessage(){}
	
    /**
     *
     * @param code
     * @param message
     */
    public ApiResponseMessage(int code, String message){
		this.code = code;
		switch(code){
		case ERROR:
			setType("error");
			break;
		case WARNING:
			setType("warning");
			break;
		case INFO:
			setType("info");
			break;
		case OK:
			setType("ok");
			break;
		case TOO_BUSY:
			setType("too busy");
			break;
		default:
			setType("unknown");
			break;
		}
		this.message = message;
	}

    /**
     *
     * @return
     */
    @XmlTransient
	public int getCode() {
		return code;
	}

    /**
     *
     * @param code
     */
    public void setCode(int code) {
		this.code = code;
	}

    /**
     *
     * @return
     */
    public String getType() {
		return type;
	}

    /**
     *
     * @param type
     */
    public void setType(String type) {
		this.type = type;
	}

    /**
     *
     * @return
     */
    public String getMessage() {
		return message;
	}

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
		this.message = message;
	}
}
