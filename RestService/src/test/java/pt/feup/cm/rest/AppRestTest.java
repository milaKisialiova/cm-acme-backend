package pt.feup.cm.rest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pt.feup.cm.entities.Card;
import pt.feup.cm.entities.request.CartItemRequest;
import pt.feup.cm.entities.request.UserInfoRequest;
import pt.feup.cm.entities.response.BaseResponse;
import pt.feup.cm.entities.response.CartResponse;
import pt.feup.cm.entities.response.ProductInfoResponse;
import pt.feup.cm.entities.response.TokenResponse;
import pt.feup.cm.warehouse.enums.ErrorCode;

public class AppRestTest {

	public final static String APP_ADDRESS_BASE = "http://192.168.56.1:5050/rest/app/";
	private static HttpClient client = HttpClientBuilder.create().build();
	private static ObjectMapper mapper = new ObjectMapper();


	@Test
	public void testGetProduct() throws ClientProtocolException, IOException {
		HttpResponse httpResponse = executeGetRequest("product?barcode=1", null);
		
		ProductInfoResponse rsp = retrieveResourceFromResponse(httpResponse, ProductInfoResponse.class);
		assertNull(rsp.getErrorCode());
		assertEquals(Integer.valueOf(1), rsp.getId());
		assertEquals("TV samsung", rsp.getName());
		assertEquals(BigDecimal.valueOf(1200), rsp.getPrice());
		assertEquals("Samsung-TV22-SW00K", rsp.getDescrition());
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
		UserInfoRequest req = new UserInfoRequest("Vasiliy", "Porto", "123456789", "Abc-123", card, "123");
		HttpResponse httpResponse = executePostRequest("signup", req, null);
		
		BaseResponse rsp = retrieveResourceFromResponse(httpResponse, BaseResponse.class);
		assertNull(rsp.getErrorCode());
		
		httpResponse = executePostRequest("signup", req, null);
		rsp = retrieveResourceFromResponse(httpResponse, BaseResponse.class);
		assertEquals(ErrorCode.CODE_AUTH_FIS_NUM_UNIQUE.getValue(), rsp.getErrorCode());
		
		req.setFiscalNumber("123456780");
		httpResponse = executePostRequest("signup", req, null);
		rsp = retrieveResourceFromResponse(httpResponse, BaseResponse.class);
		assertEquals(ErrorCode.CODE_AUTH_NAME_UNIQUE.getValue(), rsp.getErrorCode());
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
		
		CartItemRequest reqAdd = new CartItemRequest(1, 2);
		HttpResponse httpResponseAdd = executePostRequest("/cart/item/add", reqAdd, rspLogin.getToken());
		BaseResponse rspAdd = retrieveResourceFromResponse(httpResponseAdd, BaseResponse.class);
		assertNull(rspAdd.getErrorCode());
		
		HttpResponse httpResponseCart = executeGetRequest("/cart", rspLogin.getToken());
		CartResponse rspCart = retrieveResourceFromResponse(httpResponseCart, CartResponse.class);
		assertNull(rspCart.getErrorCode());
		assertEquals(rspCart.getItems().get(0).getItems().get(0).getNumber(), Integer.valueOf(2));
		assertEquals(rspCart.getItems().get(0).getItems().get(0).getProduct().getId(), Integer.valueOf(1));
	}

	public static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz) throws IOException {
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(jsonFromResponse, clazz);
	}
	
	public static HttpResponse executeGetRequest(String urlSuffix, String token) throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(APP_ADDRESS_BASE + urlSuffix);
		if (token != null) {
			request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
	    }
		return client.execute(request);
	}
	
	public static HttpResponse executePostRequest(String urlSuffix, Object body, String token) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(APP_ADDRESS_BASE + urlSuffix);
		
		httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	    if (token != null) {
	    	httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
	    }
	    
	    String json = mapper.writeValueAsString(body);
	    StringEntity entity = new StringEntity(json);
	    httpPost.setEntity(entity);
	    
		return client.execute(httpPost);
	}
}
