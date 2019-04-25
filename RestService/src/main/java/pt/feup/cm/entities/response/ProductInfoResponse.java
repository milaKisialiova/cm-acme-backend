package pt.feup.cm.entities.response;

import java.math.BigDecimal;

public class ProductInfoResponse extends BaseResponse {

	private Integer id;
	private String name;
	private BigDecimal price;
	private String description;
	// private byte[] image;

	public ProductInfoResponse() {
		super();
	}

	public ProductInfoResponse(Integer id, String name, BigDecimal price, String description) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ProductInfoResponse [name=" + name + ", price=" + price + ", description=" + description + "]";
	}
}
