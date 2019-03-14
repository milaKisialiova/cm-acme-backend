package pt.feup.cm.entities.response;

import java.util.List;

import pt.feup.cm.entities.Cart;

public class CartResponse extends BaseResponse {

	private List<Cart> items;

	public CartResponse() {
		super();
	}

	public CartResponse(List<Cart> items) {
		super();
		this.items = items;
	}

	public CartResponse(int errorCode) {
		super(errorCode);
	}

	public List<Cart> getItems() {
		return items;
	}

	public void setItems(List<Cart> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "CartResponse [items=" + items + "]";
	}
}
