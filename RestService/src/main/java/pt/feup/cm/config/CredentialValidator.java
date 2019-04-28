package pt.feup.cm.config;

import pt.feup.cm.warehouse.enums.ErrorCode;
import pt.feup.cm.warehouse.exception.BusinessException;

public class CredentialValidator {

	public static void validate(String name, String password, String fisNumber,
			String cardNumber, String cardValidity, String cardValidDate) throws BusinessException {
		if (name == null || name.isEmpty() || 
				!name.matches("^[A-Za-z](?=.*[a-z])(?=\\S+$).{3,}$")) {
			throw new BusinessException(ErrorCode.CODE_AUTH_VALIDATION_NAME);
		}
		if (password == null || password.isEmpty() || 
				!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+-=])(?=\\S+$).{6,}$")) {
			throw new BusinessException(ErrorCode.CODE_AUTH_VALIDATION_PASSWORD);
		}
		if (fisNumber == null || fisNumber.isEmpty() || 
				!fisNumber.matches("^\\d{9}$")) {
			throw new BusinessException(ErrorCode.CODE_AUTH_VALIDATION_FISNUM);
		}
		if (cardNumber == null || cardNumber.isEmpty() || 
				!cardNumber.matches("^\\d{16}$")) {
			throw new BusinessException(ErrorCode.CODE_AUTH_VALIDATION_CARD_NUMBER);
		}
		if (cardValidity == null || cardValidity.isEmpty() || 
				!cardValidity.matches("^\\d{3}$")) {
			throw new BusinessException(ErrorCode.CODE_AUTH_VALIDATION_CARD_VALIDITY);
		}
		if (cardValidDate == null || cardValidDate.isEmpty() 
				/*|| !cardValidDate.matches("^\\d{2}/\\d{2}$")*/) {
			throw new BusinessException(ErrorCode.CODE_AUTH_VALIDATION_CARD_VALID_DATE);
		}
	}
}
