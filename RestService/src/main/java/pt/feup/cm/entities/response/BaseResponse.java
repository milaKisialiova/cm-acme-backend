package pt.feup.cm.entities.response;

public class BaseResponse {

	private Integer errorCode;

	public BaseResponse() {
	}

	public BaseResponse(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
}
