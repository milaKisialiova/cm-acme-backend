package pt.feup.cm.warehouse.enums;

public enum ErrorCode {
	
	CODE_WARNING(200),
	CODE_WARNING_PAYMNET_UPDATE(201),
	
	CODE_GENERAL(500),
	
	CODE_AUTH(600),
	CODE_AUTH_VALIDATION_NAME(601),
	CODE_AUTH_VALIDATION_PASSWORD(602),
	CODE_AUTH_VALIDATION_FISNUM(603),
	
	CODE_AUTH_NAME_UNIQUE(604),
	CODE_AUTH_FIS_NUM_UNIQUE(605),

	CODE_AUTH_NOT_FOUND(606),

	CODE_CART(700),
	
	CODE_PRODUCT(800),
	
	CODE_PAYMENT(900);
	
	int value;
	
	ErrorCode(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
