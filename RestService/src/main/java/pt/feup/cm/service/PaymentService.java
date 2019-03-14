package pt.feup.cm.service;

import java.math.BigDecimal;

import pt.feup.cm.config.AppConfig;
import pt.feup.cm.config.PaymentRandomizer;
import pt.feup.cm.config.TokenGenerator;
import pt.feup.cm.entities.response.BaseResponse;
import pt.feup.cm.entities.response.PaymentInfoResponse;
import pt.feup.cm.entities.response.ReceiptResponse;
import pt.feup.cm.mocks.MockUtils;
import pt.feup.cm.warehouse.entities.DbCart;
import pt.feup.cm.warehouse.entities.DbCartItem;
import pt.feup.cm.warehouse.entities.DbPayment;
import pt.feup.cm.warehouse.entities.DbProduct;
import pt.feup.cm.warehouse.entities.DbUser;
import pt.feup.cm.warehouse.enums.ErrorCode;
import pt.feup.cm.warehouse.exception.BusinessException;
import pt.feup.cm.warehouse.exception.ServiceException;

public class PaymentService extends BaseService {

	public PaymentInfoResponse doPayment(Integer userId) {
		if (AppConfig.USE_MOCKS_DO_PAYMENT) {
			return MockUtils.pay();
		}
		PaymentInfoResponse rsp = null;
		try {
			DbUser user = getWarehouseManager().getUser(userId);
			DbCart dbCart = getWarehouseManager().getUserActiveCart(user);
			DbPayment dbPayment = doPayment(dbCart);
			rsp = new PaymentInfoResponse(dbPayment.getToken(), dbPayment.getDate(), dbPayment.getAmount(),
					dbPayment.getReceipt());
			updateCarts(dbCart); // TODO when error, show Info box, not Error box to user
		} catch (BusinessException e) {
			return new PaymentInfoResponse(e.getCode().getValue());
		} catch (Exception e) {
			return new PaymentInfoResponse(ErrorCode.CODE_GENERAL.getValue());
		}
		return rsp;
	}

	private String buildReceipt(DbCart dbCart) throws ServiceException {
		StringBuilder sb = new StringBuilder("RECEIPT\n");
		sb.append("Payment status: OK\n");
		sb.append("Card: 4*5612\n");
		sb.append("Items: \n");
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (DbCartItem dbCartItem : getWarehouseManager().getCartItems(dbCart)) {
			DbProduct dbProduct = getWarehouseManager().getProduct(dbCartItem.getProductId());
			sb.append("  " + dbProduct.getName() + " * " + dbCartItem.getNumber() + "     "
					+ dbProduct.getPrice().multiply(BigDecimal.valueOf(dbCartItem.getNumber())) + "\n");
			totalPrice = totalPrice.add(dbProduct.getPrice().multiply(BigDecimal.valueOf(dbCartItem.getNumber())));
		}
		sb.append("------------------\n");
		sb.append("Total:   " + totalPrice + "\n");
		return sb.toString();
	}

	private DbPayment doPayment(DbCart dbCart) throws ServiceException {
		if (!PaymentRandomizer.isSuccess()) {
			throw new BusinessException(ErrorCode.CODE_PAYMENT);
		}
		return getWarehouseManager().doPayment(TokenGenerator.generate(), dbCart.getId(), dbCart.getUserId(),
				getCartTotalPrice(dbCart).doubleValue(), buildReceipt(dbCart));

	}

	private BigDecimal getCartTotalPrice(DbCart dbCart) throws ServiceException {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (DbCartItem dbCartItem : getWarehouseManager().getCartItems(dbCart)) {
			DbProduct dbProduct = getWarehouseManager().getProduct(dbCartItem.getProductId());
			totalPrice = totalPrice.add(dbProduct.getPrice().multiply(BigDecimal.valueOf(dbCartItem.getNumber())));
		}
		return totalPrice;
	}

	private void updateCarts(DbCart dbCart) throws ServiceException {
		try {
			getWarehouseManager().setCartPayed(dbCart);
			getWarehouseManager().addUserCart(dbCart.getUserId());
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CODE_WARNING_PAYMNET_UPDATE);
		}
	}

	public BaseResponse addToCart(Integer productId, Integer number, Integer userId) {
		if (AppConfig.USE_MOCKS_CART_ADD) {
			return MockUtils.addToCart();
		}
		try {
			DbUser user = getWarehouseManager().getUser(userId);
			DbCart dbCart = getWarehouseManager().getUserActiveCart(user);
			getWarehouseManager().addCartItem(productId, number, dbCart.getId());
		} catch (BusinessException e) {
			return new BaseResponse(e.getCode().getValue());
		} catch (Exception e) {
			return new BaseResponse(ErrorCode.CODE_GENERAL.getValue());
		}
		return new BaseResponse();
	}
	
	public ReceiptResponse getReceipt(String token) {
		if (AppConfig.USE_MOCKS_GET_RECEIPT) {
			return MockUtils.getReceipt();
		}
		ReceiptResponse rsp = null;
		try {
			DbPayment dbPayment = getWarehouseManager().getPayment(token);
			rsp = new ReceiptResponse(dbPayment.getReceipt());
		} catch (BusinessException e) {
			return new ReceiptResponse(e.getCode().getValue());
		} catch (Exception e) {
			return new ReceiptResponse(ErrorCode.CODE_GENERAL.getValue());
		}
		return rsp;
	}

	
	/*
	public PaymentInfoResponse getNfc(Integer paymentId) {
		if (AppConfig.USE_MOCKS) {
			return MockUtils.getNfc();
		}
		return null;
	}

	public PaymentInfoResponse getQrCode(Integer paymentId) {
		if (AppConfig.USE_MOCKS) {
			return MockUtils.getQrCode();
		}
		return null;
	}
	*/
}
