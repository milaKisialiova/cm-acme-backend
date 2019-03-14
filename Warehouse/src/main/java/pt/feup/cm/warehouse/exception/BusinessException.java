package pt.feup.cm.warehouse.exception;

import pt.feup.cm.warehouse.enums.ErrorCode;

public class BusinessException extends ServiceException {

	private static final long serialVersionUID = 4430992197083807669L;
	
	public BusinessException(Throwable cause, ErrorCode code) {
		super(cause);
		setCode(code);
	}
	
	public BusinessException(ErrorCode code) {
		this(null, code);
	}
}
