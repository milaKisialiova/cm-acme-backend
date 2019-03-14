package pt.feup.cm.warehouse.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "payments")
@NamedQueries({
		@NamedQuery(name = "Payment.getByToken", query = "SELECT p from DbPayment p WHERE p.token = :token") })

public class DbPayment {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "token")
	private String token;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "cart_id")
	private long cartId;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "receipt")
	private String receipt;

	public DbPayment() {
	}

	public DbPayment(String token, long cartId, Double amount, long userId, Date date, String receipt) {
		super();
		this.token = token;
		this.userId = userId;
		this.cartId = cartId;
		this.amount = amount;
		this.date = date;
		this.receipt = receipt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	@Override
	public String toString() {
		return "DbPayment [id=" + id + ", token=" + token + ", userId=" + userId + ", cartId=" + cartId + ", amount="
				+ amount + ", date=" + date + ", receipt=" + receipt + "]";
	}

}
