package pt.feup.cm.entities.response;

import java.util.Date;

public class PaymentInfoResponse extends BaseResponse {

	private String token;
	private Date date;
	private Double totalAmount;
	private String memo;

	public PaymentInfoResponse() {
		super();
	}

	public PaymentInfoResponse(Integer errorCode) {
		super(errorCode);
	}

	public PaymentInfoResponse(String token, Date date, Double totalAmount, String memo) {
		super();
		this.token = token;
		this.date = date;
		this.totalAmount = totalAmount;
		this.memo = memo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
