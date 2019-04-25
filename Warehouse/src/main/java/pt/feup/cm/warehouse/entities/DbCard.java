package pt.feup.cm.warehouse.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cards")
public class DbCard {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "holder_name")
	private String holderName;

	@Column(name = "card_num")
	private String cardNumber;

	@Column(name = "validity")
	private String cardValidity;

	@Column(name = "valid_date")
	String validDate;

	@Column(name = "type")
	String type;
	
	@Column(name = "user_id")
	private long userId;
	
	public DbCard() {
	}

	public DbCard(String holderName, String cardNumber, String cardValidity, String validDate, String type, long userId) {
		super();
		this.holderName = holderName;
		this.cardNumber = cardNumber;
		this.cardValidity = cardValidity;
		this.validDate = validDate;
		this.type = type;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardValidity() {
		return cardValidity;
	}

	public void setCardValidity(String cardValidity) {
		this.cardValidity = cardValidity;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
