package pt.feup.cm.entities;

import java.math.BigDecimal;
import java.util.List;

public class Cart {

	private List<CartItem> items;
	private Integer id;
	private BigDecimal totalPrice;
	private String cartStatus;
	private String date;
	private String memo;
	

	public void calcTotalPrice() {
		totalPrice = BigDecimal.ZERO;
		if (items == null || items.isEmpty()) {
			return;
		}
		for (CartItem item : items) {
			totalPrice = totalPrice.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getNumber())));
		}
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCartStatus() {
		return cartStatus;
	}

	public void setCartStatus(String cartStatus) {
		this.cartStatus = cartStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "items=" + items + ", id=" + id + ", totalPrice=" + totalPrice + ", cartStatus=" + cartStatus + ", date=" + date + ", memo=" + memo;
	}

}
