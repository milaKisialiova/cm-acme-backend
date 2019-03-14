package pt.feup.cm.warehouse.exception;

import pt.feup.cm.warehouse.enums.ErrorCode;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 476967502224456955L;
	private ErrorCode code;

	public ServiceException(Throwable cause) {
		super(cause);
		this.code = ErrorCode.CODE_GENERAL;
	}

	public ErrorCode getCode() {
		return code;
	}

	public void setCode(ErrorCode code) {
		this.code = code;
	}

}