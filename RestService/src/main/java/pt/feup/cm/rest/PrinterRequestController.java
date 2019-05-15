package pt.feup.cm.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.feup.cm.entities.response.ReceiptResponse;
import pt.feup.cm.service.PaymentService;

@RestController
@RequestMapping("/printer")
public class PrinterRequestController {

	private static final Logger logger = LoggerFactory.getLogger(PrinterRequestController.class.getSimpleName());
	
	private PaymentService paymentService = new PaymentService();
	
	@RequestMapping(value = "/receipt")
	public ReceiptResponse getReceipt(@RequestParam(value = "id") String token) {
		logger.debug("GET /app/payment/do?id={}", token);
		return paymentService.getReceipt(token);
	}
}
