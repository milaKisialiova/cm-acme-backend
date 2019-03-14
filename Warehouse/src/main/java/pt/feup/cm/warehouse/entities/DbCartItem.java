package pt.feup.cm.warehouse.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "carts_products")
@NamedQueries({
	@NamedQuery(name = "Product.getByCart", query = "SELECT p from DbCartItem p WHERE p.cartId = :cartId"),
	@NamedQuery(name = "Product.deleteByCart", query = "DELETE from DbCartItem p WHERE p.cartId = :cartId")
})
public class DbCartItem {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "product_id")
	private long productId;

	@Column(name = "product_num")
	private Integer number;

	@Column(name = "cart_id")
	private long cartId;
	
	public DbCartItem() {
	}
	
	public DbCartItem(long productId, Integer number, long cartId) {
		super();
		this.productId = productId;
		this.number = number;
		this.cartId = cartId;
	}

	public long getId() {
		return id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "id=" + id + ", productId=" + productId + ", number=" + number + ", cartId=" + cartId;
	}

}
