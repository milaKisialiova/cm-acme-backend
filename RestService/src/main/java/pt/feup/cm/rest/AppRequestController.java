package pt.feup.cm.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.feup.cm.entities.request.CartItemRequest;
import pt.feup.cm.entities.request.UserInfoRequest;
import pt.feup.cm.entities.response.BaseResponse;
import pt.feup.cm.entities.response.CartItemResponse;
import pt.feup.cm.entities.response.CartResponse;
import pt.feup.cm.entities.response.PaymentInfoResponse;
import pt.feup.cm.entities.response.ProductInfoResponse;
import pt.feup.cm.service.AuthorizationService;
import pt.feup.cm.service.CartService;
import pt.feup.cm.service.PaymentService;
import pt.feup.cm.service.ProductService;

@RestController
@RequestMapping("/app")
public class AppRequestController {

	private AuthorizationService authorizationService = new AuthorizationService();
	private ProductService productService = new ProductService();
	private CartService cartService = new CartService();
	private PaymentService paymentService = new PaymentService();

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public BaseResponse signup(@RequestBody UserInfoRequest request) {
		return authorizationService.signUp(request.getName(), request.getPassword(), request.getFiscalNumber());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public BaseResponse login(@RequestBody UserInfoRequest request) {
		return authorizationService.login(request.getName(), request.getPassword());
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ProductInfoResponse getProduct(@RequestParam(value = "barcode") String barcode) {
		return productService.getProduct(barcode);
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	//TODO no user id, get from User Session
	public CartResponse getCart(@RequestParam(value = "id") Integer userId) {
		return cartService.getCart(userId);
	}

	@RequestMapping(value = "/cart/clean", method = RequestMethod.DELETE)
	//TODO no user id, get from User Session
	public BaseResponse cleanActiveCart(@RequestParam(value = "id") Integer userId) {
		return cartService.cleanCart(userId);
	}

	@RequestMapping(value = "/cart/item", method = RequestMethod.GET)
	public CartItemResponse getCartItem(@RequestParam(value = "id") Integer cartItemId) {
		return cartService.getCartItem(cartItemId);
	}

	@RequestMapping(value = "/cart/item/add", method = RequestMethod.POST)
	//TODO no user id, get from User Session
	public BaseResponse addToCart(@RequestBody CartItemRequest request) {
		return cartService.addToCart(request.getProductId(), request.getNumber(), request.getUserId());
	}

	@RequestMapping(value = "/cart/item/delete", method = RequestMethod.DELETE)
	public BaseResponse deleteFromCart(@RequestParam(value = "id") Integer cartItemId) {
		return cartService.deleteFromCart(cartItemId);
	}

	@RequestMapping(value = "/payment/do", method = RequestMethod.GET)
	//TODO no user id, get from User Session
	public PaymentInfoResponse doPayment(@RequestParam(value = "id") Integer userId) {
		return paymentService.doPayment(userId);
	}

	/*
	@RequestMapping(value = "/payment/qrcode", method = RequestMethod.GET)
	public PaymentInfoResponse getQrCode(@RequestParam(value = "id") Integer paymentId) {
		return paymentService.getQrCode(paymentId);
	}

	@RequestMapping(value = "/payment/nfc", method = RequestMethod.GET)
	public PaymentInfoResponse getNfc(@RequestParam(value = "id") Integer paymentId) {
		return paymentService.getNfc(paymentId);
	}
	*/
}
