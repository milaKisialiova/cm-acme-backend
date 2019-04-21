package pt.feup.cm.entities;

public class Card {
	private String type;
	private String number;
	private String holderName;
	private String validDate;
	private String validity;

	
	public Card() {
	}

	public Card(String type, String number, String holderName, String validDate, String validity) {
		super();
		this.type = type;
		this.number = number;
		this.holderName = holderName;
		this.validDate = validDate;
		this.validity = validity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}
	
	@Override
	public String toString() {
		return "Card [type=" + type + ", number=" + getMaskedNumber() + ", holderName=" + holderName + ", validDate=" + validDate
				+ ", validity=" + getMaskedValidity() + "]";
	}
	
	private String getMaskedValidity() {
		if (validity != null)
			return "***";
		return validity;
	}

	private String getMaskedNumber() {
		if (number != null && number.length() == 16)
			return number.substring(0, 1) + "*** **** **** " + number.substring(13);
		return number;
	}
}
