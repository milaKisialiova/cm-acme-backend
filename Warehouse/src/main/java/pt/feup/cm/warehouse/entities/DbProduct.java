package pt.feup.cm.warehouse.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@NamedQueries({ @NamedQuery(name = "Product.getAll", query = "SELECT p from DbProduct p"),
		@NamedQuery(name = "Product.getByBarcode", query = "SELECT p from DbProduct p WHERE p.barcode = :barcode") })
public class DbProduct {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "image_id")
	private long image;

	@Column(name = "barcode")
	private String barcode;

	public DbProduct() {
	}

	public DbProduct(Integer id, String name, String description, BigDecimal price, long image, String barcode) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.barcode = barcode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public long getImage() {
		return image;
	}

	public void setImage(long image) {
		this.image = image;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", image=" + image
				+ ", barcode=" + barcode;
	}
}
