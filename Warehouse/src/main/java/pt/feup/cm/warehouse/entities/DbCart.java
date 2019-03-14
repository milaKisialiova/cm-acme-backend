package pt.feup.cm.warehouse.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
@NamedQueries({
	@NamedQuery(name = "Cart.getByUser", query = "SELECT c from DbCart c WHERE c.userId = :userId"),
	@NamedQuery(name = "Cart.getByStatus", query = "SELECT c from DbCart c WHERE c.userId = :userId and c.cartStatus = :status")
})
public class DbCart {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "status")
	private String cartStatus;

	public DbCart() {
	}

	public DbCart(long userId, String cartStatus) {
		super();
		this.userId = userId;
		this.cartStatus = cartStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCartStatus() {
		return cartStatus;
	}

	public void setCartStatus(String cartStatus) {
		this.cartStatus = cartStatus;
	}

	@Override
	public String toString() {
		return "id=" + id + ", userId=" + userId + ", cartStatus=" + cartStatus;
	}


}
