package pt.feup.cm.entities.request;

import pt.feup.cm.entities.Card;

public class UserInfoRequest extends BaseRequest {

	private String name;
	private String address;
	private String fiscalNumber;
	private String password;
	private Card card;
	private byte[] publicRsaKey;

	public UserInfoRequest() {
		super();
	}

	public UserInfoRequest(String name, String address, String fiscalNumber, String password, Card card, byte[] publicRsaKey) {
		super();
		this.name = name;
		this.address = address;
		this.fiscalNumber = fiscalNumber;
		this.password = password;
		this.card = card;
		this.publicRsaKey = publicRsaKey;
	}
	
	public UserInfoRequest(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFiscalNumber() {
		return fiscalNumber;
	}

	public void setFiscalNumber(String fiscalNumber) {
		this.fiscalNumber = fiscalNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public byte[] getPublicRsaKey() {
		return publicRsaKey;
	}

	public void setPublicRsaKey(byte[] publicRsaKey) {
		this.publicRsaKey = publicRsaKey;
	}

	@Override
	public String toString() {
		return "UserInfoRequest [name=" + name + ", address=" + address + ", fiscalNumber=" + fiscalNumber
				+ /*", password=" + password + */ ", card=" + card + ", publicRsaKey=" + publicRsaKey + "]";
	}
}
