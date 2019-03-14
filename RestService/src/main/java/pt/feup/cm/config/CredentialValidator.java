package pt.feup.cm.config;

import pt.feup.cm.warehouse.enums.ErrorCode;
import pt.feup.cm.warehouse.exception.BusinessException;

public class CredentialValidator {

	public static void validate(String name, String password, String fisNumber) throws BusinessException {
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
	}
}
