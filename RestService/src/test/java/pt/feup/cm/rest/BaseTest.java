package pt.feup.cm.rest;

import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {

	public final static String APP_ADDRESS_BASE = "http://192.168.56.1:5050/rest/app/";
	public final static String PRINTER_ADDRESS_BASE = "http://192.168.56.1:5050/rest/printer/";
	
	private static HttpClient client = HttpClientBuilder.create().build();
	private static ObjectMapper mapper = new ObjectMapper();


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
	
	public static HttpResponse executeDeleteRequest(String urlSuffix, String token) throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpDelete(APP_ADDRESS_BASE + urlSuffix);
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
