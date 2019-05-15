package pt.feup.cm.entities.request;

public class CartItemRequest extends BaseRequest {

	private Integer productId;
	private Integer number;

	
	public CartItemRequest() {
	}

	public CartItemRequest(Integer productId, Integer number) {
		super();
		this.productId = productId;
		this.number = number;
	}

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

	@Override
	public String toString() {
		return "CartItemRequest [productId=" + productId + ", number=" + number + "]";
	}
}
