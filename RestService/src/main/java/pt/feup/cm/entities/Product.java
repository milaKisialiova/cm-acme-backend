package pt.feup.cm.entities;

import java.math.BigDecimal;

public class Product {

	private Integer id;
	private String name;
	private BigDecimal price;
	private String descrition;
	// private byte[] image;

	
	public Product(Integer id, String name, BigDecimal price, String descrition) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.descrition = descrition;
	}

	public Product() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
