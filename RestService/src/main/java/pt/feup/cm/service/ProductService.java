package pt.feup.cm.service;

import pt.feup.cm.config.AppConfig;
import pt.feup.cm.entities.response.ProductInfoResponse;
import pt.feup.cm.mocks.MockUtils;
import pt.feup.cm.warehouse.entities.DbProduct;
import pt.feup.cm.warehouse.enums.ErrorCode;

public class ProductService extends BaseService {

	public ProductInfoResponse getProduct(String barcode) {
		if (AppConfig.USE_MOCKS_PRODUCT) {
			return MockUtils.getProduct();
		}
		ProductInfoResponse rsp = null;
		try {
			DbProduct product = getWarehouseManager().getProductByBarcode(barcode);
			rsp = new ProductInfoResponse((int) product.getId(), product.getName(), product.getPrice(),
					product.getDescription());
		} catch (Exception e) {
			return new ProductInfoResponse(ErrorCode.CODE_GENERAL.getCode());
		}
		return rsp;
	}
}
