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
	CODE_AUTH_VALIDATION_CARD_NUMBER(607),
	CODE_AUTH_VALIDATION_CARD_VALIDITY(608),
	CODE_AUTH_VALIDATION_CARD_VALID_DATE(609),
	
	AUTH_TOKEN_EXPIRED(610),
	AUTH_TOKEN_INVALID(611),

	CODE_CART(700),
	
	CODE_PRODUCT(800),
	
	CODE_PAYMENT(900);
	
	Integer value;
	
	ErrorCode(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
