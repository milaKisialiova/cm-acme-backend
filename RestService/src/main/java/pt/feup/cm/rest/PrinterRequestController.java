package pt.feup.cm.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.feup.cm.entities.response.ReceiptResponse;
import pt.feup.cm.service.PaymentService;

@RestController
@RequestMapping("/printer")
public class PrinterRequestController {

	private PaymentService paymentService = new PaymentService();
	
	@RequestMapping(value = "/receipt")
	public ReceiptResponse getReceipt(@RequestParam(value = "id") String token) {
		return paymentService.getReceipt(token);
	}
}
