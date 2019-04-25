package pt.feup.cm.rest;

import org.apache.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.feup.cm.entities.request.CartItemRequest;
import pt.feup.cm.entities.request.UserInfoRequest;
import pt.feup.cm.entities.response.BaseResponse;
import pt.feup.cm.entities.response.CartItemResponse;
import pt.feup.cm.entities.response.CartResponse;
import pt.feup.cm.entities.response.TokenResponse;
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
	public BaseResponse signUp(@RequestBody UserInfoRequest request) {
		return authorizationService.signUp(request.getName(), request.getPassword(), request.getAddress(),
				request.getFiscalNumber(), request.getCard().getHolderName(), request.getCard().getNumber(),
				request.getCard().getValidity(), request.getCard().getValidDate(), request.getCard().getType(),
				request.getPublicRsaKey());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public TokenResponse login(@RequestBody UserInfoRequest request) {
		return authorizationService.login(request.getName(), request.getPassword());
	}

	@RequestMapping(value = "/product/{barcode}", method = RequestMethod.GET)
	public ProductInfoResponse getProduct(@PathVariable("barcode") String barcode) {
		return productService.getProduct(barcode);
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public CartResponse getCart(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
		return cartService.getCart(token);
	}

	@RequestMapping(value = "/cart/clean", method = RequestMethod.DELETE)
	public BaseResponse cleanActiveCart(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
		return cartService.cleanCart(token);
	}

	@RequestMapping(value = "/cart/item/{id}", method = RequestMethod.GET)
	public CartItemResponse getCartItem(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
			@PathVariable(value = "id") Integer cartItemId) {
		return cartService.getCartItem(token, cartItemId);
	}

	@RequestMapping(value = "/cart/item", method = RequestMethod.POST)
	public BaseResponse addToCart(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
			@RequestBody CartItemRequest request) {
		return cartService.addToCart(token, request.getProductId(), request.getNumber());
	}

	@RequestMapping(value = "/cart/item/{id}", method = RequestMethod.DELETE)
	public BaseResponse deleteFromCart(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
			@PathVariable(value = "id") Integer cartItemId) {
		return cartService.deleteFromCart(token, cartItemId);
	}

	@RequestMapping(value = "/payment/do", method = RequestMethod.GET)
	public PaymentInfoResponse doPayment(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
			@RequestParam(value = "token") String paymentToken) {
		return paymentService.doPayment(token, paymentToken);
	}
	/*
	 * 
	@RequestMapping(value = "/payment/do/{token}", method = RequestMethod.GET)
	public PaymentInfoResponse doPayment(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authToken,
			@PathVariable(value = "token") String paymentToken) {
		return paymentService.doPayment(authToken, paymentToken);
	 */

	/*
	 * @RequestMapping(value = "/payment/qrcode", method = RequestMethod.GET) public
	 * PaymentInfoResponse getQrCode(@RequestParam(value = "id") Integer paymentId)
	 * { return paymentService.getQrCode(paymentId); }
	 * 
	 * @RequestMapping(value = "/payment/nfc", method = RequestMethod.GET) public
	 * PaymentInfoResponse getNfc(@RequestParam(value = "id") Integer paymentId) {
	 * return paymentService.getNfc(paymentId); }
	 */
}
