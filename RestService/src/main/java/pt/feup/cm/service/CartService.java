package pt.feup.cm.service;

import java.util.ArrayList;
import java.util.List;

import pt.feup.cm.config.AppConfig;
import pt.feup.cm.config.AuthTokenGenerator;
import pt.feup.cm.entities.Cart;
import pt.feup.cm.entities.CartItem;
import pt.feup.cm.entities.Product;
import pt.feup.cm.entities.response.BaseResponse;
import pt.feup.cm.entities.response.CartItemResponse;
import pt.feup.cm.entities.response.CartResponse;
import pt.feup.cm.mocks.MockUtils;
import pt.feup.cm.warehouse.entities.DbCart;
import pt.feup.cm.warehouse.entities.DbCartItem;
import pt.feup.cm.warehouse.entities.DbProduct;
import pt.feup.cm.warehouse.entities.DbUser;
import pt.feup.cm.warehouse.enums.ErrorCode;
import pt.feup.cm.warehouse.exception.BusinessException;

public class CartService extends BaseService {

	AuthTokenGenerator tokenGenerator = new AuthTokenGenerator();
	
	public CartResponse getCart(String token) {
		if (AppConfig.USE_MOCKS_GET_CART) {
			return MockUtils.getCart();
		}
		CartResponse rsp = new CartResponse(new ArrayList<Cart>());
		try {
			token = tokenGenerator.resolveToken(token);
			tokenGenerator.validateToken(token);
			DbUser user = getWarehouseManager().getUserByName(tokenGenerator.getUsername(token));
			List<DbCart> dbCarts = getWarehouseManager().getUserCart(user);
			for (DbCart dbCart : dbCarts) {
				Cart cartRsp = new Cart();
				cartRsp.setCartStatus(dbCart.getCartStatus());
				cartRsp.setId((int) dbCart.getId());
				cartRsp.setItems(new ArrayList<CartItem>());
				for (DbCartItem dbCartItem : getWarehouseManager().getCartItems(dbCart)) {
					DbProduct dbProduct = getWarehouseManager().getProduct(dbCartItem.getProductId());
					Product productRsp = new Product((int) dbProduct.getId(), dbProduct.getName(), dbProduct.getPrice(),
							dbProduct.getDescription());
					cartRsp.getItems().add(new CartItem((int) dbCartItem.getId(), productRsp, dbCartItem.getNumber()));
				}
				cartRsp.calcTotalPrice();
				rsp.getItems().add(cartRsp);
			}
		} catch (BusinessException e) {
			return new CartResponse(e.getCode().getValue());
		} catch (Exception e) {
			return new CartResponse(ErrorCode.CODE_GENERAL.getValue());
		}
		return rsp;
	}

	public BaseResponse cleanCart(String token) {
		if (AppConfig.USE_MOCKS_CLEAN_CART) {
			return MockUtils.cleanCart();
		}
		try {
			token = tokenGenerator.resolveToken(token);
			tokenGenerator.validateToken(token);
			DbUser user = getWarehouseManager().getUserByName(tokenGenerator.getUsername(token));
			DbCart dbCart = getWarehouseManager().getUserActiveCart(user);
			getWarehouseManager().deleteCartItems(dbCart);
		} catch (BusinessException e) {
			return new BaseResponse(e.getCode().getValue());
		} catch (Exception e) {
			return new BaseResponse(ErrorCode.CODE_GENERAL.getValue());
		}
		return new BaseResponse();
	}

	public CartItemResponse getCartItem(String token, Integer cartItemId) {
		if (AppConfig.USE_MOCKS) {
			return MockUtils.getCartItem();
		}
		CartItemResponse rsp = null;
		try {
			token = tokenGenerator.resolveToken(token);
			tokenGenerator.validateToken(token);
			DbCartItem dbCartItem = getWarehouseManager().getCartItem(cartItemId);
			DbProduct dbProduct = getWarehouseManager().getProduct(dbCartItem.getProductId());
			Product productRsp = new Product((int) dbProduct.getId(), dbProduct.getName(), dbProduct.getPrice(),
					dbProduct.getDescription());
			rsp = new CartItemResponse(productRsp, dbCartItem.getNumber());
		} catch (BusinessException e) {
			return new CartItemResponse(e.getCode().getValue());
		} catch (Exception e) {
			return new CartItemResponse(ErrorCode.CODE_GENERAL.getValue());
		}
		return rsp;
	}

	public BaseResponse addToCart(String token, Integer productId, Integer number) {
		if (AppConfig.USE_MOCKS_CART_ADD) {
			return MockUtils.addToCart();
		}
		try {
			token = tokenGenerator.resolveToken(token);
			tokenGenerator.validateToken(token);
			DbUser user = getWarehouseManager().getUserByName(tokenGenerator.getUsername(token));
			DbCart dbCart = getWarehouseManager().getUserActiveCart(user);
			getWarehouseManager().addCartItem(productId, number, dbCart.getId());
		} catch (BusinessException e) {
			return new BaseResponse(e.getCode().getValue());
		} catch (Exception e) {
			return new BaseResponse(ErrorCode.CODE_GENERAL.getValue());
		}
		return new BaseResponse();
	}

	public BaseResponse deleteFromCart(String token, Integer cartItemId) {
		if (AppConfig.USE_MOCKS_CART_DELETE) {
			return MockUtils.deleteFromCart();
		}
		try {
			token = tokenGenerator.resolveToken(token);
			tokenGenerator.validateToken(token);
			getWarehouseManager().deleteCartItem(cartItemId);
		} catch (BusinessException e) {
			return new BaseResponse(e.getCode().getValue());
		} catch (Exception e) {
			return new BaseResponse(ErrorCode.CODE_GENERAL.getValue());
		}
		return new BaseResponse();
	}
}
