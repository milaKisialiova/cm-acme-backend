package pt.feup.cm.service;

import pt.feup.cm.config.AppConfig;
import pt.feup.cm.config.AuthGenerator;
import pt.feup.cm.config.CredentialValidator;
import pt.feup.cm.entities.response.BaseResponse;
import pt.feup.cm.entities.response.TokenResponse;
import pt.feup.cm.mocks.MockUtils;
import pt.feup.cm.warehouse.enums.ErrorCode;
import pt.feup.cm.warehouse.exception.BusinessException;

public class AuthorizationService extends BaseService {

	AuthGenerator tokenGenerator = new AuthGenerator();
	
	public BaseResponse signUp(String name, String password, String address, String fiscalNumber, String cardHolderName,
			String cardNumber, String cardValidity, String cardValidDate, String cardType, byte[] publicRsaKey) {
		if (AppConfig.USE_MOCKS_REGISTRATION) {
			return MockUtils.signUp();
		}
		try {
			CredentialValidator.validate(name, password, fiscalNumber, cardNumber, cardValidity, cardValidDate);
			getWarehouseManager().addUser(name, password, address, fiscalNumber, cardHolderName, cardNumber,
					cardValidity, cardValidDate, cardType, publicRsaKey);
			
		} catch (BusinessException e) {
			return new BaseResponse(e.getCode().getValue());
		} catch (Exception e) {
			return new BaseResponse(ErrorCode.CODE_AUTH.getValue());
		}
		return new BaseResponse();
	}

	public TokenResponse login(String name, String password) {
		if (AppConfig.USE_MOCKS_LOGIN) {
			return MockUtils.login();
		}
		try {
			if (getWarehouseManager().isValidUser(name, password)) {
				return new TokenResponse(tokenGenerator.createToken(name));
			}
		} catch (BusinessException e) {
			return new TokenResponse(e.getCode().getValue());
		} catch (Exception e) {
			return new TokenResponse(ErrorCode.CODE_AUTH.getValue());
		}
		return new TokenResponse(ErrorCode.CODE_AUTH.getValue());
	}
}
