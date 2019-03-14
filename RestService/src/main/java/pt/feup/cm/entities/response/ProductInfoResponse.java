package pt.feup.cm.entities.response;

import java.math.BigDecimal;

public class ProductInfoResponse extends BaseResponse {

	private Integer id;
	private String name;
	private BigDecimal price;
	private String descrition;
	// private byte[] image;

	public ProductInfoResponse() {
		super();
	}

	public ProductInfoResponse(Integer id, String name, BigDecimal price, String descrition) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.descrition = descrition;
	}

	public ProductInfoResponse(int errorCode) {
		super(errorCode);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ProductInfoResponse [name=" + name + ", price=" + price + ", descrition=" + descrition + "]";
	}
}
