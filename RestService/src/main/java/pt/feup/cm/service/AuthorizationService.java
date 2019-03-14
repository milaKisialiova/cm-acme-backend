package pt.feup.cm.service;

import pt.feup.cm.config.AppConfig;
import pt.feup.cm.config.CredentialValidator;
import pt.feup.cm.entities.response.BaseResponse;
import pt.feup.cm.mocks.MockUtils;
import pt.feup.cm.warehouse.enums.ErrorCode;
import pt.feup.cm.warehouse.exception.BusinessException;

public class AuthorizationService extends BaseService {

	public BaseResponse signUp(String name, String password, String fiscalNumber) {
		if (AppConfig.USE_MOCKS_REGISTRATION) {
			return MockUtils.signUp();
		}
		try {
			CredentialValidator.validate(name, password, fiscalNumber);
			getWarehouseManager().addUser(name, password, fiscalNumber);
		} catch (BusinessException e) {
			return new BaseResponse(e.getCode().getCode());
		} catch (Exception e) {
			return new BaseResponse(ErrorCode.CODE_AUTH.getCode());
		}
		return new BaseResponse();
	}

	public BaseResponse login(String name, String password) {
		if (AppConfig.USE_MOCKS_LOGIN) {
			return MockUtils.login();
		}
		try {
			if (getWarehouseManager().isValidUser(name, password)) {
				// TODO init User Session
				return new BaseResponse();
			}
		} catch (BusinessException e) {
			return new BaseResponse(e.getCode().getCode());
		} catch (Exception e) {
			return new BaseResponse(ErrorCode.CODE_AUTH.getCode());
		}
		return new BaseResponse(ErrorCode.CODE_AUTH.getCode());
	}
}
