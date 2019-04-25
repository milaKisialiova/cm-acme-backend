package pt.feup.cm.rest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.RsaProvider;
import pt.feup.cm.entities.Card;
import pt.feup.cm.entities.Cart;
import pt.feup.cm.entities.CartItem;
import pt.feup.cm.entities.request.CartItemRequest;
import pt.feup.cm.entities.request.UserInfoRequest;
import pt.feup.cm.entities.response.BaseResponse;
import pt.feup.cm.entities.response.CartItemResponse;
import pt.feup.cm.entities.response.CartResponse;
import pt.feup.cm.entities.response.PaymentInfoResponse;
import pt.feup.cm.entities.response.ProductInfoResponse;
import pt.feup.cm.entities.response.TokenResponse;
import pt.feup.cm.warehouse.enums.ErrorCode;

public class AppRestTest extends BaseTest {

	@Test
	public void testGetProduct() throws ClientProtocolException, IOException {
		HttpResponse httpResponse = executeGetRequest("product/1", null);
		
		ProductInfoResponse rsp = retrieveResourceFromResponse(httpResponse, ProductInfoResponse.class);
		assertNull(rsp.getErrorCode());
		assertEquals(Integer.valueOf(1), rsp.getId());
		assertEquals("TV samsung", rsp.getName());
		assertEquals(BigDecimal.valueOf(1200), rsp.getPrice());
		assertEquals("Samsung-TV22-SW00K", rsp.getDescription());
	}
	
	@Test
	public void testGetProductNotFound() throws ClientProtocolException, IOException {
		HttpResponse httpResponse = executeGetRequest("product?barcode=-1", null);
		
		ProductInfoResponse rsp = retrieveResourceFromResponse(httpResponse, ProductInfoResponse.class);
		assertEquals(ErrorCode.CODE_GENERAL.getValue(), rsp.getErrorCode());
	}
	
	@Test
	public void testSignup() throws ClientProtocolException, IOException {
		Card card = new Card("VISA", "1234123412341234", "Vasiliy Pupkin", "0222", "123");
		KeyPair kp = RsaProvider.generateKeyPair(1024);
		byte[] publicKey = kp.getPublic().getEncoded();
		UserInfoRequest req = new UserInfoRequest("Vasiliy", "Porto", "123456789", "Abc-123", card, publicKey);
		HttpResponse httpResponse = executePostRequest("signup", req, null);
		
		BaseResponse rsp = retrieveResourceFromResponse(httpResponse, BaseResponse.class);
		assertNull(rsp.getErrorCode());
	}
	
	@Test
	public void testLogin() throws ClientProtocolException, IOException {
		UserInfoRequest req = new UserInfoRequest("Vasiliy", "Abc-123");
		HttpResponse httpResponse = executePostRequest("login", req, null);
		
		TokenResponse rsp = retrieveResourceFromResponse(httpResponse, TokenResponse.class);
		assertNotNull(rsp.getToken());
	}
	
	@Test
	public void testAddToCart() throws ClientProtocolException, IOException {
		UserInfoRequest reqLogin = new UserInfoRequest("Vasiliy", "Abc-123");
		HttpResponse httpResponseLogin = executePostRequest("login", reqLogin, null);
		TokenResponse rspLogin = retrieveResourceFromResponse(httpResponseLogin, TokenResponse.class);
		
		Integer productId = 1;
		Integer numberOfProducts = 1;
		CartItemRequest reqAdd = new CartItemRequest(productId, numberOfProducts);
		HttpResponse httpResponseAdd = executePostRequest("cart/item/add", reqAdd, rspLogin.getToken());
		BaseResponse rspAdd = retrieveResourceFromResponse(httpResponseAdd, BaseResponse.class);
		assertNull(rspAdd.getErrorCode());
		
		HttpResponse httpResponseCart = executeGetRequest("cart", rspLogin.getToken());
		CartResponse rspCart = retrieveResourceFromResponse(httpResponseCart, CartResponse.class);
		assertNull(rspCart.getErrorCode());
		boolean found = false;
		for (Cart cart : rspCart.getItems()) {
			if (cart.getCartStatus().equals("active")) {
				for (CartItem cartItem : cart.getItems()) {
					if (cartItem.getProduct().getId().equals(productId)
							&& cartItem.getNumber().equals(numberOfProducts)) {
						found = true;
					}
				}
				break;
			}
		}
		assertTrue(found);
	}
	
	@Test
	public void testRemoveFromCart() throws ClientProtocolException, IOException {
		UserInfoRequest reqLogin = new UserInfoRequest("Vasiliy", "Abc-123");
		HttpResponse httpResponseLogin = executePostRequest("login", reqLogin, null);
		TokenResponse rspLogin = retrieveResourceFromResponse(httpResponseLogin, TokenResponse.class);
		
		CartItemRequest reqAdd = new CartItemRequest(2, 4);
		HttpResponse httpResponseAdd = executePostRequest("cart/item", reqAdd, rspLogin.getToken());
		BaseResponse rspAdd = retrieveResourceFromResponse(httpResponseAdd, BaseResponse.class);
		assertNull(rspAdd.getErrorCode());
		
		HttpResponse httpResponseCart = executeGetRequest("cart", rspLogin.getToken());
		CartResponse rspCart = retrieveResourceFromResponse(httpResponseCart, CartResponse.class);
		
		Integer cartIdToRemove = null;
		for (Cart cart : rspCart.getItems()) {
			if (cart.getCartStatus().equals("active")) {
				for (CartItem cartItem : cart.getItems()) {
					if (cartItem.getProduct().getId().equals(2)) {
						cartIdToRemove = cartItem.getId();
						break;
					}
				}
				break;
			}
		}
		HttpResponse httpResponse = executeDeleteRequest("cart/item/" + cartIdToRemove, rspLogin.getToken());
		
		BaseResponse rsp = retrieveResourceFromResponse(httpResponse, BaseResponse.class);
		assertNull(rsp.getErrorCode());
	}
	
	@Test
	public void testGetCartItem() throws ClientProtocolException, IOException {
		UserInfoRequest reqLogin = new UserInfoRequest("Vasiliy", "Abc-123");
		HttpResponse httpResponseLogin = executePostRequest("login", reqLogin, null);
		TokenResponse rspLogin = retrieveResourceFromResponse(httpResponseLogin, TokenResponse.class);
		
		HttpResponse httpResponseCart = executeGetRequest("cart/item/3", rspLogin.getToken());
		CartItemResponse rspCart = retrieveResourceFromResponse(httpResponseCart, CartItemResponse.class);
		
		assertNull(rspCart.getErrorCode());
	}
	
	@Test
	public void testCleanActiveCart() throws ClientProtocolException, IOException {
		UserInfoRequest reqLogin = new UserInfoRequest("Vasiliy", "Abc-123");
		HttpResponse httpResponseLogin = executePostRequest("login", reqLogin, null);
		TokenResponse rspLogin = retrieveResourceFromResponse(httpResponseLogin, TokenResponse.class);
		
		HttpResponse httpResponse = executeDeleteRequest("cart/clean", rspLogin.getToken());
		
		BaseResponse rsp = retrieveResourceFromResponse(httpResponse, BaseResponse.class);
		assertNull(rsp.getErrorCode());
	}
	
	@Test
	public void testDoPayment() throws ClientProtocolException, IOException {
		
		KeyPair kp = RsaProvider.generateKeyPair(1024);
		PublicKey pubKey = kp.getPublic();
		PrivateKey privKey = kp.getPrivate();
		Claims claims = Jwts.claims().setSubject("Payment");
		String token = Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.RS256, privKey).compact();
		
		Card card = new Card("VISA", "1234123412341234", "Vasiliy Pupkin", "0222", "123");
		UserInfoRequest req = new UserInfoRequest("PaymentTest", "Porto", "123456780", "Abc-123", card, pubKey.getEncoded());
		
		HttpResponse httpResponse = executePostRequest("signup", req, null);
		
		BaseResponse rsp1 = retrieveResourceFromResponse(httpResponse, BaseResponse.class);
		assertNull(rsp1.getErrorCode());
		
		UserInfoRequest reqLogin = new UserInfoRequest("PaymentTest", "Abc-123");
		HttpResponse httpResponseLogin = executePostRequest("login", reqLogin, null);
		TokenResponse rspLogin = retrieveResourceFromResponse(httpResponseLogin, TokenResponse.class);
		
		httpResponse = executeGetRequest("payment/do?token=" + token, rspLogin.getToken());
		
		PaymentInfoResponse rsp2 = retrieveResourceFromResponse(httpResponse, PaymentInfoResponse.class);
		assertNull(rsp2.getErrorCode());
		assertNotNull(rsp2.getDate());
		assertNotNull(rsp2.getToken());
		assertNotNull(rsp2.getMemo());
	}
	
}
