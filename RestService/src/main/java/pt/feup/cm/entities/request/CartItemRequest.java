package pt.feup.cm.entities.request;

public class CartItemRequest extends BaseRequest {

	private Integer productId;
	private Integer number;
	private Integer userId; // TODO remove when use UserSession

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
