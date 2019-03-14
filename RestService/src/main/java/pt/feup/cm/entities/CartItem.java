package pt.feup.cm.entities;

public class CartItem {

	private Integer id;
	private Product product;
	private Integer number;

	public CartItem() {
	}

	public CartItem(Integer id, Product product, Integer number) {
		super();
		this.id = id;
		this.product = product;
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
