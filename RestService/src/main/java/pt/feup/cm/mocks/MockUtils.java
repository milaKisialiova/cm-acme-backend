package pt.feup.cm.mocks;

import java.math.BigDecimal;
import java.util.ArrayList;

import pt.feup.cm.entities.Cart;
import pt.feup.cm.entities.Product;
import pt.feup.cm.entities.response.BaseResponse;
import pt.feup.cm.entities.response.CartItemResponse;
import pt.feup.cm.entities.response.CartResponse;
import pt.feup.cm.entities.response.PaymentInfoResponse;
import pt.feup.cm.entities.response.ProductInfoResponse;
import pt.feup.cm.entities.response.ReceiptResponse;

public class MockUtils {

	public static BaseResponse signUp() {
		return new BaseResponse();
	}
	
	public static BaseResponse login() {
		return new BaseResponse();
	}

	public static ProductInfoResponse getProduct() {
		return new ProductInfoResponse(1, "Headphones", BigDecimal.valueOf(250.0), "Best product");
	}

	public static CartResponse getCart() {
		CartResponse rsp = new CartResponse();
		rsp.setItems(new ArrayList<Cart>());
		rsp.getItems().add(new Cart());
		//rsp.getItems().add(new CartItem(new Product("Headphones", BigDecimal.valueOf(250.0), "Best product"), 2));
		//rsp.getItems().add(new CartItem(new Product("Camera", BigDecimal.valueOf(1000.0), "Good choice"), 1));
		return rsp;
	}

	public static BaseResponse cleanCart() {
		return new BaseResponse();
	}
	
	public static CartItemResponse getCartItem() {
		return new CartItemResponse(new Product(1, "Headphones", BigDecimal.valueOf(250.0), "Best product"), 2);
	}

	public static BaseResponse addToCart() {
		return new BaseResponse();
	}

	public static BaseResponse deleteFromCart() {
		return new BaseResponse();
	}

	public static PaymentInfoResponse pay() {
		return new PaymentInfoResponse("PN_50001", null, 1200.0, getReceipt().getMemo());
	}

	public static PaymentInfoResponse getQrCode() {
		return new PaymentInfoResponse("PN_50001", null, 1200.0, getReceipt().getMemo());
	}

	public static PaymentInfoResponse getNfc() {
		return new PaymentInfoResponse("PN_50001", null, 1200.0, getReceipt().getMemo());
	}

	public static ReceiptResponse getReceipt() {
		return new ReceiptResponse("Successful payment\n"
				+ "Payment PN_50001\n"
				+ "Date: 01.01.2019\n"
				+ "Headphones * 2        500\n"
				+ "Camera * 1           1000\n"
				+ "------------------------\n"
				+ "Total amount: 2000");
	}

}
