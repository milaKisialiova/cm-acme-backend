package pt.feup.cm.rest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import pt.feup.cm.entities.response.ReceiptResponse;

public class PrinterRestTest extends BaseTest {

	@Test
	public void testReceipt() throws ClientProtocolException, IOException {
		HttpResponse httpResponse = executeGetRequest("receipt?id=D3D15D45-AEC6-4466-98B2-489C5D062B11_PM_1556186500655", null);
		
		ReceiptResponse rsp = retrieveResourceFromResponse(httpResponse, ReceiptResponse.class);
		assertNull(rsp.getErrorCode());
		assertNotNull(rsp.getMemo());
	}

}
