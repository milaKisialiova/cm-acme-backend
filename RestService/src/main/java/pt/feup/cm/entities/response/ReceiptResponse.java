package pt.feup.cm.entities.response;

public class ReceiptResponse extends BaseResponse {

	private String memo;

	public ReceiptResponse() {
		super();
	}

	public ReceiptResponse(String memo) {
		super();
		this.memo = memo;
	}

	public ReceiptResponse(int errorCode) {
		super(errorCode);
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "ReceiptResponse [memo=" + memo + "]";
	}

}
