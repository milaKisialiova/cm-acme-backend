package pt.feup.cm.warehouse.enums;

public enum CartStatus {
	ACTIVE("active"), PAYED("payed");

	String value;

	CartStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
