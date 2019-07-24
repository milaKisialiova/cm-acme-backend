package pt.feup.cm.entities.response;

import pt.feup.cm.entities.Product;

public class CartItemResponse extends BaseResponse {
	private Product product;
	private Integer number;

	public CartItemResponse() {
		super();
	}

	public CartItemResponse(Product product, Integer number) {
		super();
		this.product = product;
		this.number = number;
	}

	public CartItemResponse(int errorCode) {
		super(errorCode);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "CartItemResponse [product=" + product + ", number=" + number + ",errorCode=" + getErrorCode() + "]";
	}

}
